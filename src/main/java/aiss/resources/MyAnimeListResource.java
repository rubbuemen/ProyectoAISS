package aiss.resources;

import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_XML_TYPE;
import static javax.ws.rs.core.MediaType.WILDCARD_TYPE;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.restlet.resource.ResourceException;

import aiss.myAnimeList.AnimeEntry;
import aiss.myAnimeList.AnimeListEntryValues;
import aiss.myAnimeList.AnimeListProfileEntry;
import aiss.myAnimeList.VerifyCredentials;
import aiss.utility.Autenticacion;

public class MyAnimeListResource {
	
	private static final Logger log = Logger.getLogger(MyAnimeListResource.class.getName());

	private static final String URL_MYANIMELIST = "https://myanimelist.net";
	private static final String PATH_ANIME_SEARCH = "/api/anime/search.xml";
	private static final String PATH_ANIME_ADD = "/api/animelist/add/%id.xml";
	private static final String PATH_ANIME_UPDATE = "/api/animelist/update/%id.xml";
	private static final String PATH_ANIME_DELETE = "/api/animelist/delete/%id.xml";
	private static final String PATH_ANIME_PROFILE = "/malappinfo.php";
	private static final String PATH_VERIFY_CREDENTIALS = "/api/account/verify_credentials.xml";

	private final Client client;
	private final String myAnimeListURL;
		
		public MyAnimeListResource(String usuario, String clave) {
		this(usuario, clave, URL_MYANIMELIST);
	}

	public MyAnimeListResource(String usuario, String clave, String malUrl) {
		client = ClientBuilder.newClient().register(new Autenticacion(usuario, clave));
		this.myAnimeListURL = malUrl;
	}

	public Boolean verificarCredenciales(String usuario) {
		Boolean res = false;
		VerifyCredentials correcto = client.target(myAnimeListURL).path(PATH_VERIFY_CREDENTIALS).request(APPLICATION_XML_TYPE).get(new GenericType<VerifyCredentials>() {});
		if (usuario.equals(correcto.getUsername())) {
			res = true;
		}
		return res;
	}

	public List<AnimeEntry> getAnimeSearch(String query) {
		List<AnimeEntry> resultados = new ArrayList<>();
		try {
			resultados = client.target(myAnimeListURL).path(PATH_ANIME_SEARCH).queryParam("q", query).request(APPLICATION_XML_TYPE).get(new GenericType<List<AnimeEntry>>() {});
			log.log(Level.FINE, "Búsqueda de animes de " + query + " realizada correctamente.");
		} catch (ResourceException e) {
			log.log(Level.WARNING, "Error al obtener los animes");
			throw e;
		}
		return resultados;
	}

	public Boolean addAnime(String id, AnimeListEntryValues datos) {
		Form form = new Form();
		Boolean res = true;
		try {
			form.param("data", xml(datos, datos.getClass()));
			Response respuesta = client.target(myAnimeListURL).path(PATH_ANIME_ADD.replace("%id", id)).request(WILDCARD_TYPE).post(entity(form, APPLICATION_FORM_URLENCODED_TYPE));
			if(respuesta.getStatus() == 201) {
				log.log(Level.FINE, "Operación de añadir anime realizada correctamente.");
			}
			else {
				log.log(Level.SEVERE, "No se ha podido realizar la operación de añadir anime.");
				res = false;
			}
			return res;
			
		} catch (ResourceException e) {
			log.log(Level.WARNING, "Error al realizar la operación de añadir un anime");
			throw e;
			
		}		
	}

	public Boolean updateAnime(String id, AnimeListEntryValues datos, String usuario) {
		Form form = new Form();
		Boolean res = false;
		try {
			form.param("data", xml(datos, datos.getClass()));
			List<AnimeListProfileEntry> animes = getAnimeProfile(usuario);
			Response respuesta = client.target(myAnimeListURL).path(PATH_ANIME_UPDATE.replace("%id", id)).request(WILDCARD_TYPE).put(entity(form, APPLICATION_FORM_URLENCODED_TYPE));
			if(respuesta.getStatus() == 200) {
				for (AnimeListProfileEntry a : animes) {
					if(id.equals(a.getSeriesId())) {
						res = true;
						log.log(Level.FINE, "Operación de actualizar anime realizada correctamente.");
						break;
					}
				}
			}
			if (res == false) {
				log.log(Level.SEVERE, "No se ha podido realizar la operación de actualizar anime.");
			}
			return res;
		} catch (ResourceException e) {
			log.log(Level.WARNING, "Error al realizar la operación de actualizar un anime");
			throw e;
		}	
	}

	public Boolean deleteAnime(String id, String usuario) {
		Boolean res = false;
		try {
			List<AnimeListProfileEntry> animes = getAnimeProfile(usuario);
			for (AnimeListProfileEntry a : animes) {
				if(id.equals(a.getSeriesId())) {
					res = true;
					log.log(Level.FINE, "Operación de eliminar anime realizada correctamente.");
					break;
				}
			}
			if (res == false) {
				log.log(Level.SEVERE, "No se ha podido realizar la operación de eliminar anime.");
			}
			client.target(myAnimeListURL).path(PATH_ANIME_DELETE.replace("%id", id)).request(WILDCARD_TYPE).delete(String.class);
			return res;
		} catch (ResourceException e) {
			log.log(Level.WARNING, "Error al realizar la operación de eliminar un anime");
			throw e;
		}	
	}

	public List<AnimeListProfileEntry> getAnimeProfile(String usuario) {
		List<AnimeListProfileEntry> resultados = new ArrayList<>();
		try {
			resultados = client.target(myAnimeListURL).path(PATH_ANIME_PROFILE).queryParam("u", usuario).queryParam("type", "anime").queryParam("status", "all").request(APPLICATION_XML_TYPE).get(AnimeListProfileEntry.Perfil.class).getRecords();
			log.log(Level.FINE, "Búsqueda de animes del perfil " + usuario + " realizada correctamente.");
		} catch (ResourceException e) {
			log.log(Level.WARNING, "Error al obtener los animes del perfil " + usuario);
			throw e;
		}
		return resultados;
	}
	
	// Método auxiliar para leer el XML de AnimeListProfileEntryValues
	private String xml(final Object o, final Class<? extends AnimeListEntryValues> t) {
		final StringWriter sw = new StringWriter();
		try {
			JAXBContext.newInstance(t).createMarshaller().marshal(o, sw);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
		return sw.toString();
	}
	

}

package aiss.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import aiss.aliExpress.SearchProducts;
import aiss.animeNewsNetwork.SearchAnimeNews;
import aiss.myAnimeList.AnimeEntry;
import aiss.resources.AliExpressResource;
import aiss.resources.AnimeNewsNetworkResource;
import aiss.resources.MyAnimeListResource;
import aiss.resources.TumblrResource;
import aiss.resources.YouTubeResource;
import aiss.tumblr.SearchImages;
import aiss.youtube.SearchVideos;

public class SelectedAnimeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(SelectedAnimeController.class.getName());

	public SelectedAnimeController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;

		log.log(Level.INFO, "Procesando SelectedAnimeController.");

		// Obtenemos el usuario y la clave decodificados
		String usuario = new String(Base64.decodeBase64(request.getParameter("usuario").getBytes()));
		String clave = new String(Base64.decodeBase64(request.getParameter("clave").getBytes()));
		
		// Inicializamos el recurso de MyAnimeList en null
		MyAnimeListResource autentificacionMyAnimeList = null;
		
		// En el caso de haberse logeado comprobamos que sean correctos el usuario y la clave. También creamos el recurso de MyAnimeList con dicho usuario y clave
		if (usuario != null || clave != null) {
			log.log(Level.INFO, "Verificando credenciales.");
			autentificacionMyAnimeList = new MyAnimeListResource(usuario, clave);
			boolean verificacionResult = autentificacionMyAnimeList.verificarCredenciales(usuario);
			if (verificacionResult == false) {
				log.log(Level.WARNING, "Error al verificar las credenciales, el usuario y/o clave no son correctos.");
				rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
			log.log(Level.FINE, "Credenciales verificadas con éxito.");
		}

		// Obtenemos el parámetro de búsqueda del anime
		String query = request.getParameter("searchQuery");
		
		// Creamos el recurso de Anime News Network
		AnimeNewsNetworkResource animeNewsNetwork = new AnimeNewsNetworkResource();
		
		// Creamos el recurso de Tumblr
		TumblrResource tumblr = new TumblrResource();
		
		// Creamos el recurso de YouTube
		YouTubeResource youtube = new YouTubeResource();
		
		// Creamos el recurso de AliExpress
		AliExpressResource aliexpress = new AliExpressResource();
		
		// Obtenemos los resultados de MyAnimeList según la búsqueda realizada
		log.log(Level.INFO, "Buscando para MyAnimeList información de " + query);
		Collection<AnimeEntry> myAnimeListResults = autentificacionMyAnimeList.getAnimeSearch(query);
		
		// Obtenemos los resultados de Anime News Network según la búsqueda realizada
		log.log(Level.INFO, "Buscando para Anime News Network noticias de " + query);
		Collection<SearchAnimeNews> animeNewsNetworkResults = animeNewsNetwork.getAnimeNews(query);
		
		// Obtenemos los resultados de Tumblr según la búsqueda realizada
		log.log(Level.INFO, "Buscando para Tumblr imágenes de " + query);
		SearchImages tumblrResults = tumblr.getPost(query);
		
		// Obtenemos los resultados de YouTube según la búsqueda realizada
		log.log(Level.INFO, "Buscando para YouTube vídeos de " + query);
		SearchVideos youtubeResults = youtube.getVideo(query);
		
		// Obtenemos los resultados de AliExpress según la búsqueda realizada
		log.log(Level.INFO, "Buscando para AliExpress productos de " + query);
		SearchProducts aliexpressResults = aliexpress.getProducts(query);
		
		// Comprobamos los resultados y en caso no obtenerlos se redirigirá a la página de error
		if (myAnimeListResults != null || animeNewsNetworkResults != null || tumblrResults != null || youtubeResults != null || aliexpressResults != null) {
			request.setAttribute("informacion", myAnimeListResults);
			request.setAttribute("noticias", animeNewsNetworkResults);
			request.setAttribute("fotos", tumblrResults);
			request.setAttribute("videos", youtubeResults);
			request.setAttribute("productos", aliexpressResults);
			rd = request.getRequestDispatcher("/resultados.jsp");
			log.log(Level.FINE, "Búsqueda de información, noticias, fotos, vídeos y productos realiazada y procesada correctamente.");
		} else {
			log.log(Level.WARNING, "Error al realizar la búsqueda");
			rd = request.getRequestDispatcher("/error.jsp");
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}
}
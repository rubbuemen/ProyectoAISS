package aiss.resources.pruebas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;

import aiss.myAnimeList.AnimeEntry;
import aiss.myAnimeList.AnimeListEntryValues;
import aiss.myAnimeList.AnimeListProfileEntry;
import aiss.resources.MyAnimeListResource;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyAnimeListTests {

	private static final String USUARIO = "GrupoMARC";
	private static final String CLAVE = "OuAltR0o2e85";
	private static final String ID_ANIME = "856";
	private static final String ESTADO_ANIME = "1";
	private static final String PUNTUACION_ANIME = "1";
	private static final String EPISODIOS = "1";

	MyAnimeListResource myAnimeList = new MyAnimeListResource(USUARIO, CLAVE);
	
	AnimeListEntryValues datos = new AnimeListEntryValues(ESTADO_ANIME, PUNTUACION_ANIME, EPISODIOS);
	
	@Test
	public void testGetAnimeSearch() throws UnsupportedEncodingException {
		String busqueda = "Naruto";
		System.out.println("Test MyAnimeList: Probando búsqueda de información de " + busqueda);
		List<AnimeEntry> res = myAnimeList.getAnimeSearch(busqueda);

		// La siguiente línea es para traducir cuando no hay resultados
		if (res.isEmpty()) {
			res = null;
		}
		if (res != null) {
			System.out.println("Encontrado Correctamente");
		}

		assertNotNull("La lista de animes no puede ser null", res);
	}
	
	@Test
	public void testGetAnimeProfile() throws UnsupportedEncodingException {
		System.out.println("Test MyAnimeList: Probando búsqueda de la lista de animes de " + USUARIO);
		List<AnimeListProfileEntry> res = myAnimeList.getAnimeProfile(USUARIO);

		// La siguiente línea es para traducir cuando no hay resultados
		if (res.isEmpty()) {
			res = null;
		}
		if (res != null) {
			System.out.println("Encontrado Correctamente");
		}

		assertNotNull("La lista de animes del perfil no puede ser null", res);
	}

	@Test
	public void _testAddAnime() throws UnsupportedEncodingException {
		System.out.println("Test MyAnimeList: Probando añadir anime a la lista de " + USUARIO);
		Boolean res = myAnimeList.addAnime(ID_ANIME, datos); 
		
		if (res == true) {
			System.out.println("Operacion realizada correctamente");
		}

		assertTrue("El anime no se añadió correctamente", res);
	}
	

	@Test
	public void _testUpdateAnime() throws UnsupportedEncodingException {
		System.out.println("Test MyAnimeList: Probando actualizar anime de la lista de " + USUARIO);
		Boolean res = myAnimeList.updateAnime(ID_ANIME, datos, USUARIO); 
		
		if (res == true) {
			System.out.println("Operacion realizada correctamente");
		}

		assertTrue("El anime no se actualizó correctamente", res);
	}
	
	
	@Test
	public void testDeleteAnime() throws UnsupportedEncodingException {
		System.out.println("Test MyAnimeList: Probando eliminar anime de la lista de " + USUARIO);
		Boolean res = myAnimeList.deleteAnime(ID_ANIME, USUARIO); 
		
		if (res == true) {
			System.out.println("Operacion realizada correctamente");
		}

		assertTrue("El anime no se eliminó correctamente", res);
	}
	

}

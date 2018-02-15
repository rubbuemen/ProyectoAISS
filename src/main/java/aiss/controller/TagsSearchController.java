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

import aiss.myAnimeList.AnimeEntry;
import aiss.resources.MyAnimeListResource;

public class TagsSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(TagsSearchController.class.getName());

	public TagsSearchController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;

		log.log(Level.INFO, "Procesando TagsSearchController.");

		// Obtenemos el usuario y la clave decodificados
		String usuario = new String(Base64.decodeBase64(request.getParameter("usuario").getBytes()));
		String clave = new String(Base64.decodeBase64(request.getParameter("clave").getBytes()));

		// Obtenemos el parámetro de búsqueda del anime
		String query = request.getParameter("searchQuery");

		// Creamos el recurso de MyAnimeList con el usuario y la clave
		MyAnimeListResource autentificacionMyAnimeList = new MyAnimeListResource(usuario, clave);

		// Obtenemos la lista de animes según la búsqueda realizada
		Collection<AnimeEntry> myAnimeListResults = autentificacionMyAnimeList.getAnimeSearch(query);
		
		// Comprobamos los resultados y en caso no obtenerlos se redirigirá a la página de error
		if (myAnimeListResults != null) {
			log.log(Level.INFO, "Buscando animes.");
			request.setAttribute("informacion", myAnimeListResults);
			rd = request.getRequestDispatcher("/listaAnimes.jsp");
			log.log(Level.FINE, "Búsqueda de anime realiazada y procesada correctamente.");
		} else {
			log.log(Level.WARNING, "Error al realizar la búsqueda del anime: " + myAnimeListResults);
			rd = request.getRequestDispatcher("/error.jsp");
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}
}
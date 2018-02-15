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

import aiss.myAnimeList.AnimeListProfileEntry;
import aiss.resources.MyAnimeListResource;

public class SearchPerfilController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(SearchPerfilController.class.getName());

	public SearchPerfilController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;

		log.log(Level.INFO, "Procesando SearchPerfilController.");

		// Obtenemos el usuario y la clave decodificados
		String usuario = new String(Base64.decodeBase64(request.getParameter("usuario").getBytes()));
		String clave = new String(Base64.decodeBase64(request.getParameter("clave").getBytes()));

		// Obtenemos el parámetro de búsqueda del perfil
		String query = request.getParameter("searchPerfilQuery");

		// Creamos el recurso de MyAnimeList con el usuario y la clave
		MyAnimeListResource autentificacionMyAnimeList = new MyAnimeListResource(usuario, clave);

		// Obtenemos la lista de animes del perfil
		Collection<AnimeListProfileEntry> searchAnimeProfile = autentificacionMyAnimeList.getAnimeProfile(query);
		
		// Comprobamos los resultados y en caso no obtenerlos se redirigirá a la página de error
		if (searchAnimeProfile != null) {
			log.log(Level.INFO, "Buscando perfil.");
			request.setAttribute("perfilInfo", searchAnimeProfile);
			rd = request.getRequestDispatcher("/perfil.jsp");
			log.log(Level.FINE, "Perfil encontrado y procesado correctamente.");
		} else {
			log.log(Level.WARNING, "Error al buscar el perfil: " + searchAnimeProfile);
			rd = request.getRequestDispatcher("/error.jsp");
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

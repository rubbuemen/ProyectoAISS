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

public class DeleteAnimeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(DeleteAnimeController.class.getName());

	public DeleteAnimeController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;

		log.log(Level.INFO, "Procesando DeleteAnimeController.");

		// Obtenemos el usuario y la clave decodificados
		String usuario = new String(Base64.decodeBase64(request.getParameter("usuario").getBytes()));
		String clave = new String(Base64.decodeBase64(request.getParameter("clave").getBytes()));

		// Obtenemos los parámetros necesarios
		String query = request.getParameter("searchQuery");
		String id = request.getParameter("idAnime");

		// Creamos el recurso de MyAnimeList con el usuario y la clave
		MyAnimeListResource autentificacionMyAnimeList = new MyAnimeListResource(usuario, clave);

		// Eliminamos el anime y en caso de error capturamos la excepción
		try {
			log.log(Level.INFO, "Eliminando anime.");
			boolean deleted = autentificacionMyAnimeList.deleteAnime(id, usuario);
			Collection<AnimeEntry> myAnimeListResults = autentificacionMyAnimeList.getAnimeSearch(query);
			request.setAttribute("informacion", myAnimeListResults);
			if(deleted == true) {
				request.setAttribute("deleted", myAnimeListResults);
				log.log(Level.FINE, "Anime eliminado correctamente.");
			}
			else {
				request.setAttribute("noDeleted", myAnimeListResults);
				log.log(Level.SEVERE, "No se ha podido eliminar el anime correctamente.");
			}
			rd = request.getRequestDispatcher("/listaAnimes.jsp");
		} catch (Exception e) {
			log.log(Level.WARNING, "Error al añadir el anime.");
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}
}
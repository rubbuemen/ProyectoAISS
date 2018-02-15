package aiss;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import aiss.resources.MyAnimeListResource;

public class Index extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Index.class.getName());

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd = null;

		log.log(Level.INFO, "Procesando index.");

		// Por defecto usamos un usuario y clave ya que es obligatorio
		String usuarioCodificado = "GrupoMARC";
		String claveCodificada = "OuAltR0o2e85";

		// Obtenemos el usuario y la clave en el caso de haberse logeado
		String usuario = request.getParameter("usuario");
		String clave = request.getParameter("clave");

		// Obtenemos una variable de sesión para saber si está logeado
		String sesion = request.getParameter("sesion");

		// En el caso de haberse logeado comprobamos que sean correctos el usuario y la clave. En cualquier caso codificamos ambas variables
		if (usuario != null || clave != null) {
			log.log(Level.INFO, "Verificando credenciales.");
			MyAnimeListResource autentificacionMyNimeList = new MyAnimeListResource(usuario, clave);
			boolean verificacionResult = autentificacionMyNimeList.verificarCredenciales(usuario);
			if (verificacionResult == false) {
				log.log(Level.WARNING, "Error al verificar las credenciales, el usuario y/o clave no son correctos.");
				rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
			log.log(Level.FINE, "Credenciales verificadas con éxito.");
			usuarioCodificado = new String(Base64.encodeBase64(usuario.getBytes()));
			claveCodificada = new String(Base64.encodeBase64(clave.getBytes()));
		} else {
			usuarioCodificado = new String(Base64.encodeBase64(usuarioCodificado.getBytes()));
			claveCodificada = new String(Base64.encodeBase64(claveCodificada.getBytes()));
		}

		// Mostramos el contenido con HTML
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html> ");
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' type='text/css' href='/css/general.css'>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>");
		out.println("<title>Inicio</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class='contenedor'>");
		out.println("<div class='col-100 tablet-100 movil-100 alinearCentro reescalarImagen'>");
		out.println("<img class='logoIndex' src='/images/Logo.png'>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class='clear'></div>");
		out.println("<div class='contenedor'>");
		out.println("<div class='col-100 tablet-100 movil-100 alinearCentro'>");
		out.println("<form action='/tagsSearchController' method='post'>");
		out.println("<input type='text' placeholder='Busca un anime' class='busqueda' name='searchQuery' required/><br /><br />");
		out.println("<input type='hidden' name='usuario' value='" + usuarioCodificado + "' />");
		out.println("<input type='hidden' name='clave' value='" + claveCodificada + "' />");
		out.println("<input type='hidden' name='sesion' value='" + sesion + "' />");
		out.println("<input type='submit' class='enviar' name='searchBtn' title='search' value='Buscar'>");
		out.println("</form>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class='clear'></div>");
		out.println("<br /><br />");
		out.println("<div class='contenedor'>");
		out.println("<div class='col-100 tablet-100 movil-100 alinearCentro'>");
		if (sesion != null) {
			out.println("<span>Logeado como <b>" + usuario + ".</b></span> ");
			out.println("<span><a href='index'>Cerrar sesión</a></span>");
		} else {
			out.println("<span>Si desea logear en MyAnimeList, haga click <a href='loginMyAnimeList.jsp'>aquí</a></span>");
		}
		out.println("<br />");
		out.println("<form action='/buscadorPerfil' method='post'>");
		out.println("<input type='hidden' name='usuario' value='" + usuarioCodificado + "' />");
		out.println("<input type='hidden' name='clave' value='" + claveCodificada + "' />");
		out.println("<span>Si desea buscar un perfil, haga click <input class='enlace' type='submit' value = 'aquí'></input></span>");
		out.println("</form'><br><br>");
		out.println("<a class='api' href='/docs/index.html'> Ver documentación de la API</a>");
		out.println("</div>");
		out.println("<video id='video' autoplay='autoplay' muted loop>");
		out.println("<source src='/videos/amv.mp4' type='video/mp4'></source>");
		out.println("</video>");
		out.println("</div>");
		out.println("</body></html>");

		log.log(Level.FINE, "Index procesado con éxito.");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}
}
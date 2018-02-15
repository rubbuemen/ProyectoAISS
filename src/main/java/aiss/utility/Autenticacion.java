package aiss.utility;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.DatatypeConverter;

public class Autenticacion implements ClientRequestFilter {

	private String usuario;
	private String clave;

	// Constructor de la clase para realizar la autenticación
	public Autenticacion(String usuario, String clave) {
		this.usuario = usuario;
		this.clave = clave;
	}
	
	// Método auxiliar para obtener el formato de la autenticación correctamente
	private String getBasicAuthentication() {
		String token = this.usuario + ":" + this.clave;
		try {
			return "BASIC " + DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException ex) {
			throw new IllegalStateException("No se puede codificar en UTF-8", ex);
		}
	}

	// Metodo para añadir la autenticación a las cabeceras de la web
	public void filter(ClientRequestContext requestContext) throws IOException {
		MultivaluedMap<String, Object> headers = requestContext.getHeaders();
		String basicAuthentication = getBasicAuthentication();
		headers.add("Authorization", basicAuthentication);
	}
}
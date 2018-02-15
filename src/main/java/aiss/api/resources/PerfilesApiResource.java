package aiss.api.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.MyAnime;
import aiss.model.Perfil;
import aiss.model.repository.MarcRepository;
import aiss.model.repository.MarcRepositoryImplement;

@Path("/perfiles")
public class PerfilesApiResource {
	
public static PerfilesApiResource _instance = null;
	
	MarcRepository repository;

	public PerfilesApiResource() {
		repository = MarcRepositoryImplement.getInstance();
	}

	public static PerfilesApiResource getInstance() {
		if (_instance == null) {
			_instance = new PerfilesApiResource();
		}
		return _instance;
	}
	
	// Método que devuelve todos los perfiles
	@GET
	@Produces("application/json")
	public Collection<Perfil> getAllPerfiles() {
		return repository.getAllPerfiles();
	}
	
	// Método que devuelve un perfil con una determinada id
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Perfil getPerfilById(@PathParam("id") String id) {
		Perfil perfil = repository.getPerfilById(id);
		if (perfil == null) {
			throw new BadRequestException("El perfil solicitado no existe.");
		}
		return perfil;
	}
	
	// Método que devuelve un perfil de un determinado usuario
	@GET
	@Path("/usuario")
	@Produces("application/json")
	public Perfil getPerfilByUser(@QueryParam("user") String user) {
		Perfil perfil = repository.getAnimePerfilByNick(user);
		if (perfil == null) {
			throw new NotFoundException("No se encuentra ningún perfil.");
		}
		return perfil;
	}
	
	// Método que añade un perfil
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPerfil(@Context UriInfo uriInfo, Perfil perfil) {
		if (perfil.getId() == null || perfil.getId() == "") {
			throw new BadRequestException("El perfil no es válido");
		}
		repository.addPerfil(perfil);
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(perfil);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(perfil);
		return resp.build();
	}

	// Método que actualiza un perfil
	@PUT
	@Consumes("application/json")
	public Response updatePerfil(Perfil perfil) {
		Perfil oldPerfil = repository.getPerfilById(perfil.getId());
		if (oldPerfil == null) {
			throw new NotFoundException("El perfil con id="+ perfil.getId() +" no fue encontrado");			
		}
		else {
			repository.updatePerfil(perfil);
		}
		return Response.noContent().build();
	}

	// Método que elimina un perfil
	@DELETE
	@Path("/{id}")
	public Response removePerfil(@PathParam("id") String id) {
		Perfil perfil = repository.getPerfilById(id);
		if (perfil == null) {
			throw new NotFoundException("El perfil con id="+ id +" no fue encontrado");			
		}
		else {
			repository.deletePerfil(id);
		}
		return Response.noContent().build();
	}
	
	// Método que añade un anime a un perfil
	@POST
	@Path("/anime/{idPerfil}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addAnimeToMyList(@Context UriInfo uriInfo, @PathParam("idPerfil") String idPerfil, MyAnime anime) {
		if (idPerfil == null || idPerfil == "") {
			throw new BadRequestException("El anime no es válido");
		}
		repository.addAnimeToMyList(idPerfil, anime);
		ResponseBuilder resp = Response.created(uriInfo.getAbsolutePath());
		resp.entity(anime);   
		return resp.build();
	}

	// Método que actualiza un anime de un perfil
	@PUT
	@Path("/anime/{idPerfil}")
	@Consumes("application/json")
	public Response updateAnimeInMyList(@PathParam("idPerfil") String idPerfil, MyAnime anime) {
		MyAnime oldAnime = (MyAnime) repository.getAnimeById(anime.getId());
		if (oldAnime == null) {
			throw new NotFoundException("El anime no fue encontrado");			
		}
		else {
			repository.updateAnimeInMyList(idPerfil, anime);
		}
		return Response.noContent().build();
	}

	// Método que elimina un anime de un perfil
	@DELETE
	@Path("/anime/{idPerfil}/{idAnime}")
	@Consumes("application/json")
	public Response deleteAnimeInMyList(@PathParam("idPerfil") String idPerfil, @PathParam("idAnime") String idAnime) {
		if (idAnime == null) {
			throw new NotFoundException("El anime no fue encontrado");			
		}
		else {
			repository.deleteAnimeInMyList(idPerfil, idAnime);
		}
		return Response.noContent().build();
	}
}

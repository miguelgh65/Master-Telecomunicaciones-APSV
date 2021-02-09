package es.upm.dit.apsv.cris.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.upm.dit.apsv.cris.dao.PublicationDAOImplementation;
import es.upm.dit.apsv.cris.dao.ResearcherDAOImplementation;
import es.upm.dit.apsv.cris.model.Publication;
import es.upm.dit.apsv.cris.model.Researcher;

@Path("/Researchers")
public class ResearcherResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Researcher> readAll(){
		return ResearcherDAOImplementation.getInstance().readAll();
	}
	
	@GET
	@Path("{id}/Publications")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Publication> readAllPublications(@PathParam("id") String id){
		return PublicationDAOImplementation.getInstance().readAllPublications(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Researcher rnew) throws URISyntaxException {
	    Researcher r = ResearcherDAOImplementation.getInstance().create(rnew);
	    URI uri = new URI("/CRISSERVICE/rest/Researchers/" + r.getId());
	    return Response.created(uri).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("id") String id) {
	    Researcher r = ResearcherDAOImplementation.getInstance().read(id);
	    if (r == null)
	      return Response.status(Response.Status.NOT_FOUND).build();
	    return Response.ok(r, MediaType.APPLICATION_JSON).build();
	}        

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response update(@PathParam("id") String id, Researcher r) {
	    Researcher rold = ResearcherDAOImplementation.getInstance().read(id);
	    if ((rold == null) || (! rold.getId().contentEquals(r.getId())))
	      return Response.notModified().build();
	    ResearcherDAOImplementation.getInstance().update(r);
	    return Response.ok().build();                
	}
       

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") String  id) {
	    Researcher rold = ResearcherDAOImplementation.getInstance().read(id);
	    if (rold == null)
	        return Response.notModified().build();
	    ResearcherDAOImplementation.getInstance().delete(rold);
	    return Response.ok().build();
	}
      

	@GET
	@Path("/email")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readByEmail(@QueryParam("email") String email) {
	   Researcher r = ResearcherDAOImplementation.getInstance().readByEmail(email);
	   if (r == null)
	     return Response.status(Response.Status.NOT_FOUND).build();
	   return Response.ok(r, MediaType.APPLICATION_JSON).build();
	}

}

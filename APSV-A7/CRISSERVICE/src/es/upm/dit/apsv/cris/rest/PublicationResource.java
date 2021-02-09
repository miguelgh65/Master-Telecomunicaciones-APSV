package es.upm.dit.apsv.cris.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;

import es.upm.dit.apsv.cris.dao.PublicationDAOImplementation;
import es.upm.dit.apsv.cris.model.Publication;


@Path("/Publications")
public class PublicationResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Publication> readAll(){
		return PublicationDAOImplementation.getInstance().readAll();
	}
		
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Publication rnew) throws URISyntaxException {
	    Publication r = PublicationDAOImplementation.getInstance().create(rnew);
	    URI uri = new URI("/CRISSERVICE/rest/Publications/" + r.getId());
	    return Response.created(uri).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("id") String id) {
	    Publication r = PublicationDAOImplementation.getInstance().read(id);
	    if (r == null)
	      return Response.status(Response.Status.NOT_FOUND).build();
	    return Response.ok(r, MediaType.APPLICATION_JSON).build();
	}        

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response update(@PathParam("id") String id, Publication r) {
	    Publication rold = PublicationDAOImplementation.getInstance().read(id);
	    if ((rold == null) || (! rold.getId().contentEquals(r.getId())))
	      return Response.notModified().build();
	    PublicationDAOImplementation.getInstance().update(r);
	    return Response.ok().build();                
	}
       

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") String  id) {
	    Publication rold = PublicationDAOImplementation.getInstance().read(id);
	    if (rold == null)
	        return Response.notModified().build();
	    PublicationDAOImplementation.getInstance().delete(rold);
	    return Response.ok().build();
	}
    
	@GET
	@Path("{id}/UpdateCiteNumber")
	public Response updateCiteNumber(@PathParam("id") String id) {
	String APIKey = "e7886536a56fb04b58d03d613a9cde99";
	Publication p = PublicationDAOImplementation.getInstance().read(id);
	if (p == null)
	  return Response.status(Response.Status.NOT_FOUND).build();
	Client client = ClientBuilder.newClient(new ClientConfig());
	JsonObject o = client.register(JsonProcessingFeature.class).target(
	                "https://api.elsevier.com/content/search/scopus?query=SCOPUS-ID(" + id + ")&field=citedby-count")
	                .request().accept(MediaType.APPLICATION_JSON)
	                .header("X-ELS-APIKey", APIKey)
	                .get(JsonObject.class);
	
	JsonArray a =((JsonArray) ((JsonObject) o.get("search-results")).get("entry"));
	JsonObject oo = a.getJsonObject(0);
	int ncites = Integer.parseInt(oo.get("citedby-count").toString().replaceAll("\"", ""));
	p.setCiteCount(ncites);
	PublicationDAOImplementation.getInstance().update(p);
	return Response.ok().build();
	}
}

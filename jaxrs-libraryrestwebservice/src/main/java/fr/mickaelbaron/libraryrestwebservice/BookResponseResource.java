package fr.mickaelbaron.libraryrestwebservice;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
@Path("/responsebooks")
public class BookResponseResource {

	@GET
	@Path("ok")
	public Response getBook() {
		System.out.println("BookResponseResource.getBook()");
		
		return Response.status(Response.Status.OK).entity("Java For Life").build();
	}
	
	@GET
	@Path("ok/without_response")
	public String getBookWithoutResponse() {
		System.out.println("BookResponseResource.getBookWithoutResponse()");
		
		return "Java For Life";
	}
	
	@GET
	@Path("ok/headers")
	public Response getBookWithHeaders() {
		System.out.println("BookResponseResource.getBookWithHeaders()");

		return Response.status(Response.Status.OK).entity("Java For Life").header("param1", "value1").build();
	}
	
	@GET
	@Path("ok/json")
	public Response getBookJSON() {
		System.out.println("BookResponseResource.getBookJSON()");
		
		Book current = new Book();
		current.setIsbn("1-111111-11");
		current.setName("Harry");
		
		return Response.status(Response.Status.OK).entity(current).type(MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("ok/json_annotation")
	public Response getBookJSONAnnotation() {
		System.out.println("BookResponseResource.getBookJSONAnnotation()");		
		
		Book current = new Book();
		current.setIsbn("1-111111-11");
		current.setName("Harry");
		
		return Response.status(Response.Status.OK).entity(current).build();
	}
	
	@GET
	@Path("error/webapplicationexception")
	public String getBookWithWebApplicationException(@QueryParam("id") String id) {
		System.out.println("BookResponseResource.getBookWithWebApplicationException()");

		if (null == id) {
			// throw new WebApplicationException(Status.BAD_REQUEST);
			throw new BadRequestException();
		}
		
		return "Java For Life" + id;
	}
	
	@GET
	@Path("error")
	public Response getBookWithError(@QueryParam("id") String id) {
		System.out.println("BookResponseResource.getBookWithError()");

		if (null == id) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		return Response.status(Response.Status.OK).entity("Java For Life" + id).build();
	}
}

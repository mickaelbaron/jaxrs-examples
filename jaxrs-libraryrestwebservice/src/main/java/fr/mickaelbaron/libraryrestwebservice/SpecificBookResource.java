package fr.mickaelbaron.libraryrestwebservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
public class SpecificBookResource {
	
	@GET
	@Path("{id}")
	public String getSpecificBookById(@PathParam("id") int id) {
		System.out.println("SpecificBookResource.getSpecificBookById()");
		
		return ".NET platform is Bad";
	}
}

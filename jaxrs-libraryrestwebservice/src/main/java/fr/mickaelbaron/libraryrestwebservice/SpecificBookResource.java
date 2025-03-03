package fr.mickaelbaron.libraryrestwebservice;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

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

package fr.mickaelbaron.helloworldrestwebservice;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
@Path("/hello")
@Produces(MediaType.TEXT_PLAIN)
public class HelloWorldResource {

	@GET
	public String getHelloWorld() {
		return "Hello World from text/plain";
	}
}

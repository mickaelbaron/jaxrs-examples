package fr.mickaelbaron.helloworldrestwebservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

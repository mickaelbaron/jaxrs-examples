package fr.mickaelbaron.helloworldrestwebservice;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
@ApplicationPath("api2")
public class HelloWorldPathApplication extends ResourceConfig {

	public HelloWorldPathApplication() {
		this.packages("fr.mickaelbaron.helloworldrestwebservice");
	}
}

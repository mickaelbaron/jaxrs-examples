package fr.mickaelbaron.helloworldrestwebservice;

import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
@ApplicationPath("api2")
public class HelloWorldPathApplication extends ResourceConfig {

	public HelloWorldPathApplication() {
		this.packages("fr.mickaelbaron.helloworldrestwebservice");
	}
}

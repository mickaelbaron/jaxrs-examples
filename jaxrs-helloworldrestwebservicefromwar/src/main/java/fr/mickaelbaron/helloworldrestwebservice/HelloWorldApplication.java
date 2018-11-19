package fr.mickaelbaron.helloworldrestwebservice;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
public class HelloWorldApplication extends ResourceConfig {

	public HelloWorldApplication() {
		this.packages("fr.mickaelbaron.helloworldrestwebservice");
	}
}

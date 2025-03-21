package fr.mickaelbaron.helloworldrestwebservice;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.core.UriBuilder;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
public class HelloWorldRestWebServiceLauncher {

	public static final URI BASE_URI = getBaseURI();

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/helloworldrestwebservice/api/").port(9992).build();
	}

	public static void main(String[] args) throws IOException {
		ResourceConfig resourceConfig = new ResourceConfig();
		resourceConfig.registerClasses(HelloWorldResource.class);
		resourceConfig.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, Level.WARNING.getName());

		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig);
		server.start();

		System.out.println(String.format(
				"Jersey app started with WADL available at " + "%sapplication.wadl\nHit enter to stop it...", BASE_URI,
				BASE_URI));

		System.in.read();
		server.shutdownNow();
	}
}

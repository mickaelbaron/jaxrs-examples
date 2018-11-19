package fr.mickaelbaron.libraryrestwebservice;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class LibraryRestWebServiceClientLauncher {

	public static final URI BASE_URI = getBaseURI();

	private static URI getBaseURI() {
     	return UriBuilder.fromUri("http://localhost/libraryrestwebservice/api/").port(9992).build();
	}

	public LibraryRestWebServiceClientLauncher() throws IOException {
		ResourceConfig resourceConfig = new ResourceConfig();
		resourceConfig.registerClasses(BookResource.class, BookContentResource.class);
		resourceConfig.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, Level.WARNING.getName());
		
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig);
		server.start();	
		
		Client client = ClientBuilder.newClient();
		String string = client.target(getBaseURI()).path("books/details/123").request(MediaType.TEXT_PLAIN).get(String.class);
		System.out.println(string);
		
		string = client.target(getBaseURI()).path("books/details/123").request(MediaType.TEXT_XML_TYPE).get(String.class);
		System.out.println(string);
		
		string = client.target(getBaseURI()).path("books/details/123").request(MediaType.TEXT_HTML_TYPE).get(String.class);
		System.out.println(string);
		
		String myBookString = "Le Livre";
		Response create = client.target(getBaseURI()).path("books").request().post(Entity.entity(myBookString, MediaType.TEXT_PLAIN_TYPE));
		System.out.println(create.getStatusInfo().getReasonPhrase());
		
		Form form = new Form();
		form.param("name", myBookString);
		create = client.target(getBaseURI()).path("books/createfromform").request(MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(form));
		System.out.println(create.getStatusInfo().getReasonPhrase());
		
		Book current = client.target(getBaseURI()).path("contentbooks/json").request(MediaType.APPLICATION_JSON_TYPE).get(Book.class);
		System.out.println(current);
		
		List<Book> books = client.target(getBaseURI()).path("contentbooks/").request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Book>>(){});
		System.out.println(books.size());
		
		Book myBook = new Book();
		myBook.setIsbn("1-111111-11");
		myBook.setName("harry");
		Response put = client.target(getBaseURI()).path("contentbooks/json").request().put(Entity.entity(myBook, MediaType.APPLICATION_JSON_TYPE));
		System.out.println(put.getStatusInfo().getReasonPhrase());
		
		server.shutdownNow();				
	}
	
	public static void main(String[] args) throws IOException {
		new LibraryRestWebServiceClientLauncher();
	}	
}

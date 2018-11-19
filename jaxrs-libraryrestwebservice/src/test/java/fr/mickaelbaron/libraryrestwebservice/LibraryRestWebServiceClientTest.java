package fr.mickaelbaron.libraryrestwebservice;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LibraryRestWebServiceClientTest {

	private HttpServer server;
	
	public static final URI BASE_URI = getBaseURI();

	private static URI getBaseURI() {
     	return UriBuilder.fromUri("http://localhost/libraryrestwebservice/api/").port(9992).build();
	}
	
	@Before
	public void setup() throws IOException {
		ResourceConfig resourceConfig = new ResourceConfig();
		resourceConfig.registerClasses(BookResource.class, BookContentResource.class);
		
		server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig);
		server.start();	
	}
	
	@After
	public void tearDown() {
		server.shutdownNow();	
	}

	@Test
	public void LibraryRestWebServiceClientLauncher() throws IOException {
		Client client = ClientBuilder.newClient();
		String string = client.target(getBaseURI()).path("books/details/123").request(MediaType.TEXT_PLAIN).get(String.class);
		Assert.assertEquals("Ce livre est une introduction sur la vie", string);
		
		string = client.target(getBaseURI()).path("books/details/123").request(MediaType.TEXT_XML_TYPE).get(String.class);
		Assert.assertEquals("<?xml version=\"1.0\"?><details>Ce livre est une introduction sur la vie</details>", string);
		
		string = client.target(getBaseURI()).path("books/details/123").request(MediaType.TEXT_HTML_TYPE).get(String.class);
		Assert.assertEquals("<html><title>Details</title><body><h1>Ce livre est une introduction sur la vie</h1></body></html>", string);
		
		String myBookString = "Le Livre";
		Response create = client.target(getBaseURI()).path("books").request().post(Entity.entity(myBookString, MediaType.TEXT_PLAIN_TYPE));
		Assert.assertEquals(200, create.getStatus());
		
		Form form = new Form();
		form.param("name", myBookString);
		create = client.target(getBaseURI()).path("books/createfromform").request(MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(form));
		Assert.assertEquals(200, create.getStatus());
		
		Book current = client.target(getBaseURI()).path("contentbooks/json").request(MediaType.APPLICATION_JSON_TYPE).get(Book.class);
		Assert.assertEquals("Harry", current.toString());
		
		Book myBook = new Book();
		myBook.setIsbn("1-111111-11");
		myBook.setName("harry");
		Response put = client.target(getBaseURI()).path("contentbooks/json").request().put(Entity.entity(myBook, MediaType.APPLICATION_JSON_TYPE));
		Assert.assertEquals(204, put.getStatus());
		
		string = client.target(getBaseURI()).path("books/headerparameters").request().header("name", "harry").header("isbn", "1-111111-11").header("isExtended",true).get(String.class);
		Assert.assertEquals("harry 1-111111-11 true", string);
	}
}

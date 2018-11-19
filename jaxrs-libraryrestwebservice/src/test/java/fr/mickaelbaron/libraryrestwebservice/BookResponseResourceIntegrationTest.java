package fr.mickaelbaron.libraryrestwebservice;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
public class BookResponseResourceIntegrationTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(BookResponseResource.class);
	}

	@Test
	public void getBookTest() {
		// Given

		// When
		Response response = target("/responsebooks/ok").request().get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());

		String content = response.readEntity(String.class);
		Assert.assertEquals("Content of ressponse is: ", "Java For Life", content);
	}

	@Test
	public void getBookWithHeadersTest() {
		// Given

		// When
		Response response = target("/responsebooks/ok/headers").request().get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		String content = response.readEntity(String.class);
		Assert.assertEquals("Content of ressponse is: ", "Java For Life", content);
		String headerString = response.getHeaderString("param1");
		Assert.assertNotNull(headerString);
		Assert.assertEquals("value1", headerString);
	}

	@Test
	public void getBookJSONTest() {
		// Given

		// When
		Response response = target("/responsebooks/ok/json").request().get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		Assert.assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());

		Book content = response.readEntity(Book.class);
		Assert.assertEquals("Content of ressponse is: ", "Harry", content.toString());
	}
	
	@Test
	public void getBookJSONAnnotationTest() {
		// Given

		// When
		Response response = target("/responsebooks/ok/json_annotation").request().get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		Assert.assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());

		Book content = response.readEntity(Book.class);
		Assert.assertEquals("Content of ressponse is: ", "Harry", content.toString());
	}
	
	@Test
	public void getBookWithWebApplicationExceptionTest() {
		// Given
		
		// When
		Response response = target("/responsebooks/error/webapplicationexception").request().get();
		
		// Then
		Assert.assertEquals("Http Response should be 400: ", Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void getBookWithErrorTest() {
		// Given
		
		// When
		Response response = target("/responsebooks/error").request().get();
		
		// Then
		Assert.assertEquals("Http Response should be 400: ", Status.BAD_REQUEST.getStatusCode(), response.getStatus());	
	}
}

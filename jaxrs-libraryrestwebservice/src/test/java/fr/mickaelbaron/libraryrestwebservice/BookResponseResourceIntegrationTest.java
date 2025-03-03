package fr.mickaelbaron.libraryrestwebservice;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

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
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");

		String content = response.readEntity(String.class);
		Assertions.assertEquals("Java For Life", content, "Content of ressponse is: ");
	}

	@Test
	public void getBookWithHeadersTest() {
		// Given

		// When
		Response response = target("/responsebooks/ok/headers").request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");
		String content = response.readEntity(String.class);
		Assertions.assertEquals("Java For Life", content, "Content of ressponse is: ");
		String headerString = response.getHeaderString("param1");
		Assertions.assertNotNull(headerString);
		Assertions.assertEquals("value1", headerString);
	}

	@Test
	public void getBookJSONTest() {
		// Given

		// When
		Response response = target("/responsebooks/ok/json").request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");
		Assertions.assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());

		Book content = response.readEntity(Book.class);
		Assertions.assertEquals("Harry", content.toString(), "Content of ressponse is: ");
	}

	@Test
	public void getBookJSONAnnotationTest() {
		// Given

		// When
		Response response = target("/responsebooks/ok/json_annotation").request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");
		Assertions.assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());

		Book content = response.readEntity(Book.class);
		Assertions.assertEquals("Harry", content.toString(), "Content of ressponse is: ");
	}

	@Test
	public void getBookWithWebApplicationExceptionTest() {
		// Given

		// When
		Response response = target("/responsebooks/error/webapplicationexception").request().get();

		// Then
		Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus(),
				"Http Response should be 400: ");
	}

	@Test
	public void getBookWithErrorTest() {
		// Given

		// When
		Response response = target("/responsebooks/error").request().get();

		// Then
		Assertions.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus(),
				"Http Response should be 400: ");
	}
}

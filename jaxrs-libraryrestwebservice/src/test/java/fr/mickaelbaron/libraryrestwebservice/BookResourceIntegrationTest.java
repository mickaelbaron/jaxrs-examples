package fr.mickaelbaron.libraryrestwebservice;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
public class BookResourceIntegrationTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(BookResource.class);
	}

	@Test
	public void getBooksTest() {
		// Given

		// When
		Response response = target("/books/").request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");

		String content = response.readEntity(String.class);
		Assertions.assertEquals("Cuisine et moi / Java 18", content, "Content of ressponse is: ");
	}

	@Test
	public void getBorrowedBooksTest() {
		// Given

		// When
		Response response = target("/books/borrowed").request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");

		String content = response.readEntity(String.class);
		Assertions.assertEquals("Java in 5 lessons / Java VS .NET", content, "Content of ressponse is: ");
	}

	@Test
	public void getBookByIdTest() {
		// Given
		String pathParam = "123";

		// When
		Response response = target("/books").path(pathParam).request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");

		String content = response.readEntity(String.class);
		Assertions.assertEquals("Java For Life 123", content, "Content of ressponse is: ");
	}

	@Test
	public void getBookByNameAndEditorTest() {
		// Given
		String namePathParam = "sc2";
		String editorPathParam = "oreilly";

		// When
		Response response = target("/books/name-" + namePathParam + "-editor-" + editorPathParam).request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");

		String content = response.readEntity(String.class);
		Assertions.assertEquals("Starcraft 2 for Dummies (Name:sc2 - Editor:oreilly)",
				content, "Content of ressponse is: ");
	}

	@Test
	public void getBookEditorByIdTest() {
		// Given
		String megaPathParam = "123/path1/path2";

		// When
		Response response = target("/books/" + megaPathParam).path("editor").request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");

		String content = response.readEntity(String.class);
		Assertions.assertEquals("OReilly", content, "Content of ressponse is: ");

	}

	@Test
	public void getOriginalBookByIdTest() {
		// Given
		String megaPathParam = "123/path1/path2";

		// When
		Response response = target("/books/original/").path(megaPathParam).request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");

		String content = response.readEntity(String.class);
		Assertions.assertEquals("Science will reveal the truth", content, "Content of ressponse is: ");
	}

	@Test
	public void getSpecificBookTest() {
		// Given
		String pathParam = "123";

		// When
		Response response = target("/books/specific/").path(pathParam).request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");

		String content = response.readEntity(String.class);
		Assertions.assertEquals(".NET platform is Bad", content, "Content of ressponse is: ");
	}

	@Test
	public void createBookTest() {
		// Given
		String bookContent = "Le livre";

		// When
		Response response = target("/books").request().post(Entity.text(bookContent));

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");

		String content = response.readEntity(String.class);
		Assertions.assertEquals("Le livre", content, "Content of ressponse is: ");
	}

	@Test
	public void updateBookByIdTest() {
		// Given
		String pathParam = "123";

		// When
		Response response = target("/books").path(pathParam).request().put(Entity.text("Le livre"));

		// Then
		Assertions.assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus(),
				"Http Response should be 204: ");
	}

	@Test
	public void deleteBookByIdTest() {
		// Given
		String pathParam = "123";

		// When
		Response response = target("/books").path(pathParam).request().delete();

		// Then
		Assertions.assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus(),
				"Http Response should be 204: ");
	}

	@Test
	public void getQueryParameterBookTest() {
		// Given
		String name = "harry";
		String isbn = "1-111111-11";
		boolean isExtended = true;

		// When
		Response response = target("/books/queryparameters").queryParam("name", name).queryParam("isbn", isbn)
				.queryParam("isExtended", isExtended).request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");
	}

	@Test
	public void createBookFromFormTest() {
		// Given
		Form form = new Form();
		form.param("name", "untest");

		// When
		Response response = target("/books/createfromform").request(MediaType.APPLICATION_FORM_URLENCODED)
				.post(Entity.form(form));

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");
		String content = response.readEntity(String.class);
		Assertions.assertEquals("untest", content, "Content of ressponse is: ");
	}

	@Test
	public void getHeaderParameterBookTest() {
		// Given
		String name = "harry";
		String isbn = "1-111111-11";
		boolean isExtended = true;

		// When
		Response response = target("/books/headerparameters").request().header("name", name).header("isbn", isbn)
				.header("isExtended", isExtended).get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");
		String content = response.readEntity(String.class);
		Assertions.assertEquals("harry 1-111111-11 true", content, "Content of ressponse is: ");
	}

	@Test
	public void getInformationFromUriInfoTest() {
		// Given
		String pathParam = "test";

		// When
		Response response = target("/books/informationfromuriinfo/").path(pathParam).request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");
	}

	@Test
	public void getInformationFromHttpHeadersTest() {
		// Given

		// When
		Response response = target("/books/informationfromhttpheaders/").request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");
	}

	@Test
	public void getDetailTextBookIdTest() {
		// Given
		String pathName = "12";

		// When
		Response response = target("/books/details/").path(pathName).request(MediaType.TEXT_PLAIN).get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");
		String content = response.readEntity(String.class);
		Assertions.assertEquals("Ce livre est une introduction sur la vie", content, "Content of ressponse is: ");
		Assertions.assertEquals(MediaType.TEXT_PLAIN_TYPE, response.getMediaType());
	}

	@Test
	public void getDetailXMLBookIdTest() {
		// Given
		String pathName = "12";

		// When
		Response response = target("/books/details/").path(pathName).request(MediaType.TEXT_XML).get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");
		String content = response.readEntity(String.class);
		Assertions.assertEquals(
				"<?xml version=\"1.0\"?><details>Ce livre est une introduction sur la vie</details>", content,
				"Content of ressponse is: ");
		Assertions.assertEquals(MediaType.TEXT_XML_TYPE, response.getMediaType());
	}

	@Test
	public void getDetailHTMLBookIdTest() {
		// Given
		String pathName = "12";

		// When
		Response response = target("/books/details/").path(pathName).request(MediaType.TEXT_HTML).get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");
		String content = response.readEntity(String.class);
		Assertions.assertEquals(
				"<html><title>Details</title><body><h1>Ce livre est une introduction sur la vie</h1></body></html>",
				content, "Content of ressponse is: ");
		Assertions.assertEquals(MediaType.TEXT_HTML_TYPE, response.getMediaType());
	}
}

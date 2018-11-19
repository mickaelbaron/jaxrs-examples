package fr.mickaelbaron.libraryrestwebservice;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
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
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());

		String content = response.readEntity(String.class);
		Assert.assertEquals("Content of ressponse is: ", "Cuisine et moi / Java 18", content);
	}

	@Test
	public void getBorrowedBooksTest() {
		// Given

		// When
		Response response = target("/books/borrowed").request().get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());

		String content = response.readEntity(String.class);
		Assert.assertEquals("Content of ressponse is: ", "Java in 5 lessons / Java VS .NET", content);
	}

	@Test
	public void getBookByIdTest() {
		// Given
		String pathParam = "123";

		// When
		Response response = target("/books").path(pathParam).request().get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());

		String content = response.readEntity(String.class);
		Assert.assertEquals("Content of ressponse is: ", "Java For Life 123", content);
	}

	@Test
	public void getBookByNameAndEditorTest() {
		// Given
		String namePathParam = "sc2";
		String editorPathParam = "oreilly";

		// When
		Response response = target("/books/name-" + namePathParam + "-editor-" + editorPathParam).request().get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());

		String content = response.readEntity(String.class);
		Assert.assertEquals("Content of ressponse is: ", "Starcraft 2 for Dummies (Name:sc2 - Editor:oreilly)",
				content);
	}

	@Test
	public void getBookEditorByIdTest() {
		// Given
		String megaPathParam = "123/path1/path2";

		// When
		Response response = target("/books/" + megaPathParam).path("editor").request().get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());

		String content = response.readEntity(String.class);
		Assert.assertEquals("Content of ressponse is: ", "OReilly", content);

	}

	@Test
	public void getOriginalBookByIdTest() {
		// Given
		String megaPathParam = "123/path1/path2";

		// When
		Response response = target("/books/original/").path(megaPathParam).request().get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());

		String content = response.readEntity(String.class);
		Assert.assertEquals("Content of ressponse is: ", "Science will reveal the truth", content);
	}

	@Test
	public void getSpecificBookTest() {
		// Given
		String pathParam = "123";

		// When
		Response response = target("/books/specific/").path(pathParam).request().get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());

		String content = response.readEntity(String.class);
		Assert.assertEquals("Content of ressponse is: ", ".NET platform is Bad", content);
	}

	@Test
	public void createBookTest() {
		// Given
		String bookContent = "Le livre";

		// When
		Response response = target("/books").request().post(Entity.text(bookContent));

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());

		String content = response.readEntity(String.class);
		Assert.assertEquals("Content of ressponse is: ", "Le livre", content);
	}

	@Test
	public void updateBookByIdTest() {
		// Given
		String pathParam = "123";

		// When
		Response response = target("/books").path(pathParam).request().put(Entity.text("Le livre"));

		// Then
		Assert.assertEquals("Http Response should be 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteBookByIdTest() {
		// Given
		String pathParam = "123";

		// When
		Response response = target("/books").path(pathParam).request().delete();

		// Then
		Assert.assertEquals("Http Response should be 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
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
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
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
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		String content = response.readEntity(String.class);
		Assert.assertEquals("Content of ressponse is: ", "untest", content);
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
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		String content = response.readEntity(String.class);
		Assert.assertEquals("Content of ressponse is: ", "harry 1-111111-11 true", content);
	}

	@Test
	public void getInformationFromUriInfoTest() {
		// Given
		String pathParam = "test";

		// When
		Response response = target("/books/informationfromuriinfo/").path(pathParam).request().get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void getInformationFromHttpHeadersTest() {
		// Given

		// When
		Response response = target("/books/informationfromhttpheaders/").request().get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void getDetailTextBookIdTest() {
		// Given
		String pathName = "12";
		
		// When
		Response response = target("/books/details/").path(pathName).request(MediaType.TEXT_PLAIN).get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		String content = response.readEntity(String.class);
		Assert.assertEquals("Content of ressponse is: ", "Ce livre est une introduction sur la vie", content);
		Assert.assertEquals(MediaType.TEXT_PLAIN_TYPE, response.getMediaType());
	}
	
	@Test
	public void getDetailXMLBookIdTest() {
		// Given
		String pathName = "12";
		
		// When
		Response response = target("/books/details/").path(pathName).request(MediaType.TEXT_XML).get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		String content = response.readEntity(String.class);
		Assert.assertEquals("Content of ressponse is: ", "<?xml version=\"1.0\"?><details>Ce livre est une introduction sur la vie</details>", content);
		Assert.assertEquals(MediaType.TEXT_XML_TYPE, response.getMediaType());
	}
	
	@Test
	public void getDetailHTMLBookIdTest() {
		// Given
		String pathName = "12";
		
		// When
		Response response = target("/books/details/").path(pathName).request(MediaType.TEXT_HTML).get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		String content = response.readEntity(String.class);
		Assert.assertEquals("Content of ressponse is: ", "<html><title>Details</title><body><h1>Ce livre est une introduction sur la vie</h1></body></html>", content);
		Assert.assertEquals(MediaType.TEXT_HTML_TYPE, response.getMediaType());
	}
}

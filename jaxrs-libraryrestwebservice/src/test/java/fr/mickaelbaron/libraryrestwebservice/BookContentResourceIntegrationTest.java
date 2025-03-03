package fr.mickaelbaron.libraryrestwebservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
public class BookContentResourceIntegrationTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(BookContentResource.class);
	}

	@Test
	public void updateContentBookWithInputStreamTest() throws FileNotFoundException {
		// Given

		// When
		Response response = target("/contentbooks/").path("inputstream").request()
				.put(Entity.entity(new FileInputStream("src/main/resources/sample.txt"), "application/octet-stream"));

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");
		String content = response.readEntity(String.class);
		Assertions.assertEquals(
				"Moira is one of the support heroes of Overwatch. She is a geneticist that secretly works for Talon and currently resides in Oasis.",
				content, "Content of ressponse is: ");
	}

	@Test
	public void getContentBookWithInputStreamTest() {
		// Given

		// When
		Response response = target("/contentbooks/").path("inputstream").request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");

		InputStream readEntity = response.readEntity(InputStream.class);
		Assertions.assertNotNull(readEntity);

	}

	@Test
	public void updateContentBookWithFileTest() throws FileNotFoundException {
		// Given

		// When
		Response response = target("/contentbooks/").path("file").request()
				.put(Entity.entity(new File("src/main/resources/sample.txt"), "application/octet-stream"));

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");
		String content = response.readEntity(String.class);
		Assertions.assertEquals(
				"Moira is one of the support heroes of Overwatch. She is a geneticist that secretly works for Talon and currently resides in Oasis.",
				content, "Content of ressponse is: ");
	}

	@Test
	public void getContentBookWithFileTest() {
		// Given

		// When
		Response response = target("/contentbooks/").path("file").request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");

		File readEntity = response.readEntity(File.class);
		Assertions.assertNotNull(readEntity);
	}

	@Test
	public void updateContentBookWithXMLTest() {
		// Given
		Book myBook = new Book();
		myBook.setIsbn("1-111111-11");
		myBook.setName("harry");

		// When
		Response response = target("/contentbooks").path("xml").request()
				.put(Entity.entity(myBook, MediaType.APPLICATION_XML_TYPE));

		// Then
		Assertions.assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus(),
				"Http Response should be 204: ");
	}

	@Test
	public void getContentBookWithXMLTest() {
		// Given

		// When
		Response response = target("/contentbooks").path("xml").request(MediaType.APPLICATION_XML).get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");
		Book currentBook = response.readEntity(Book.class);
		Assertions.assertEquals("1-111111-11", currentBook.getIsbn());
		Assertions.assertEquals("Harry", currentBook.getName());
	}

	@Test
	public void updateContentBookWithJSONTest() {
		// Given
		Book myBook = new Book();
		myBook.setIsbn("1-111111-11");
		myBook.setName("harry");

		// When
		Response response = target("/contentbooks").path("json").request()
				.put(Entity.entity(myBook, MediaType.APPLICATION_JSON_TYPE));

		// Then
		Assertions.assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus(),
				"Http Response should be 204: ");
	}

	@Test
	public void updateContentBookWithPlainJSONTest() {
		// Given
		Book myBook = new Book();
		myBook.setIsbn("1-111111-11");
		myBook.setName("harry");

		// When
		Response response = target("/contentbooks").path("json").request()
				.put(Entity.json("{\"book_name\":\"Harry\",\"book_isbn\":\"1-111111-11\"}"));

		// Then
		Assertions.assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus(),
				"Http Response should be 204: ");
	}

	@Test
	public void getContentBookWithPlainJSONTest() {
		// Given

		// When
		Response response = target("/contentbooks").path("json").request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");
		Assertions.assertEquals("{\"book_name\":\"Harry\",\"book_isbn\":\"1-111111-11\"}",
				response.readEntity(String.class));
	}

	@Test
	public void getContentBookWithJSONAndXMLTest() {
		// Given

		// When
		String currentBook = target("/contentbooks").path("jsonxml").request(MediaType.APPLICATION_XML_TYPE)
				.get(String.class);

		// Then
		Assertions.assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><book><isbn>1-111111-11</isbn><name>Harry</name></book>",
				currentBook);

		// When
		currentBook = target("/contentbooks").path("jsonxml").request(MediaType.APPLICATION_JSON_TYPE)
				.get(String.class);

		// Then
		Assertions.assertEquals("{\"book_name\":\"Harry\",\"book_isbn\":\"1-111111-11\"}", currentBook);
	}

	@Test
	public void getContentBookWithJSONTest() {
		// Given

		// When
		Response response = target("/contentbooks").path("json").request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");
		Book currentBook = response.readEntity(Book.class);
		Assertions.assertEquals("1-111111-11", currentBook.getIsbn());
		Assertions.assertEquals("Harry", currentBook.getName());
	}

	@Test
	public void getContentBooksWithJSONTest() {
		// Given

		// When
		Response response = target("/contentbooks").request().get();

		// Then
		Assertions.assertEquals(Status.OK.getStatusCode(), response.getStatus(), "Http Response should be 200: ");
		List<Book> books = response.readEntity(new GenericType<List<Book>>() {
		});
		Assertions.assertEquals(2, books.size());
		Assertions.assertEquals("1-111111-11", books.get(0).getIsbn());
		Assertions.assertEquals("Harry", books.get(0).getName());
	}
}

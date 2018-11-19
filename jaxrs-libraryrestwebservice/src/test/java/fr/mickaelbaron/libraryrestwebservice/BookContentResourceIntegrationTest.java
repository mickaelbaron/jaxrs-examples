package fr.mickaelbaron.libraryrestwebservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
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
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		String content = response.readEntity(String.class);
		Assert.assertEquals("Content of ressponse is: ",
				"Moira is one of the support heroes of Overwatch. She is a geneticist that secretly works for Talon and currently resides in Oasis.",
				content);
	}

	@Test
	public void getContentBookWithInputStreamTest() {
		// Given

		// When
		Response response = target("/contentbooks/").path("inputstream").request().get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());

		InputStream readEntity = response.readEntity(InputStream.class);
		Assert.assertNotNull(readEntity);

	}

	@Test
	public void updateContentBookWithFileTest() throws FileNotFoundException {
		// Given

		// When
		Response response = target("/contentbooks/").path("file").request()
				.put(Entity.entity(new File("src/main/resources/sample.txt"), "application/octet-stream"));

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		String content = response.readEntity(String.class);
		Assert.assertEquals("Content of ressponse is: ",
				"Moira is one of the support heroes of Overwatch. She is a geneticist that secretly works for Talon and currently resides in Oasis.",
				content);
	}

	@Test
	public void getContentBookWithFileTest() {
		// Given

		// When
		Response response = target("/contentbooks/").path("file").request().get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());

		File readEntity = response.readEntity(File.class);
		Assert.assertNotNull(readEntity);
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
		Assert.assertEquals("Http Response should be 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void getContentBookWithXMLTest() {
		// Given

		// When
		Response response = target("/contentbooks").path("xml").request(MediaType.APPLICATION_XML).get();
		
		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		Book currentBook = response.readEntity(Book.class);
		Assert.assertEquals("1-111111-11", currentBook.getIsbn());
		Assert.assertEquals("Harry", currentBook.getName());
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
		Assert.assertEquals("Http Response should be 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
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
		Assert.assertEquals("Http Response should be 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}

	@Test
	public void getContentBookWithPlainJSONTest() {
		// Given

		// When
		Response response = target("/contentbooks").path("json").request().get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		Assert.assertEquals("{\"book_name\":\"Harry\",\"book_isbn\":\"1-111111-11\"}", response.readEntity(String.class));
	}

	@Test
	public void getContentBookWithJSONAndXMLTest() {
		// Given

		// When
		String currentBook = target("/contentbooks").path("jsonxml").request(MediaType.APPLICATION_XML_TYPE)
				.get(String.class);

		// Then
		Assert.assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><book><isbn>1-111111-11</isbn><name>Harry</name></book>",
				currentBook);

		// When
		currentBook = target("/contentbooks").path("jsonxml").request(MediaType.APPLICATION_JSON_TYPE)
				.get(String.class);

		// Then
		Assert.assertEquals("{\"book_name\":\"Harry\",\"book_isbn\":\"1-111111-11\"}", currentBook);
	}

	@Test
	public void getContentBookWithJSONTest() {
		// Given

		// When
		Response response = target("/contentbooks").path("json").request().get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		Book currentBook = response.readEntity(Book.class);
		Assert.assertEquals("1-111111-11", currentBook.getIsbn());
		Assert.assertEquals("Harry", currentBook.getName());
	}
	
	@Test
	public void getContentBooksWithJSONTest() {
		// Given

		// When
		Response response = target("/contentbooks").request().get();

		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		List<Book> books = response.readEntity(new GenericType<List<Book>>(){});
		Assert.assertEquals(2, books.size());
		Assert.assertEquals("1-111111-11", books.get(0).getIsbn());
		Assert.assertEquals("Harry", books.get(0).getName());		
	}
}

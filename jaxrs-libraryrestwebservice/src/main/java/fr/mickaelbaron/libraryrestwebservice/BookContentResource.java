package fr.mickaelbaron.libraryrestwebservice;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
@Path("/contentbooks")
public class BookContentResource {

	/**
	 * URI: /inputstream
	 */
	@Path("inputstream")
	@PUT
	public String updateContentBookWithInputStream(InputStream is) throws IOException {
		System.out.println("BookContentResource.updateContentBooksWithInputStream()");

		byte[] bytes = readFromStream(is);
		String input = new String(bytes);

		return input;
	}

	/**
	 * URI: /inputstream
	 */
	@Path("inputstream")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public InputStream getContentBookWithInputStream() throws FileNotFoundException {
		System.out.println("BookContentResource.getContentBooksWithInputStream()");

		FileInputStream fileInputStream = new FileInputStream("src/main/resources/sample.txt");
		return fileInputStream;
	}

	private byte[] readFromStream(InputStream stream) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int wasRead = 0;
		byte[] buffer = new byte[1024];
		while ((wasRead = stream.read(buffer, 0, buffer.length)) != -1) {
			baos.write(buffer, 0, wasRead);
		}

		return baos.toByteArray();
	}

	/**
	 * URI: /file
	 */
	@Path("file")
	@PUT
	public String updateContentBookWithFile(File file) throws IOException {
		System.out.println("BookContentResource.updateContentBooksWithFile()");

		byte[] bytes = readFromStream(new FileInputStream(file));
		String input = new String(bytes);

		return input;
	}

	/**
	 * URI: /file
	 */
	@Path("file")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public File getContentBookWithFile() {
		System.out.println("BookContentResource.getContentBooksWithFile()");

		File file = new File("src/main/resources/sample.txt");
		return file;
	}

	/**
	 * URI: /xml
	 */
	@Path("xml")
	@Consumes(MediaType.APPLICATION_XML)
	@PUT
	public void updateContentBookWithXML(Book current) throws IOException {
		System.out.println("BookContentResource.updateContentBooksWithXML()");

		System.out.println("Name: " + current.getName() + ", ISBN: " + current.getIsbn());
	}

	/**
	 * URI: /xml
	 */
	@Path("xml")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Book getContentBookWithXML() {
		System.out.println("BookContentResource.getContentBooksWithXML()");

		Book current = new Book();
		current.setIsbn("1-111111-11");
		current.setName("Harry");

		return current;
	}

	/**
	 * URI: /json
	 */
	@Path("json")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Book getContentBookWithJSON() {
		System.out.println("BookContentResource.getContentBooksWithJSON()");

		Book current = new Book();
		current.setIsbn("1-111111-11");
		current.setName("Harry");

		return current;
	}

	/**
	 * URI: /jsonxml
	 */
	@Path("jsonxml")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Book getContentBookWithJSONAndXML() {
		System.out.println("BookContentResource.getContentBooksWithJSONAndXML()");

		Book current = new Book();
		current.setIsbn("1-111111-11");
		current.setName("Harry");

		return current;
	}

	/**
	 * URI: /json
	 */
	@Path("json")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public void updateContentBookWithJSON(Book current) throws IOException {
		System.out.println("BookContentResource.updateContentBooksWithJSON()");

		System.out.println("Name: " + current.getName() + ", ISBN: " + current.getIsbn());
	}

	/**
	 * URI: /
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getContentBooksWithJSON() {
		System.out.println("BookContentResource.getBooksWithJSON()");

		Book book1 = new Book();
		book1.setIsbn("1-111111-11");
		book1.setName("Harry");

		Book book2 = new Book();
		book2.setIsbn("1-111111-11");
		book2.setName("Harry");

		return Arrays.asList(book1, book2);
	}
}

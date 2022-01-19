package fr.mickaelbaron.libraryrestwebservice;

import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.PathSegment;
import jakarta.ws.rs.core.UriInfo;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 * 
 *         This resource is intended to be used for pedagogic aspects. I'm not
 *         consider a book like a String content...
 * 
 */
@Path("/books")
public class BookResource {

	/**
	 * URI: /
	 */
	@GET
	public String getBooks() {
		System.out.println("BookResource.getBooks()");

		return "Cuisine et moi / Java 18";
	}

	/**
	 * URI: /borrowed
	 */
	@GET
	@Path("/borrowed")
	public String getBorrowedBooks() {
		System.out.println("BookResource.getBorrowedBooks()");

		return "Java in 5 lessons / Java VS .NET";
	}

	/**
	 * URI: /123
	 */
	@GET
	@Path("{id}")
	public String getBookById(@PathParam("id") int id) {
		System.out.println("BookResource.getBookById()");

		return "Java For Life " + id;
	}

	/**
	 * URI: /name-sc2-editor-oreilly
	 */
	@GET
	@Path("name-{name}-editor-{editor}")
	public String getBookByNameAndEditor(@PathParam("name") String name, @PathParam("editor") String editor) {
		System.out.println("BookResource.getBookByNameAndEditor()");

		return "Starcraft 2 for Dummies (Name:" + name + " - Editor:" + editor + ")";
	}

	/**
	 * URI: /123/path1/path2/editor
	 */
	@GET
	@Path("{id : .+}/editor")
	public String getBookEditorById(@PathParam("id") String id) {
		System.out.println("BookResource.getBookEditorById()");

		return "OReilly";
	}

	/**
	 * URI: /original/123/path1/path2
	 */
	@GET
	@Path("original/{id : .+}")
	public String getOriginalBookById(@PathParam("id") String id) {
		System.out.println("BookResource.getSpecificBookById()");

		return "Science will reveal the truth";
	}

	@Path("specific")
	public SpecificBookResource getSpecificBook() {
		System.out.println("BookResource.getSpecificBook()");

		return new SpecificBookResource();
	}

	/**
	 * URI : /
	 */
	@POST
	public String createBook(String livre) {
		System.out.println("BookResource.createBook()");

		return livre;
	}

	/**
	 * URI: /123
	 */
	@PUT
	@Path("{id}")
	public void updateBookById(@PathParam("id") int id, String livre) {
		System.out.println("BookResource.updateBookById()");
	}

	/**
	 * URI: /123
	 */
	@DELETE
	@Path("{id}")
	public void deleteBookById(@PathParam("id") int id) {
		System.out.println("BookResource.deleteBookById()");
	}

	/**
	 * URI: /queryparameters/?name=harry&isbn=1-111111-11&isExtended=true
	 */
	@GET
	@Path("queryparameters")
	public String getQueryParameterBook(@DefaultValue("all") @QueryParam("name") String name,
			@DefaultValue("?-???????-?") @QueryParam("isbn") String isbn,
			@DefaultValue("false") @QueryParam("isExtended") boolean isExtented) {
		System.out.println("BookResource.getQueryParameterBook()");

		return name + " " + isbn + " " + isExtented;
	}

	/**
	 * URI: /createfromform
	 */
	@POST
	@Path("createfromform")
	@Consumes("application/x-www-form-urlencoded")
	public String createBookFromForm(@FormParam("name") String name) {
		System.out.println("BookResource.createBookFromForm()");

		return name;
	}

	/**
	 * URI: /headerparameters
	 */
	@GET
	@Path("headerparameters")
	public String getHeaderParameterBook(@DefaultValue("all") @HeaderParam("name") String name,
			@DefaultValue("?-???????-?") @HeaderParam("isbn") String isbn,
			@DefaultValue("false") @HeaderParam("isExtended") boolean isExtented) {
		System.out.println("BookResource.getHeaderParameterBook()");

		return name + " " + isbn + " " + isExtented;
	}

	/**
	 * URI: /informationfromuriinfo/test
	 */
	@GET
	@Path("informationfromuriinfo/{name}")
	public String getInformationFromUriInfo(@Context UriInfo uriInfo, @PathParam("name") String name) {
		StringBuffer result = new StringBuffer();

		result.append("getPath(): " + uriInfo.getPath() + "\n");
		List<PathSegment> pathSegments = uriInfo.getPathSegments();
		result.append("getPathSegements(): ");
		for (PathSegment pathSegment : pathSegments) {
			result.append(pathSegment.getPath() + " ");
		}
		result.append("\n");

		result.append("getPathParameters(): ");
		MultivaluedMap<String, String> pathParameters = uriInfo.getPathParameters();
		Set<String> pathParametersSet = pathParameters.keySet();
		for (String currentParameter : pathParametersSet) {
			result.append(currentParameter + " ");
		}
		result.append("\n");

		result.append("getQueryParameters(): ");
		MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();
		Set<String> queryParametersSet = queryParameters.keySet();
		for (String currentParameter : queryParametersSet) {
			result.append(currentParameter + " ");
		}
		result.append("\n");

		result.append("getAbsolutePath(): " + uriInfo.getAbsolutePath() + "\n");
		result.append("getBaseUri(): " + uriInfo.getBaseUri() + "\n");
		result.append("getRequestUri(): " + uriInfo.getRequestUri() + "\n");

		System.out.println(result.toString());
		return result.toString();
	}

	/**
	 * URI: /informationfromhttpheaders/test
	 */
	@GET
	@Path("informationfromhttpheaders")
	public String getInformationFromHttpHeaders(@Context HttpHeaders httpheaders) {
		StringBuffer result = new StringBuffer("Cookies:");
		Map<String, Cookie> cookies = httpheaders.getCookies();
		Set<String> currentKeySet = cookies.keySet();
		for (String currentCookie : currentKeySet) {
			result.append(currentCookie + "\n");
		}

		result.append("RequestHeaders:" + "\n");
		MultivaluedMap<String, String> requestHeaders = httpheaders.getRequestHeaders();
		Set<String> requestHeadersSet = requestHeaders.keySet();
		for (String currentHeader : requestHeadersSet) {
			result.append(currentHeader + ": " + requestHeaders.get(currentHeader) + "\n");
		}

		System.out.println(result.toString());
		return result.toString();
	}

	/**
	 * URI: /details/12
	 */
	@GET
	@Path("details/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getDetailTextBookId(@PathParam("id") String id) {
		System.out.println("BookResource.getDetailTextBookId()");

		return "Ce livre est une introduction sur la vie";
	}

	/**
	 * URI: /details/12
	 */
	@GET
	@Path("details/{id}")
	@Produces(MediaType.TEXT_XML)
	public String getDetailXMLBookId(@PathParam("id") String id) {
		System.out.println("BookResource.getDetailXMLBookId()");

		return "<?xml version=\"1.0\"?>" + "<details>Ce livre est une introduction sur la vie" + "</details>";
	}

	/**
	 * URI: /details/12
	 */
	@GET
	@Path("details/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String getDetailHTMLBookId(@PathParam("id") String id) {
		System.out.println("BookResource.getDetailHTMLBookId()");

		return "<html>" + "<title>" + "Details" + "</title>" + "<body><h1>" + "Ce livre est une introduction sur la vie"
				+ "</h1></body>" + "</html>";
	}
}

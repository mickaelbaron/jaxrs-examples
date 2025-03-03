package fr.mickaelbaron.libraryrestwebservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
@XmlRootElement(name = "book")
@JsonPropertyOrder({ "name", "isbn" })
public class Book {
	@JsonProperty("book_name")
	protected String name;

	@JsonProperty("book_isbn")
	protected String isbn;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String toString() {
		return name;
	}
}

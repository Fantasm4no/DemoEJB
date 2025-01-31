package model;

import java.io.Serializable;

public class Book implements Serializable {
	
	 private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String author;
	private String isbn;
	private String publisher;
	private Integer publicationYear;
	
	public Book(String title, String author, String isbn, String publisher, Integer publicationYear) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
	}
	
	public Long getId() { return id;	}
	public void setId(Long id) { this.id = id; }
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	public String getAuthor() { return author; }
	public void setAuthor(String author) { this.author = author; }
	
	public String getIsbn() { return isbn; }
	public void setIsbn(String isbn) { this.isbn = isbn; }
	
	public String getPublisher() { return publisher; }
	public void setPublisher(String publisher) { this.publisher = publisher; }
	
	public Integer getPublicationYear() { return publicationYear; }
	public void setPublicationYear(Integer publicationYear) { this.publicationYear = publicationYear; }
	
	
}

package service;

import java.util.List;

import model.Book;

public interface BookRegistrationRemote {
	
	void register(Book book) throws Exception;
	
	List<Book> listBooks() throws Exception;

}

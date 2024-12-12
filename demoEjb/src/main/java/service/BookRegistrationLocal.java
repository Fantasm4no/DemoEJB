package service;

import java.util.List;

import jakarta.ejb.Local;
import model.Book;

@Local
public interface BookRegistrationLocal {
	void register(Book book);
	List<Book> listBooks();
}

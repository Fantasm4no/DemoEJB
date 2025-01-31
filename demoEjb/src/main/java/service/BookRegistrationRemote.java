package service;

import java.util.List;

import jakarta.ejb.Remote;
import model.Book;

@Remote
public interface BookRegistrationRemote {
	void register(Book book);
	List<Book> listBooks();
}

package service;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Book;

@Stateless
public class BookRegistration implements BookRegistrationLocal, BookRegistrationRemote{
	
	@PersistenceContext
    private EntityManager em;

    @Override
    public void register(Book book) {
        em.persist(book);
    }
    
    @Override
    public List<Book> listBooks() {
        // Consulta los libros desde la base de datos
        List<Book> books = em.createQuery("SELECT b FROM Book b", Book.class).getResultList();

        // Imprimir los detalles de cada libro en la consola del servidor
        for (Book book : books) {
            System.out.println("Book:");
            System.out.println("  Title: " + book.getTitle());
            System.out.println("  Author: " + book.getAuthor());
            System.out.println("  ISBN: " + book.getIsbn());
            System.out.println("  Publisher: " + book.getPublisher());
            System.out.println("  Year: " + book.getPublicationYear());
            System.out.println("----------------------------");
        }

        return books;
    }

}

package Controller;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import model.Book;
import service.BookRegistrationLocal;

import java.util.List;

@Named
@RequestScoped
public class BookController {

    @EJB
    private BookRegistrationLocal bookRegistration;

    private Book newBook;
    private List<Book> books; // Lista para almacenar los libros registrados

    @PostConstruct
    public void init() {
        newBook = new Book();
        listBooks(); // Cargar la lista de libros al inicializar el controlador
    }

    public void register() {
        try {
            bookRegistration.register(newBook);
            System.out.println("Book registered successfully: " + newBook.getTitle());
            listBooks(); // Actualizar la lista de libros después de registrar uno nuevo
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listBooks() {
        try {
            books = bookRegistration.listBooks(); // Obtener la lista de libros desde el servicio
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getters y Setters
    public Book getNewBook() {
        return newBook;
    }

    public void setNewBook(Book newBook) {
        this.newBook = newBook;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

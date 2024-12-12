package views;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Book;
import service.BookRegistrationRemote;

public class BookAppGUI extends JFrame {

    private BookRegistrationRemote bookRegistration;

    // Componentes de la interfaz gráfica
    private JTextField titleField;
    private JTextField authorField;
    private JTextField isbnField;
    private JTextField publisherField;
    private JTextField publicationYearField;
    private JButton registerButton;

    public BookAppGUI(BookRegistrationRemote bookRegistration) {
        this.bookRegistration = bookRegistration;
        initializeUI();
    }

    private void initializeUI() {
        // Configuración de la ventana principal
        setTitle("Book Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        // Crear campos de entrada de texto
        titleField = new JTextField(20);
        authorField = new JTextField(20);
        isbnField = new JTextField(20);
        publisherField = new JTextField(20);
        publicationYearField = new JTextField(4);

        // Crear el botón de registro
        registerButton = new JButton("Register Book");

        // Agregar el listener al botón
        registerButton.addActionListener(e -> registerBook());

        // Agregar los componentes a la ventana
        add(new JLabel("Title:"));
        add(titleField);
        add(new JLabel("Author:"));
        add(authorField);
        add(new JLabel("ISBN:"));
        add(isbnField);
        add(new JLabel("Publisher:"));
        add(publisherField);
        add(new JLabel("Publication Year:"));
        add(publicationYearField);
        add(registerButton);

        pack(); // Ajustar automáticamente al contenido
        setVisible(true);
    }

    private void registerBook() {
        try {
            // Validar y obtener los valores
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String isbn = isbnField.getText().trim();
            String publisher = publisherField.getText().trim();
            String yearText = publicationYearField.getText().trim();

            // Validaciones locales
            validateTitle(title);
            validateAuthor(author);
            validateISBN(isbn);
            validatePublisher(publisher);
            int publicationYear = validateYear(yearText);

            // Crear el objeto Book
            Book book = new Book(title, author, isbn, publisher, publicationYear);

            // Registrar el libro en el sistema remoto
            bookRegistration.register(book);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Book registered successfully: " + title, "Success", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar los campos del formulario
            clearFields();

            // Abrir la ventana de lista de libros, pasando la referencia remota
            new BookListGUI(bookRegistration);

        } catch (NumberFormatException ex) {
            showError("Please enter a valid year.");
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            showError("An error occurred while registering the book.");
        }
    }

    private void validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        if (title.length() > 100) {
            throw new IllegalArgumentException("Title must be at most 100 characters.");
        }
    }

    private void validateAuthor(String author) {
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty.");
        }
        if (author.length() > 50) {
            throw new IllegalArgumentException("Author must be at most 50 characters.");
        }
    }

    private void validateISBN(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty.");
        }
        if (isbn.length() < 10 || isbn.length() > 13) {
            throw new IllegalArgumentException("ISBN must be between 10 and 13 characters.");
        }
    }

    private void validatePublisher(String publisher) {
        if (publisher != null && publisher.length() > 50) {
            throw new IllegalArgumentException("Publisher must be at most 50 characters.");
        }
    }

    private int validateYear(String yearText) {
        if (yearText == null || yearText.isEmpty()) {
            throw new IllegalArgumentException("Publication year cannot be empty.");
        }
        int year = Integer.parseInt(yearText);
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        if (year < 1800 || year > currentYear) {
            throw new IllegalArgumentException("Publication year must be between 1800 and " + currentYear + ".");
        }
        return year;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        isbnField.setText("");
        publisherField.setText("");
        publicationYearField.setText("");
    }
}

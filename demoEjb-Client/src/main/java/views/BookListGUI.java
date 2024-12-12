package views;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Book;
import service.BookRegistrationRemote;

public class BookListGUI extends JFrame {

    private BookRegistrationRemote bookRegistration;

    public BookListGUI(BookRegistrationRemote bookRegistration) {
        this.bookRegistration = bookRegistration;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("List of Books");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear los encabezados de la tabla
        String[] columnNames = {"Title", "Author", "ISBN", "Publisher", "Year"};

        // Crear el modelo de la tabla
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Llenar la tabla con los libros obtenidos del servidor
        try {
            List<Book> books = bookRegistration.listBooks();
            for (Book book : books) {
                Object[] row = {
                    book.getTitle(),
                    book.getAuthor(),
                    book.getIsbn(),
                    book.getPublisher(),
                    book.getPublicationYear()
                };
                tableModel.addRow(row);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error fetching books: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        // Crear la tabla y establecer el modelo
        JTable bookTable = new JTable(tableModel);

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(bookTable);

        // Agregar el JScrollPane a la ventana
        add(scrollPane, BorderLayout.CENTER);

        // Hacer visible la ventana
        setVisible(true);
    }
}

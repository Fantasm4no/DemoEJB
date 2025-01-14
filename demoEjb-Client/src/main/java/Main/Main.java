package Main;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import service.BookRegistrationRemote;
import views.BookAppGUI;

public class Main {

    private BookRegistrationRemote bookRegistration;

    // Inicializa la conexión con el servidor EJB
    public void initialize() throws Exception {
        Hashtable<String, Object> jndiProps = new Hashtable<>();
        jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

        try {
            Context context = new InitialContext(jndiProps);
            bookRegistration = (BookRegistrationRemote) context.lookup(
                "ejb:/demoEjb/BookRegistration!service.BookRegistrationRemote"
            );
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error JNDI lookup.");
            throw e;
        }
    }

    // Devuelve la referencia al EJB remoto (para usar en la GUI)
    public BookRegistrationRemote getBookRegistration() {
        return bookRegistration;
    }

    // Método principal para iniciar la interfaz gráfica
    public static void main(String[] args) {
        try {
            // Inicializar cliente y conectar con el servidor EJB
            Main client = new Main();
            client.initialize();
            client.bookRegistration.listBooks();
            // Iniciar la interfaz gráfica de Swing, pasando la referencia al EJB remoto
            new BookAppGUI(client.getBookRegistration());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

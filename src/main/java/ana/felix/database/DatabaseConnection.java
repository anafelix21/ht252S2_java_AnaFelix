package ana.felix.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Clase para gestionar la conexión a la base de datos MySQL utilizando el patrón Singleton(patrón de diseño creacional que garantiza que una clase tenga solo una instancia y proporciona un punto de acceso global a ella)
public class DatabaseConnection {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://tu-rds-endpoint.region.rds.amazonaws.com:3306/hackaton";
    private static final String USER = "admin";
    private static final String PASSWORD = "987654321";

    // Bloque estático para cargar el driver de MySQL al inicializar la clase
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error cargando el driver de MySQL: " + e.getMessage());
        }
    }

    // Constructor privado para evitar instanciación de la clase (patrón Singleton)
    private DatabaseConnection() {}

    // Método para obtener una conexión a la base de datos, reutilizando la conexión existente si es posible
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexion a la base de datos establecida correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos:");
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
        }
        return connection;
    }

    // Método para cerrar la conexión a la base de datos si está abierta
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexion cerrada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexion: " + e.getMessage());
        }
    }

    // Método para verificar si la conexión a la base de datos está activa
    public static boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}

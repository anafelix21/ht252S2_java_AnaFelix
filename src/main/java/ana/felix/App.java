package ana.felix;

import ana.felix.view.EquipoView;
import javax.swing.*;

// Clase principal que inicia la aplicación Swing para la gestión de equipos
public class App {
    // Método main que lanza la interfaz gráfica en el hilo de eventos de Swing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EquipoView());
    }
}

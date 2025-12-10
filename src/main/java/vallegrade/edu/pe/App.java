package vallegrade.edu.pe;

import vallegrade.edu.pe.view.EquipoView;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new EquipoView();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al iniciar la aplicacion: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
    }
}

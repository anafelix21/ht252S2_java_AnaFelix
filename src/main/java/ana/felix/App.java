package ana.felix;

import ana.felix.view.EquipoView;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EquipoView());
    }
}

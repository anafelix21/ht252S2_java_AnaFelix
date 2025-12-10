package vallegrade.edu.pe.view;

import vallegrade.edu.pe.controller.EquipoController;
import vallegrade.edu.pe.model.Equipo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import vallegrade.edu.pe.utils.ConfiguracionEquipos;
import vallegrade.edu.pe.utils.ModelosPorMarca;

public class EquipoView extends JFrame {
    private EquipoController controller;
    private JTable tablaEquipos;
    private DefaultTableModel modeloTabla;

    // Componentes de entrada
    private JTextField txtCodigo;
    private JComboBox<String> cbModelo;
    private JTextField txtAlmacenamiento;
    private JTextField txtRam;
    private JComboBox<String> cbTipo;
    private JComboBox<String> cbMarca;
    private JComboBox<String> cbSO;
    private JComboBox<String> cbEstado;
    private JSpinner spinnerMantenimiento;
    
    // Botones
    private JButton btnGuardar;
    private JButton btnActualizar;
    private JButton btnLimpiar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JLabel lblEstado;

    private int equipoSeleccionadoId = -1;

    public EquipoView() {
        setTitle("CRUD Equipos - Sistema de Gestion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setResizable(true);

        // Inicializar controlador
        controller = new EquipoController();

        // Crear interfaz
        crearInterfaz();

        // Cargar datos iniciales
        cargarEquipos();

        setVisible(true);
    }

    private void crearInterfaz() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior - Formulario
        JPanel panelFormulario = crearPanelFormulario();
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);

        // Panel central - Tabla
        JPanel panelTabla = crearPanelTabla();
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);

        // Panel inferior - Estado
        lblEstado = new JLabel("Listo");
        lblEstado.setFont(new Font("Arial", Font.BOLD, 12));
        lblEstado.setForeground(new Color(34, 139, 34));
        panelPrincipal.add(lblEstado, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Registro de Equipo"));

        // Fila 1: Codigo, Tipo, Marca
        JPanel fila1 = new JPanel(new GridLayout(1, 3, 10, 0));
        fila1.add(crearCampo("Codigo:", txtCodigo = new JTextField(15)));
        fila1.add(crearCombo("Tipo:", cbTipo = new JComboBox<>(new String[]{"Laptop", "Desktop", "Servidor", "Impresora", "Monitor", "Router"})));
        fila1.add(crearCombo("Marca:", cbMarca = new JComboBox<>(new String[]{"HP", "Dell", "Lenovo", "ASUS", "Apple", "Otro"})));
        panel.add(fila1);

        // Fila 2: Modelo, SO, Almacenamiento
        JPanel fila2 = new JPanel(new GridLayout(1, 3, 10, 0));
        cbModelo = new JComboBox<>(new String[]{"Modelo Generico"});
        fila2.add(crearCombo("Modelo:", cbModelo));
        fila2.add(crearCombo("Sistema Operativo:", cbSO = new JComboBox<>(new String[]{"Windows 10", "Windows 11", "macOS", "Linux", "Otro"})));
        fila2.add(crearCampo("Almacenamiento (GB):", txtAlmacenamiento = new JTextField(10)));
        panel.add(fila2);

        // Agregar listeners para autocompletar
        cbTipo.addActionListener(e -> autocompletarEquipo());
        cbMarca.addActionListener(e -> {
            actualizarModelos();
            autocompletarEquipo();
        });
        cbSO.addActionListener(e -> autocompletarEquipo());

        // Fila 3: RAM, Estado, Mantenimiento
        JPanel fila3 = new JPanel(new GridLayout(1, 3, 10, 0));
        fila3.add(crearCampo("RAM (GB):", txtRam = new JTextField(10)));
        fila3.add(crearCombo("Estado:", cbEstado = new JComboBox<>(new String[]{"Activo", "Inactivo", "Mantenimiento", "Defectuoso"})));
        spinnerMantenimiento = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerMantenimiento, "dd/MM/yyyy");
        spinnerMantenimiento.setEditor(editor);
        fila3.add(crearCampo("Fecha Mantenimiento:", spinnerMantenimiento));
        panel.add(fila3);

        // Fila 5: Botones
        JPanel filaBotones = new JPanel(new GridLayout(1, 4, 10, 0));
        btnGuardar = crearBoton("Guardar", new Color(34, 139, 34), e -> guardarEquipo());
        btnActualizar = crearBoton("Actualizar", new Color(0, 102, 204), e -> actualizarEquipo());
        btnLimpiar = crearBoton("Limpiar", new Color(255, 140, 0), e -> limpiarFormulario());
        btnEliminar = crearBoton("Eliminar", new Color(220, 20, 60), e -> eliminarEquipo());

        filaBotones.add(btnGuardar);
        filaBotones.add(btnActualizar);
        filaBotones.add(btnLimpiar);
        filaBotones.add(btnEliminar);
        panel.add(filaBotones);

        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        return panel;
    }

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Equipos"));

        // Barra de busqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JTextField txtBuscar = new JTextField(20);
        JComboBox<String> cbBuscarPor = new JComboBox<>(new String[]{"codigo", "tipo", "marcas", "estado"});
        btnBuscar = crearBoton("Buscar", new Color(0, 102, 204), e -> buscarEquipo(cbBuscarPor.getSelectedItem().toString(), txtBuscar.getText()));
        JButton btnMostrarTodos = crearBoton("Mostrar Todos", new Color(34, 139, 34), e -> cargarEquipos());
        JButton btnRecargar = crearBoton("Recargar", new Color(100, 100, 100), e -> cargarEquipos());

        panelBusqueda.add(new JLabel("Buscar por:"));
        panelBusqueda.add(cbBuscarPor);
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnMostrarTodos);
        panelBusqueda.add(btnRecargar);
        panel.add(panelBusqueda, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {"ID", "Codigo", "Tipo", "Marca", "Modelo", "SO", "Almacenamiento", "RAM", "Estado", "Mantenimiento", "Registro"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaEquipos = new JTable(modeloTabla);
        tablaEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaEquipos.setFont(new Font("Arial", Font.PLAIN, 11));
        tablaEquipos.setRowHeight(25);
        tablaEquipos.getSelectionModel().addListSelectionListener(e -> seleccionarEquipo());

        // Configurar renderizador para las columnas de fecha
        DefaultTableCellRenderer dateRenderer = new DefaultTableCellRenderer() {
            private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            protected void setValue(Object value) {
                if (value instanceof LocalDate) {
                    setText(((LocalDate) value).format(formatter));
                } else {
                    setText("");
                }
            }
        };
        dateRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Aplicar el renderizador a las columnas 9 (Mantenimiento) y 10 (Registro)
        tablaEquipos.getColumnModel().getColumn(9).setCellRenderer(dateRenderer);
        tablaEquipos.getColumnModel().getColumn(10).setCellRenderer(dateRenderer);

        JScrollPane scrollPane = new JScrollPane(tablaEquipos);
        scrollPane.setPreferredSize(new Dimension(1000, 400));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearCampo(String etiqueta, JComponent componente) {
        JPanel panel = new JPanel(new BorderLayout(5, 0));
        panel.add(new JLabel(etiqueta), BorderLayout.WEST);
        panel.add(componente, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearCombo(String etiqueta, JComboBox<?> combo) {
        JPanel panel = new JPanel(new BorderLayout(5, 0));
        panel.add(new JLabel(etiqueta), BorderLayout.WEST);
        panel.add(combo, BorderLayout.CENTER);
        return panel;
    }

    private JButton crearBoton(String texto, Color color, java.awt.event.ActionListener accion) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 11));
        boton.setFocusPainted(false);
        boton.addActionListener(accion);
        return boton;
    }

    public void cargarEquipos() {
        modeloTabla.setRowCount(0);
        List<Equipo> equipos = controller.obtenerTodos();

        for (Equipo equipo : equipos) {
            agregarFilaTabla(equipo);
        }

        actualizarEstado("Equipos cargados: " + equipos.size());
    }

    private void agregarFilaTabla(Equipo equipo) {
        Object[] fila = {
                equipo.getId(),
                equipo.getCodigo(),
                equipo.getTipo(),
                equipo.getMarcas(),
                equipo.getModelo(),
                equipo.getSo(),
                equipo.getAlmacenamiento(),
                equipo.getRam(),
                equipo.getEstado(),
                equipo.getMantenimiento(),
                equipo.getFechaRegistro()
        };
        modeloTabla.addRow(fila);
    }

    private void seleccionarEquipo() {
        int fila = tablaEquipos.getSelectedRow();
        if (fila >= 0) {
            equipoSeleccionadoId = (int) modeloTabla.getValueAt(fila, 0);
            Equipo equipo = controller.obtenerPorId(equipoSeleccionadoId);

            if (equipo != null) {
                txtCodigo.setText(equipo.getCodigo());
                cbTipo.setSelectedItem(equipo.getTipo());
                cbMarca.setSelectedItem(equipo.getMarcas());
                actualizarModelos();
                cbModelo.setSelectedItem(equipo.getModelo());
                txtAlmacenamiento.setText(String.valueOf(equipo.getAlmacenamiento()));
                cbSO.setSelectedItem(equipo.getSo());
                txtRam.setText(String.valueOf(equipo.getRam()));
                cbEstado.setSelectedItem(equipo.getEstado());

                // Cargar la fecha de mantenimiento si existe
                if (equipo.getMantenimiento() != null) {
                    java.util.Date date = java.sql.Date.valueOf(equipo.getMantenimiento());
                    spinnerMantenimiento.setValue(date);
                }

                actualizarEstado("Equipo seleccionado: " + equipo.getCodigo());
            }
        }
    }

    private void guardarEquipo() {
        if (!validarCampos()) return;

        try {
            // Obtener la fecha de mantenimiento del spinner
            LocalDate fechaMantenimiento = null;
            if (spinnerMantenimiento.getValue() != null) {
                fechaMantenimiento = ((java.util.Date) spinnerMantenimiento.getValue()).toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate();
            }

            Equipo equipo = new Equipo(
                txtCodigo.getText(),
                cbTipo.getSelectedItem().toString(),
                cbMarca.getSelectedItem().toString(),
                cbModelo.getSelectedItem().toString(),
                cbSO.getSelectedItem().toString(),
                    Integer.parseInt(txtAlmacenamiento.getText()),
                    Integer.parseInt(txtRam.getText()),
                    cbEstado.getSelectedItem().toString(),
                    fechaMantenimiento,
                    LocalDate.now()
            );

            if (controller.crearEquipo(equipo)) {
                JOptionPane.showMessageDialog(this, "Equipo guardado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                cargarEquipos();
                limpiarFormulario();
                actualizarEstado("Equipo creado");
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el equipo", "Error", JOptionPane.ERROR_MESSAGE);
                actualizarEstado("Error al crear equipo");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verifique los campos numericos", "Error", JOptionPane.ERROR_MESSAGE);
            actualizarEstado("Error en formato de datos");
        }
    }

    private void actualizarEquipo() {
        if (equipoSeleccionadoId < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un equipo para actualizar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarCampos()) return;

        try {
            Equipo equipo = new Equipo(
                equipoSeleccionadoId,
                txtCodigo.getText(),
                cbTipo.getSelectedItem().toString(),
                cbMarca.getSelectedItem().toString(),
                cbModelo.getSelectedItem().toString(),
                cbSO.getSelectedItem().toString(),
                    Integer.parseInt(txtAlmacenamiento.getText()),
                    Integer.parseInt(txtRam.getText()),
                    cbEstado.getSelectedItem().toString(),
                    ((java.util.Date) spinnerMantenimiento.getValue()).toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate(),
                    LocalDate.now()
            );

            if (controller.actualizarEquipo(equipo)) {
                JOptionPane.showMessageDialog(this, "Equipo actualizado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                cargarEquipos();
                limpiarFormulario();
                actualizarEstado("Equipo actualizado");
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                actualizarEstado("Error al actualizar");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verifique los campos numericos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarEquipo() {
        if (equipoSeleccionadoId < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un equipo para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int resultado = JOptionPane.showConfirmDialog(this,
                "ADVERTENCIA! Esta seguro de que desea ELIMINAR PERMANENTEMENTE este equipo?\n" +
                        "Esta accion NO se puede deshacer. Los datos se borraran por completo de la base de datos.",
                "Confirmar eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (resultado == JOptionPane.YES_OPTION) {
            if (controller.eliminarEquipo(equipoSeleccionadoId)) {
                JOptionPane.showMessageDialog(this, "Equipo eliminado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                cargarEquipos();
                limpiarFormulario();
                actualizarEstado("Equipo eliminado");
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                actualizarEstado("Error al eliminar");
            }
        }
    }

    private void limpiarFormulario() {
        txtCodigo.setText("");
        cbTipo.setSelectedIndex(0);
        cbMarca.setSelectedIndex(0);
        actualizarModelos();
        txtAlmacenamiento.setText("");
        cbSO.setSelectedIndex(0);
        txtRam.setText("");
        cbEstado.setSelectedIndex(0);
        equipoSeleccionadoId = -1;
        tablaEquipos.clearSelection();
        actualizarEstado("Formulario limpiado");
    }

    private void buscarEquipo(String criterio, String valor) {
        if (valor.trim().isEmpty()) {
            cargarEquipos();
            return;
        }

        modeloTabla.setRowCount(0);
        List<Equipo> resultados = controller.buscar(criterio.toLowerCase(), valor);

        for (Equipo equipo : resultados) {
            agregarFilaTabla(equipo);
        }

        actualizarEstado("Resultados encontrados: " + resultados.size());
    }

    private void autocompletarEquipo() {
        String tipo = cbTipo.getSelectedItem().toString();
        String marca = cbMarca.getSelectedItem().toString();
        String so = cbSO.getSelectedItem().toString();
        
        Integer ram = ConfiguracionEquipos.obtenerRAM(tipo, marca, so);
        Integer almacenamiento = ConfiguracionEquipos.obtenerAlmacenamiento(tipo, marca, so);
        
        if (ram != null && ram > 0) {
            txtRam.setText(String.valueOf(ram));
        }
        if (almacenamiento != null && almacenamiento > 0) {
            txtAlmacenamiento.setText(String.valueOf(almacenamiento));
        }
    }

    private void actualizarModelos() {
        String marca = cbMarca.getSelectedItem().toString();
        List<String> modelosList = ModelosPorMarca.obtenerModelos(marca);
        
        // Limpiar el combobox de modelos
        cbModelo.removeAllItems();
        
        // Agregar todos los modelos disponibles
        for (String modelo : modelosList) {
            cbModelo.addItem(modelo);
        }
        
        // Seleccionar el primer modelo
        if (!modelosList.isEmpty()) {
            cbModelo.setSelectedIndex(0);
        }
    }

    private boolean validarCampos() {
        if (txtCodigo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El codigo es obligatorio", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtAlmacenamiento.getText().trim().isEmpty() || txtRam.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Almacenamiento y RAM son obligatorios", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void actualizarEstado(String mensaje) {
        lblEstado.setText(mensaje);
    }
}

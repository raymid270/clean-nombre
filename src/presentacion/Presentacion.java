package presentacion;

import dominio.Dominio;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Presentacion extends JFrame {

    private JTextField campoId;
    private JTextField campoNombre;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public Presentacion(Dominio.GuardarNombre crearCaso, 
                        Dominio.ActualizarNombre actualizarCaso, 
                        Dominio.EliminarNombre eliminarCaso) {
        
        setTitle("Clean - CRUD Nombres");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(780, 420);
        setLocationRelativeTo(null);

        // --- Panel Izquierdo: Tabla ---
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Nombre"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Registros"));

        // --- Panel Derecho: Formulario y Botones ---
        JPanel panelDerecho = new JPanel(new GridBagLayout());
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;

        JLabel lblId = new JLabel("ID:");
        campoId = new JTextField(15);

        JLabel lblNombre = new JLabel("Nombre:");
        campoNombre = new JTextField(15);

        // Definimos un tamaño estándar para que los botones queden ordenados y centrados
        Dimension botonDimension = new Dimension(140, 32);
        JButton btnCrear = new JButton("Crear");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        btnCrear.setPreferredSize(botonDimension);
        btnActualizar.setPreferredSize(botonDimension);
        btnEliminar.setPreferredSize(botonDimension);
        btnLimpiar.setPreferredSize(botonDimension);

        // Agregar campos al panel derecho
        c.gridx = 0; c.gridy = 0; panelDerecho.add(lblId, c);
        c.gridx = 0; c.gridy = 1; panelDerecho.add(campoId, c);

        c.gridx = 0; c.gridy = 2; panelDerecho.add(lblNombre, c);
        c.gridx = 0; c.gridy = 3; panelDerecho.add(campoNombre, c);

        // Centramos los botones en su columna
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0; c.gridy = 4; panelDerecho.add(btnCrear, c);
        c.gridx = 0; c.gridy = 5; panelDerecho.add(btnActualizar, c);
        c.gridx = 0; c.gridy = 6; panelDerecho.add(btnEliminar, c);
        c.gridx = 0; c.gridy = 7; panelDerecho.add(btnLimpiar, c);

        // --- Contenedor Principal con SplitPane (Tabla a la izquierda, Controles a la derecha) ---
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, panelDerecho);
        splitPane.setDividerLocation(450);
        splitPane.setResizeWeight(0.65);
        add(splitPane);

        // --- Eventos y Lógica ---
        tabla.getSelectionModel().addListSelectionListener(e -> {
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada != -1) {
                campoId.setText(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
                campoId.setEditable(false);
                campoNombre.setText(modeloTabla.getValueAt(filaSeleccionada, 1).toString());
            }
        });

        btnCrear.addActionListener(e -> {
            try {
                crearCaso.ejecutar(campoId.getText(), campoNombre.getText());
                modeloTabla.addRow(new Object[]{campoId.getText(), campoNombre.getText()});
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Creado correctamente.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                actualizarCaso.ejecutar(campoId.getText(), campoNombre.getText());
                int fila = tabla.getSelectedRow();
                if (fila != -1) {
                    modeloTabla.setValueAt(campoNombre.getText(), fila, 1);
                }
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Actualizado correctamente.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                eliminarCaso.ejecutar(campoId.getText());
                int fila = tabla.getSelectedRow();
                if (fila != -1) {
                    modeloTabla.removeRow(fila);
                }
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Eliminado correctamente.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void limpiarCampos() {
        campoId.setText("");
        campoNombre.setText("");
        campoId.setEditable(true);
        tabla.clearSelection();
    }
}
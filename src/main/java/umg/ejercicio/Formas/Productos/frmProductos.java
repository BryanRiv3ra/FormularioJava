package Formas.Productos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public class frmProductos {
    private JPanel JPanelPrincipal;
    private JLabel lblTitulo;
    private JLabel lblId;
    private JTextField textFieldId;
    private JLabel lblNombre;
    private JTextField textFieldNombreProducto;
    private JLabel lblOrigen;
    private JComboBox comboBoxOrigen;

    public static void main(String[] args) {
        JFrame frame = new JFrame("frmProductos");
        frame.setContentPane(new frmProductos().JPanelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JButton buttonGrabar;

    public frmProductos() {
        comboBoxOrigen.addItem("Japon");
        comboBoxOrigen.addItem("China");
        comboBoxOrigen.addItem("Corea");

        buttonGrabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }

        });
    }
}
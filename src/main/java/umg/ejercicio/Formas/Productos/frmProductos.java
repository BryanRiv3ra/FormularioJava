package umg.ejercicio.Formas.Productos;

import umg.ejercicio.DataBase.Model.Producto;
import umg.ejercicio.DataBase.Services.ProductoService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    private JButton buttonGrabar;
    private JButton buttonBuscar;
    private JButton buttonActualizar;

    public frmProductos() {
        comboBoxOrigen.addItem("Japon");
        comboBoxOrigen.addItem("China");
        comboBoxOrigen.addItem("Corea");

        buttonGrabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Producto producto = new Producto();
                producto.setDescripcion(textFieldNombreProducto.getText());
                producto.setOrigen(comboBoxOrigen.getSelectedItem().toString());
                try{
                    new ProductoService().agregarProducto(producto);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al agregar el producto");
                }
            }

        });
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int idProducto = textFieldId.getText().isEmpty() ? 0 : Integer.parseInt(textFieldId.getText());
            try{
                Producto productoEncontrado = new ProductoService().obtenerProductoPorId(idProducto);
                if(productoEncontrado!= null){
                 textFieldNombreProducto.setText(productoEncontrado.getDescripcion());
                 comboBoxOrigen.setSelectedItem(productoEncontrado.getOrigen());
                }else {
                    JOptionPane.showMessageDialog(null, "Codigo Inexistente");
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error de base de datos");

                }
            }
        });
        buttonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idProducto = textFieldId.getText().isEmpty() ? 0 : Integer.parseInt(textFieldId.getText());

                    // Asumiendo que tienes campos de texto para otros atributos del producto
                    String nombre = textFieldNombreProducto.getText();
                    int idOrigen = Integer.parseInt(comboBoxOrigen.getSelectedItem().toString());
                    int id = idProducto;

                    Producto productoActualizado = new Producto(idProducto, nombre, idOrigen, id);

                    ProductoService productoService = new ProductoService();
                    boolean actualizado = productoService.actualizarProducto(productoActualizado);

                    if (actualizado) {
                        JOptionPane.showMessageDialog(null, "Producto actualizado con éxito.");
                        // Aquí podrías actualizar la interfaz gráfica si es necesario
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo actualizar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
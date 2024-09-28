package umg.ejercicio.Formas.Productos;

import umg.ejercicio.DataBase.Model.Producto;
import umg.ejercicio.DataBase.Services.ProductoService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
                    // Validar que el campo ID no esté vacío y sea numérico
                    if (textFieldId.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El campo ID no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int idProducto = Integer.parseInt(textFieldId.getText());

                    // Validar que el campo nombre no esté vacío
                    String nombre = textFieldNombreProducto.getText();
                    if (nombre.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El campo nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Validar que el comboBox de origen tenga un valor seleccionado
                    if (comboBoxOrigen.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null, "Seleccione un origen válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Obtener el origen seleccionado desde el comboBox
                    String origen = comboBoxOrigen.getSelectedItem().toString();

                    // Crear un objeto Producto con los datos ingresados
                    Producto productoActualizado = new Producto(idProducto, nombre, origen);

                    // Llamar al servicio para actualizar el producto
                    ProductoService productoService = new ProductoService();
                    boolean actualizado = productoService.actualizarProducto(idProducto, nombre, origen);

                    if (actualizado) {
                        JOptionPane.showMessageDialog(null, "Producto actualizado con éxito.");
                        // Aquí podrías actualizar la interfaz gráfica si es necesario
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo actualizar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
}
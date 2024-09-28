package umg.ejercicio.DataBase.Services;
import umg.ejercicio.DataBase.Dao.ProductoDAO;
import umg.ejercicio.DataBase.Model.Producto;

import java.sql.SQLException;
import java.util.List;

public class ProductoService {
    private ProductoDAO productoDAO = new ProductoDAO();

    public void agregarProducto(Producto producto) throws SQLException {
        productoDAO.insertarProducto(producto);
    }

    public Producto obtenerProductoPorId(int idProducto) throws SQLException {
        return productoDAO.seleccionarProducto(idProducto);
    }

    public List<Producto> obtenerTodosLosProductos() throws SQLException {
        return productoDAO.seleccionarTodosProductos();
    }

    public boolean eliminarProducto(int idProducto) throws SQLException {
        return productoDAO.borrarProducto(idProducto);
    }

    public boolean actualizarProducto(int idProducto, String descripcion, String origen) throws SQLException {
        Producto producto = new Producto(idProducto, descripcion, origen);
        return productoDAO.actualizarProducto(producto);
    }
}


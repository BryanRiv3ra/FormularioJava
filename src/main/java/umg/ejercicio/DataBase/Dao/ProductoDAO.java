package umg.ejercicio.DataBase.Dao;
import umg.ejercicio.DataBase.Model.Producto;
import umg.ejercicio.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private static final String INSERT_PRODUCTO_SQL = "INSERT INTO tb_producto (descripcion, origen) VALUES (?, ?)";
    private static final String SELECT_PRODUCTO_BY_ID = "SELECT id_producto, descripcion, origen FROM tb_producto WHERE id_producto = ?";
    private static final String SELECT_ALL_PRODUCTOS = "SELECT * FROM tb_producto";
    private static final String DELETE_PRODUCTO_SQL = "DELETE FROM tb_producto WHERE id_producto = ?";
    private static final String UPDATE_PRODUCTO_SQL = "UPDATE tb_producto SET descripcion = ?, origen = ? WHERE id_producto = ?";

    public void insertarProducto(Producto producto) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTO_SQL)) {
            preparedStatement.setString(1, producto.getDescripcion());
            preparedStatement.setString(2, producto.getOrigen());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Producto seleccionarProducto(int idProducto) throws SQLException {
        Producto producto = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTO_BY_ID)) {
            preparedStatement.setInt(1, idProducto);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String descripcion = rs.getString("descripcion");
                String origen = rs.getString("origen");
                producto = new Producto(idProducto, descripcion, origen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    public List<Producto> seleccionarTodosProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTOS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                String descripcion = rs.getString("descripcion");
                String origen = rs.getString("origen");
                productos.add(new Producto(idProducto, descripcion, origen));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public boolean borrarProducto(int idProducto) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTO_SQL)) {
            statement.setInt(1, idProducto);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean actualizarProducto(Producto producto) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTO_SQL)) {
            statement.setString(1, producto.getDescripcion());
            statement.setString(2, producto.getOrigen());
            statement.setInt(3, producto.getIdProducto());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}

package model;

import database.CRUD;
import database.ConfigDB;
import entity.Producto;
import entity.Producto;
import entity.Tienda;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoModel implements CRUD {
    @Override
    public Object insert(Object object) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Castear el objeto
        Producto objProducto = (Producto) object;

        try{
            // 3. Crear el SQL
            String sql = "INSERT INTO producto(nombre, precio, id_tienda, stock)VALUES(?,?,?,?)";

            // 4. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar los signos de interrogación
            objPrepare.setString(1,objProducto.getNombre());
            objPrepare.setDouble(2,objProducto.getPrecio());
            objPrepare.setInt(3,objProducto.getId_tienda());
            objPrepare.setInt(4,objProducto.getStock());

            // 6. Ejecutamos el Query
            objPrepare.execute();

            // 7. Obtener el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            // 8. Recorremos el resultado y asignamos el id generado al id_producto
            while(objResult.next()){
                objProducto.setId_producto(objResult.getInt(1));
            }

            // 9. Cerramos el prepareStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Product " + objProducto.getNombre() + " was created successfully");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error adding product, " + e.getMessage());
        }

        // 10. Cerramos la conexión
        ConfigDB.closeConnection();
        return objProducto;
    }

    @Override
    public boolean update(Object object) {
        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir el objeto
        Producto objProducto = (Producto) object;

        //3. Variable bandera para saber si se actualizó
        boolean isUpdated = false;

        try {
            //4. Creamos la sentencia SQL
            String sql = "UPDATE producto SET nombre = ?, precio = ?, id_tienda = ?, stock = ? WHERE producto.id_producto = ? ";

            //5. Preparamos el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar Valor a los signos de interrogación (Parámetros de Query)
            objPrepare.setString(1,objProducto.getNombre());
            objPrepare.setDouble(2,objProducto.getPrecio());
            objPrepare.setInt(3,objProducto.getId_tienda());
            objPrepare.setInt(4,objProducto.getStock());
            objPrepare.setInt(5,objProducto.getId_producto());

            //7. Ejecutamos el Query
            int rowAffected = objPrepare.executeUpdate();

            if (rowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Product " + objProducto.getNombre() + " was updated successfully");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //8. Cerrar la conexión
        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object object) {

        //1. Convertir el objeto a la entidad
        Producto objProducto = (Producto) object;

        //2. Variable booleana para medir el estado de la eliminación
        boolean isDeleted = false;

        //3. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Escribir la sentencia SQL
            String sql = "DELETE FROM producto WHERE id_producto = ?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Asignamos el valor al ?
            objPrepare.setInt(1, objProducto.getId_producto());

            //7. ExecuteUpdate devuelve la cantidad filas afectadas por la sentencia SQL ejecutada.

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Product " + objProducto.getNombre() + " was deleted successfully.");
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //8. Cerrar la conexión
        ConfigDB.closeConnection();
        return isDeleted;
    }

    @Override
    public List<Object> findAll() {

        Connection objConnection = ConfigDB.openConnection();

        List<Object> listProductos = new ArrayList<>();

        try {

            String sql = "SELECT * FROM producto INNER JOIN tienda ON producto.id_tienda = tienda.id_tienda ORDER BY producto.id_producto ASC ";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()) {

                Producto objProductos = new Producto();
                Tienda objTienda = new Tienda();

                objProductos.setId_producto(objResult.getInt("producto.id_producto"));
                objProductos.setNombre(objResult.getString("producto.nombre"));
                objProductos.setPrecio(objResult.getDouble("producto.precio"));
                objProductos.setId_tienda(objResult.getInt("producto.id_tienda"));
                objProductos.setStock(objResult.getInt("producto.stock"));
                objTienda.setId_tienda(objResult.getInt("tienda.id_tienda"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbicacion(objResult.getString("tienda.ubicacion"));

                objProductos.setObjTienda(objTienda);

                listProductos.add(objProductos);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition error, " + e.getMessage());
        }
        return listProductos;
    }

    @Override
    public Object findById(int id) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        Producto objProducto = null;

        try {
            //2. Sentencia SQL
            String sql = "SELECT * FROM producto WHERE id_producto = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos el valor ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente entonces
            while (objResult.next()) {
                objProducto = new Producto();
                objProducto.setId_producto(objResult.getInt("producto.id_producto"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("producto.precio"));
                objProducto.setStock(objResult.getInt("producto.stock"));
                objProducto.setId_tienda(objResult.getInt("producto.id_tienda"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return objProducto;

    }

    public List<Object> findProductsByName(String nombre) {

        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Inicializar la lista donde se guardarán los registros que devuelve la base de datos
        List<Object> listProductos = new ArrayList<>();

        try {
            // 3. Escribir la sentencia SQL
            String sql = "SELECT * FROM producto \n " +
                    "INNER JOIN tienda ON producto.id_tienda = tienda.id_tienda \n " +
                    "WHERE producto.nombre LIKE '%" + nombre + "%';";

            // 4. Utilizar PrepareStatement
            PreparedStatement objPrepareStatement = (PreparedStatement) objConnection.prepareStatement(sql);

            // 5. Ejecutar el Query o Prepare
            ResultSet objResult = (ResultSet) objPrepareStatement.executeQuery();

            //6. Obtener los resultados
            while (objResult.next()) {

                // Creamos una instancia de producto
                Producto objProducto = new Producto();
                Tienda objTienda = new Tienda();

                // Llenamos nuestro objeto con lo que devuelve la base de datos (ResultSet)
                objProducto.setId_producto(objResult.getInt("producto.id_producto"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("producto.precio"));
                objProducto.setStock(objResult.getInt("producto.stock"));
                objTienda.setId_tienda(objResult.getInt("tienda.id_tienda"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbicacion(objResult.getString("tienda.ubicacion"));

                objProducto.setObjTienda(objTienda);

                // Finalmente agregamos el producto a la lista
                listProductos.add(objProducto);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerramos la conexión
        ConfigDB.closeConnection();
        return listProductos;

    }

}

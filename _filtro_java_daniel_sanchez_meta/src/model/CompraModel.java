package model;

import database.CRUD;
import database.ConfigDB;
import entity.*;
import entity.Compra;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraModel implements CRUD {
    @Override
    public Object insert(Object object) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Castear el objeto
        Compra objCompra = (Compra) object;

        try {
            // 3. Crear el SQL
            String sql = "INSERT INTO compra(id_cliente, id_producto, fecha_compra, cantidad)VALUES(?,?,?,?)";

            // 4. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar los signos de interrogación
            objPrepare.setInt(1, objCompra.getId_cliente());
            objPrepare.setInt(2, objCompra.getId_producto());
            objPrepare.setDate(3, objCompra.getFecha_compra());
            objPrepare.setInt(4, objCompra.getCantidad());

            // 6. Ejecutamos el Query
            objPrepare.execute();

            // 7. Obtener el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            // 8. Recorremos el resultado y asignamos el id generado al id_compra
            while (objResult.next()) {
                objCompra.setId_compra(objResult.getInt(1));
            }

            // 9. Cerramos el prepareStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Purchase was created successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding purchase, " + e.getMessage());
        }

        // 10. Cerramos la conexión
        ConfigDB.closeConnection();
        return objCompra;
    }

    @Override
    public boolean update(Object object) {
        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir el objeto
        Compra objCompra = (Compra) object;

        //3. Variable bandera para saber si se actualizó
        boolean isUpdated = false;

        try {
            //4. Creamos la sentencia SQL
            String sql = "UPDATE compra SET id_cliente = ?, id_producto = ?, cantidad = ? WHERE compra.id_compra = ? ";

            //5. Preparamos el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar Valor a los signos de interrogación (Parámetros de Query)
            objPrepare.setInt(1, objCompra.getId_cliente());
            objPrepare.setInt(2, objCompra.getId_producto());
            objPrepare.setInt(3, objCompra.getCantidad());
            objPrepare.setInt(4, objCompra.getId_compra());

            //7. Ejecutamos el Query
            int rowAffected = objPrepare.executeUpdate();

            if (rowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Purchase #" + objCompra.getId_compra() + " was updated successfully");
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
        Compra objCompra = (Compra) object;

        //2. Variable booleana para medir el estado de la eliminación
        boolean isDeleted = false;

        //3. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Escribir la sentencia SQL
            String sql = "DELETE FROM compra WHERE id_compra = ?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Asignamos el valor al ?
            objPrepare.setInt(1, objCompra.getId_compra());

            //7. ExecuteUpdate devuelve la cantidad filas afectadas por la sentencia SQL ejecutada.

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Purchase #" + objCompra.getId_compra() + " was deleted successfully.");
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

        List<Object> listCompra = new ArrayList<>();

        try {
            String sql = """
                    SELECT * FROM compra
                    INNER JOIN cliente ON compra.id_cliente = cliente.id_cliente
                    INNER JOIN producto ON compra.id_producto = producto.id_producto
                    INNER JOIN tienda ON producto.id_tienda = tienda.id_tienda
                    ORDER BY compra.id_compra ASC
                    """;

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()) {

                Compra objCompra = new Compra();
                Producto objProducto = new Producto();
                Cliente objCliente = new Cliente();
                Tienda objTienda = new Tienda();

                objCompra.setId_compra(objResult.getInt("compra.id_compra"));
                objCompra.setId_cliente(objResult.getInt("compra.id_cliente"));
                objCompra.setId_producto(objResult.getInt("compra.id_producto"));
                objCompra.setFecha_compra(objResult.getDate("compra.fecha_compra"));
                objCompra.setCantidad(objResult.getInt("compra.cantidad"));
                objTienda.setId_tienda(objResult.getInt("tienda.id_tienda"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbicacion(objResult.getString("tienda.ubicacion"));
                objProducto.setId_producto(objResult.getInt("producto.id_producto"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("producto.precio"));
                objProducto.setStock(objResult.getInt("producto.stock"));
                objCompra.setId_cliente(objResult.getInt("cliente.id_cliente"));
                objCliente.setNombre(objResult.getString("cliente.nombre"));
                objCliente.setApellido(objResult.getString("cliente.apellido"));
                objCliente.setEmail(objResult.getString("cliente.email"));

                objCompra.setObjCliente(objCliente);
                objCompra.setObjProducto(objProducto);
                objCompra.setObjTienda(objTienda);

                listCompra.add(objCompra);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition error, " + e.getMessage());
        }
        return listCompra;
    }

    @Override
    public Object findById(int id) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        Compra objCompra = null;

        try {
            //2. Sentencia SQL

            String sql = """                                
                    SELECT * FROM compra
                    INNER JOIN cliente ON compra.id_cliente = cliente.id_cliente
                    INNER JOIN producto ON compra.id_producto = producto.id_producto
                    INNER JOIN tienda ON producto.id_tienda = tienda.id_tienda
                    WHERE id_compra = ? ORDER BY compra.id_compra ASC;
                    """;

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos el valor ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente entonces
            while (objResult.next()) {
                objCompra = new Compra();
                Producto objProducto = new Producto();
                Cliente objCliente = new Cliente();
                Tienda objTienda = new Tienda();

                objCompra.setId_compra(objResult.getInt("compra.id_compra"));
                objCompra.setId_cliente(objResult.getInt("compra.id_cliente"));
                objCompra.setId_producto(objResult.getInt("compra.id_producto"));
                objCompra.setFecha_compra(objResult.getDate("compra.fecha_compra"));
                objCompra.setCantidad(objResult.getInt("compra.cantidad"));
                objTienda.setId_tienda(objResult.getInt("tienda.id_tienda"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbicacion(objResult.getString("tienda.ubicacion"));
                objProducto.setId_producto(objResult.getInt("producto.id_producto"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("producto.precio"));
                objProducto.setStock(objResult.getInt("producto.stock"));
                objCompra.setId_cliente(objResult.getInt("cliente.id_cliente"));
                objCliente.setNombre(objResult.getString("cliente.nombre"));
                objCliente.setApellido(objResult.getString("cliente.apellido"));
                objCliente.setEmail(objResult.getString("cliente.email"));
                objCompra.setObjCliente(objCliente);
                objCompra.setObjProducto(objProducto);
                objCompra.setObjTienda(objTienda);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return objCompra;

    }

    public boolean validateStock(int qty, int id_producto) {
        //1. Convertir el objeto a la entidad
        //Compra objCompra = (Compra) obj;

        //2. Variable booleana para medir el estado de la eliminación
        boolean noStock = false;
        int stock = 0;
        int cantidadProducto = qty;

        //3. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Escribir la sentencia SQL
            String sql = """
                    SELECT * FROM compra
                    INNER JOIN producto ON compra.id_producto = producto.id_producto
                    WHERE compra.id_compra = ?;
                    """;

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Asignamos el valor al ?
            objPrepare.setInt(1, id_producto);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                // Capturo el stock del producto de la compra que me enviaron por parametro
                stock = objResult.getInt("producto.stock");

                //valido que la cantidad no sea mayor al stock del producto de la compra
                if (cantidadProducto > stock) {
                    noStock = true;
                    JOptionPane.showMessageDialog(null, "No stock available");
                }
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding the purchase " + e.getMessage());
        }

        //8. Cerrar la conexión
        ConfigDB.closeConnection();
        return noStock;
    }

    public boolean updateStock(Object object) {
        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir el objeto
        Compra objCompra = (Compra) object;
        Producto objProducto = new Producto();

        //3. Variable bandera para saber si se actualizó
        boolean isUpdated = false;

        try {
            //4. Creamos la sentencia SQL
            String sql = "UPDATE producto SET stock = ? \n" +
                    "INNER JOIN compra ON producto.id_producto = compra.id_producto \n" +
                    "WHERE compra.id_compra = ? ";

            //5. Preparamos el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            int minStock = objCompra.getObjProducto().getStock() - objCompra.getCantidad();

            //6. Dar Valor a los signos de interrogación (Parámetros de Query)
            objPrepare.setInt(1, minStock);
            objPrepare.setInt(2, objCompra.getId_compra());

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

    public List<Object> findPurchasesByProduct(int id) {

        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Inicializar la lista donde se guardarán los registros que devuelve la base de datos
        List<Object> listCompras = new ArrayList<>();

        try {
            // 3. Escribir la sentencia SQL
            String sql = "SELECT * FROM compra \n" +
                    "INNER JOIN producto ON compra.id_producto = producto.id_producto\n" +
                    "INNER JOIN cliente ON compra.id_cliente = cliente.id_cliente\n" +
                    "INNER JOIN tienda ON producto.id_tienda = tienda.id_tienda\n" +
                    "WHERE compra.id_producto = ?;";

            // 4. Utilizar PrepareStatement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            //4. Damos el valor ?
            objPrepare.setInt(1, id);

            // 5. Ejecutar el Query o Prepare
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            //6. Obtener los resultados
            while (objResult.next()) {

                // Creamos una instancia de cliente
                Compra objCompra = new Compra();
                Cliente objCliente = new Cliente();
                Producto objProducto = new Producto();
                Tienda objTienda = new Tienda();

                // Llenamos nuestro objeto con lo que devuelve la base de datos (ResultSet)
                objCompra.setId_compra(objResult.getInt("compra.id_compra"));
                objCompra.setFecha_compra(objResult.getDate("compra.fecha_compra"));
                objCompra.setCantidad(objResult.getInt("compra.cantidad"));
                objProducto.setId_producto(objResult.getInt("compra.id_producto"));
                objCompra.setId_cliente(objResult.getInt("compra.id_cliente"));
                objProducto.setId_producto(objResult.getInt("producto.id_producto"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("producto.precio"));
                objCliente.setId_cliente(objResult.getInt("cliente.id_cliente"));
                objCliente.setNombre(objResult.getString("cliente.nombre"));
                objCliente.setApellido(objResult.getString("cliente.apellido"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbicacion(objResult.getString("tienda.ubicacion"));


                objCompra.setObjCliente(objCliente);
                objCompra.setObjProducto(objProducto);
                objCompra.setObjTienda(objTienda);

                // Finalmente agregamos el coder a la lista
                listCompras.add(objCompra);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerramos la conexión
        ConfigDB.closeConnection();
        return listCompras;

    }

}

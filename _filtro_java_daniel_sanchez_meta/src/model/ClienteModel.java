package model;

import database.CRUD;
import database.ConfigDB;
import entity.Cliente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteModel implements CRUD {
    @Override
    public Object insert(Object object) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Castear el objeto
        Cliente objCliente = (Cliente) object;

        try{
            // 3. Crear el SQL
            String sql = "INSERT INTO cliente(nombre,apellido,email)VALUES(?,?,?)";

            // 4. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar los signos de interrogación
            objPrepare.setString(1,objCliente.getNombre());
            objPrepare.setString(2,objCliente.getApellido());
            objPrepare.setString(3,objCliente.getEmail());

            // 6. Ejecutamos el Query
            objPrepare.execute();

            // 7. Obtener el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            // 8. Recorremos el resultado y asignamos el id generado al id_patient
            while(objResult.next()){
                objCliente.setId_cliente(objResult.getInt(1));
            }

            // 9. Cerramos el prepareStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, objCliente.getNombre() + " was created successfully");
        }
        catch (Exception e){

            JOptionPane.showMessageDialog(null,"Error adding passenger, " + e.getMessage());

        }

        // 10. Cerramos la conexión
        ConfigDB.closeConnection();
        return objCliente;
    }

    @Override
    public boolean update(Object object) {
        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir el objeto
        Cliente objCliente = (Cliente) object;

        //3. Variable bandera para saber si se actualizó
        boolean isUpdated = false;

        try {
            //4. Creamos la sentencia SQL
            String sql = "UPDATE cliente SET nombre = ?, apellido = ?, email = ? WHERE id_cliente = ? ";

            //5. Preparamos el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar Valor a los signos de interrogación (Parámetros de Query)
            objPrepare.setString(1, objCliente.getNombre());
            objPrepare.setString(2,objCliente.getApellido());
            objPrepare.setString(3,objCliente.getEmail());
            objPrepare.setInt(4,objCliente.getId_cliente());

            //7. Ejecutamos el Query
            int rowAffected = objPrepare.executeUpdate();

            if (rowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, objCliente.getNombre() + " was updated successfully");
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
        Cliente objCliente = (Cliente) object;

        //2. Variable booleana para medir el estado de la eliminación
        boolean isDeleted = false;

        //3. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Escribir la sentencia SQL
            String sql = "DELETE FROM cliente WHERE id_cliente = ?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Asignamos el valor al ?
            objPrepare.setInt(1, objCliente.getId_cliente());

            //7. ExecuteUpdate devuelve la cantidad filas afectadas por la sentencia SQL ejecutada.

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, objCliente.getNombre() + " was deleted successfully.");
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

        List<Object> listCliente = new ArrayList<>();

        try {

            String sql = "SELECT * FROM cliente ORDER BY cliente.id_cliente ASC ";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()) {

                Cliente objCliente = new Cliente();

                objCliente.setId_cliente(objResult.getInt("id_cliente"));
                objCliente.setNombre(objResult.getString("nombre"));
                objCliente.setApellido(objResult.getString("apellido"));
                objCliente.setEmail(objResult.getString("email"));

                listCliente.add(objCliente);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition error, " + e.getMessage());
        }
        return listCliente;
    }

    @Override
    public Object findById(int id) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        Cliente objCliente = null;

        try {
            //2. Sentencia SQL
            String sql = "SELECT * FROM cliente WHERE id_cliente = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos el valor ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente entonces
            while (objResult.next()) {
                objCliente = new Cliente();
                objCliente.setId_cliente(objResult.getInt("id_cliente"));
                objCliente.setNombre(objResult.getString("nombre"));
                objCliente.setApellido(objResult.getString("apellido"));
                objCliente.setEmail(objResult.getString("email"));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return objCliente;

    }

    public List<Object> findClientsByName(String nombre) {

        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Inicializar la lista donde se guardarán los registros que devuelve la base de datos
        List<Object> listClientes = new ArrayList<>();

        try {
            // 3. Escribir la sentencia SQL
            String sql = "SELECT * FROM cliente \n " +
                    "WHERE cliente.nombre LIKE '%" + nombre + "%';";

            // 4. Utilizar PrepareStatement
            PreparedStatement objPrepareStatement = (PreparedStatement) objConnection.prepareStatement(sql);

            // 5. Ejecutar el Query o Prepare
            ResultSet objResult = (ResultSet) objPrepareStatement.executeQuery();

            //6. Obtener los resultados
            while (objResult.next()) {

                // Creamos una instancia de cliente
                Cliente objCliente = new Cliente();

                // Llenamos nuestro objeto con lo que devuelve la base de datos (ResultSet)
                objCliente.setId_cliente(objResult.getInt("cliente.id_cliente"));
                objCliente.setNombre(objResult.getString("cliente.nombre"));
                objCliente.setApellido(objResult.getString("cliente.apellido"));
                objCliente.setEmail(objResult.getString("cliente.email"));

                // Finalmente agregamos el coder a la lista
                listClientes.add(objCliente);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerramos la conexión
        ConfigDB.closeConnection();
        return listClientes;

    }
}

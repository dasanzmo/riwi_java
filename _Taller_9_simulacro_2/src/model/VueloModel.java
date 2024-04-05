package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;
import entity.Vuelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VueloModel implements CRUD {

    @Override
    public List<Object> findAll() {

        Connection objConnection = ConfigDB.openConnection();

        List<Object> listVuelo = new ArrayList<>();

        try {

            String sql = "SELECT * FROM vuelo INNER JOIN avion ON vuelo.id_avion = avion.id_avion ORDER BY vuelo.id_vuelo ASC ";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()) {

                Vuelo objVuelo = new Vuelo();
                Avion objAvion = new Avion();

                objVuelo.setId_vuelo(objResult.getInt("vuelo.id_vuelo"));
                objVuelo.setId_avion(objResult.getInt("vuelo.id_avion"));
                objVuelo.setDestino(objResult.getString("vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getDate("fecha_salida"));
                objVuelo.setHora_salida(objResult.getTime("hora_salida"));
                objAvion.setId_avion(objResult.getInt("avion.id_avion"));
                objAvion.setModelo(objResult.getString("avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("avion.capacidad"));

                objVuelo.setObjAvion(objAvion);

                listVuelo.add(objVuelo);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition error, " + e.getMessage());
        }
        return listVuelo;
    }

    @Override
    public Object insert(Object object) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Castear el objeto
        Vuelo objVuelo = (Vuelo) object;

        try{
            // 3. Crear el SQL
            String sql = "INSERT INTO vuelo(destino, fecha_salida, hora_salida, id_avion)VALUES(?,?,?,?)";

            // 4. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar los signos de interrogación
            objPrepare.setString(1,objVuelo.getDestino());
            objPrepare.setDate(2,objVuelo.getFecha_salida());
            objPrepare.setTime(3,objVuelo.getHora_salida());
            objPrepare.setInt(4,objVuelo.getId_avion());

            // 6. Ejecutamos el Query
            objPrepare.execute();

            // 7. Obtener el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            // 8. Recorremos el resultado y asignamos el id generado al id_vuelo
            while(objResult.next()){
                objVuelo.setId_vuelo(objResult.getInt(1));
            }

            // 9. Cerramos el prepareStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Flight to " + objVuelo.getDestino() + " was created successfully");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error adding flight, " + e.getMessage());
        }

        // 10. Cerramos la conexión
        ConfigDB.closeConnection();
        return objVuelo;
    }

    @Override
    public boolean update(Object object) {
        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir el objeto
        Vuelo objVuelo = (Vuelo) object;

        //3. Variable bandera para saber si se actualizó
        boolean isUpdated = false;

        try {
            //4. Creamos la sentencia SQL
            String sql = "UPDATE vuelo SET destino = ?, fecha_salida = ?, hora_salida = ?, id_avion = ? WHERE vuelo.id_vuelo = ? ";

            //5. Preparamos el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar Valor a los signos de interrogación (Parámetros de Query)
            objPrepare.setString(1,objVuelo.getDestino());
            objPrepare.setDate(2,objVuelo.getFecha_salida());
            objPrepare.setTime(3,objVuelo.getHora_salida());
            objPrepare.setInt(4,objVuelo.getId_avion());
            objPrepare.setInt(5,objVuelo.getId_vuelo());

            //7. Ejecutamos el Query
            int rowAffected = objPrepare.executeUpdate();

            if (rowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Flight to " + objVuelo.getDestino() + " was updated successfully");
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
        Vuelo objVuelo = (Vuelo) object;

        //2. Variable booleana para medir el estado de la eliminación
        boolean isDeleted = false;

        //3. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Escribir la sentencia SQL
            String sql = "DELETE FROM vuelo WHERE id_vuelo = ?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Asignamos el valor al ?
            objPrepare.setInt(1, objVuelo.getId_vuelo());

            //7. ExecuteUpdate devuelve la cantidad filas afectadas por la sentencia SQL ejecutada.

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Flight to " + objVuelo.getDestino() + " was deleted successfully.");
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //8. Cerrar la conexión
        ConfigDB.closeConnection();
        return isDeleted;
    }

    @Override
    public Object findById(int id) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo = null;
        Avion objAvion = null;

        try {
            //2. Sentencia SQL
            String sql = "SELECT * FROM vuelo WHERE id_vuelo = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos el valor ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente entonces
            while (objResult.next()) {
                objVuelo = new Vuelo();
                objAvion = new Avion();
                objVuelo.setId_vuelo(objResult.getInt("vuelo.id_vuelo"));
                objVuelo.setId_avion(objResult.getInt("vuelo.id_avion"));
                objVuelo.setDestino(objResult.getString("vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getDate("vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getTime("vuelo.hora_salida"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return objVuelo;

    }
}

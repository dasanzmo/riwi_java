package model;

import database.CRUD;
import database.ConfigDB;
import entity.*;
import entity.Reservacion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservacionModel implements CRUD {
    @Override
    public List<Object> findAll() {

        Connection objConnection = ConfigDB.openConnection();

        List<Object> listReservacion = new ArrayList<>();

        try {
            String sql = """
            SELECT * FROM reservacion
            INNER JOIN pasajero ON reservacion.id_pasajero = pasajero.id_pasajero
            INNER JOIN vuelo ON reservacion.id_vuelo = vuelo.id_vuelo
            INNER JOIN avion ON vuelo.id_avion = avion.id_avion
            ORDER BY reservacion.id_reservacion ASC
            """;

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()) {

                Reservacion objReservacion = new Reservacion();
                Vuelo objVuelo = new Vuelo();
                Pasajero objPasajero = new Pasajero();
                Avion objAvion = new Avion();

                objReservacion.setId_reservacion(objResult.getInt("reservacion.id_reservacion"));
                objReservacion.setId_vuelo(objResult.getInt("reservacion.id_vuelo"));
                objReservacion.setId_pasajero(objResult.getInt("reservacion.id_pasajero"));
                objReservacion.setFecha_reservacion(objResult.getDate("reservacion.fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("reservacion.asiento"));
                objAvion.setId_avion(objResult.getInt("avion.id_avion"));
                objAvion.setModelo(objResult.getString("avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("avion.capacidad"));
                objVuelo.setId_avion(objResult.getInt("vuelo.id_avion"));
                objVuelo.setDestino(objResult.getString("vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getDate("vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getTime("vuelo.hora_salida"));
                objPasajero.setId_pasajero(objResult.getInt("pasajero.id_pasajero"));
                objPasajero.setNombre(objResult.getString("pasajero.nombre"));
                objPasajero.setApellido(objResult.getString("pasajero.apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("pasajero.documento_identidad"));

                objVuelo.setObjAvion(objAvion);
                objReservacion.setObjPasajero(objPasajero);
                objReservacion.setObjVuelo(objVuelo);
                objReservacion.setObjAvion(objAvion);

                listReservacion.add(objReservacion);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition error, " + e.getMessage());
        }
        return listReservacion;
    }

    @Override
    public Object insert(Object object) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Castear el objeto
        Reservacion objReservacion = (Reservacion) object;

        try{
            // 3. Crear el SQL
            String sql = "INSERT INTO reservacion(id_vuelo, id_pasajero, fecha_reservacion, asiento)VALUES(?,?,?,?)";

            // 4. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar los signos de interrogación
            objPrepare.setInt(1, objReservacion.getId_vuelo());
            objPrepare.setInt(2, objReservacion.getId_pasajero());
            objPrepare.setDate(3,objReservacion.getFecha_reservacion());
            objPrepare.setString(4, objReservacion.getAsiento());

            // 6. Ejecutamos el Query
            objPrepare.execute();

            // 7. Obtener el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            // 8. Recorremos el resultado y asignamos el id generado al id_reservacion
            while(objResult.next()){
                objReservacion.setId_reservacion(objResult.getInt(1));
            }

            // 9. Cerramos el prepareStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "The booking was created successfully");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error adding booking, " + e.getMessage());
        }

        // 10. Cerramos la conexión
        ConfigDB.closeConnection();
        return objReservacion;
    }

    @Override
    public boolean update(Object object) {
        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir el objeto
        Reservacion objReservacion = (Reservacion) object;

        //3. Variable bandera para saber si se actualizó
        boolean isUpdated = false;

        try {
            //4. Creamos la sentencia SQL
            String sql = "UPDATE reservacion SET id_pasajero = ?, id_vuelo = ?, fecha_reservacion = ?, asiento = ? WHERE reservacion.id_reservacion = ? ";

            //5. Preparamos el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar Valor a los signos de interrogación (Parámetros de Query)
            objPrepare.setInt(1,objReservacion.getId_pasajero());
            objPrepare.setInt(2,objReservacion.getId_vuelo());
            objPrepare.setDate(3,objReservacion.getFecha_reservacion());
            objPrepare.setString(4,objReservacion.getAsiento());
            objPrepare.setInt(5,objReservacion.getId_reservacion());

            //7. Ejecutamos el Query
            int rowAffected = objPrepare.executeUpdate();

            if (rowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Booking #" + objReservacion.getId_reservacion() + " was updated successfully");
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
        Reservacion objReservacion = (Reservacion) object;

        //2. Variable booleana para medir el estado de la eliminación
        boolean isDeleted = false;

        //3. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Escribir la sentencia SQL
            String sql = "DELETE FROM reservacion WHERE id_reservacion = ?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Asignamos el valor al ?
            objPrepare.setInt(1, objReservacion.getId_reservacion());

            //7. ExecuteUpdate devuelve la cantidad filas afectadas por la sentencia SQL ejecutada.

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Booking #" + objReservacion.getId_reservacion() + " was deleted successfully.");
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
        Reservacion objReservacion = null;

        try {
            //2. Sentencia SQL
            String sql = "SELECT * FROM reservacion WHERE id_reservacion = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos el valor ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente entonces
            while (objResult.next()) {
                objReservacion = new Reservacion();
                objReservacion.setId_reservacion(objResult.getInt("reservacion.id_reservacion"));
                objReservacion.setId_vuelo(objResult.getInt("reservacion.id_vuelo"));
                objReservacion.setId_pasajero(objResult.getInt("reservacion.id_pasajero"));
                objReservacion.setAsiento(objResult.getString("reservacion.asiento"));
                objReservacion.setFecha_reservacion(objResult.getDate("reservacion.fecha_reservacion"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return objReservacion;

    }

    public boolean validateSeat(String seat, int id_vuelo){
        //1. Convertir el objeto a la entidad
        //Reservacion objReservacion = (Reservacion) obj;

        //2. Variable booleana para medir el estado de la eliminación
        boolean exist = false;
        int seat_count = 0;
        int capacity = 1;

        //3. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Escribir la sentencia SQL
            String sql = "SELECT r.id_reservacion, r.asiento, a.capacidad FROM reservacion r " +
                    "INNER JOIN vuelo v ON v.id_vuelo = r.id_vuelo " +
                    "INNER JOIN avion a ON a.id_avion = v.id_avion " +
                    "WHERE r.id_vuelo = ?;";


            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Asignamos el valor al ?
            objPrepare.setInt(1, id_vuelo);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                seat_count++;
                capacity = objResult.getInt("capacidad");
                String idSeat = objResult.getString("asiento");
                if(seat.equals(idSeat)){
                    exist = true;
                    JOptionPane.showMessageDialog(null,"The seat " + seat + " in the flight number " + id_vuelo +  " already exists");
                }
            }

            if(seat_count == capacity){
                JOptionPane.showMessageDialog(null,"The plane is full");
                exist = true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding the booking " + e.getMessage());
        }

        //8. Cerrar la conexión
        ConfigDB.closeConnection();
        return exist;
    }
}

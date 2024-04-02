package model;

import database.CRUD;
import database.ConfigDB;
import entity.Appointment;
import entity.Doctor;
import entity.Patient;
import entity.Specialty;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentModel implements CRUD {
    @Override
    public Object insert(Object object) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Castear el objeto
        Appointment objAppointment = (Appointment) object;

        try{

            // 3. Genero la consulta SQL
            String sql = """
                        INSERT INTO appointment (id_appointment, id_patient, id_doctor, date_appointment, appointment_time, reason)
                        VALUES(?,?,?,?,?,?)
                        """;

            //4. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //.5. Asigno los valores ?
            objPrepare.setInt(1,objAppointment.getId_appointment());
            objPrepare.setInt(2,objAppointment.getId_patient());
            objPrepare.setInt(3,objAppointment.getId_doctor());
            objPrepare.setDate(4,objAppointment.getDate_appointment());
            objPrepare.setTime(5,objAppointment.getAppointment_time());
            objPrepare.setString(6, objAppointment.getReason());

            //6. Ejecutamos el query
            objPrepare.execute();

            //7. Obtener el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            // 8. Recorremos el resultado y asignamos el id generado al id_patient
            while(objResult.next()){
                objAppointment.setId_appointment(objResult.getInt(1));
            }

            // 9. Cerramos el prepareStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null,  "Appointment was created successfully");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error adding appointment " + e.getMessage());
        }

        // 10. Cerramos la conexion
        ConfigDB.closeConnection();
        return objAppointment;
    }

    @Override
    public boolean update(Object object) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir el objeto
        Appointment objAppointment = (Appointment) object;

        //3. Variable bandera para saber si se actualizó
        boolean isUpdated = false;

        try {
            //4. Creamos la sentencia SQL
            String sql = "UPDATE appointment SET id_patient = ?, id_doctor = ?, date_appointment = ?, appointment_time = ?, reason = ? WHERE id_appointment = ? ";

            //5. Preparamos el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar Valor a los signos de interrogación (Parámetros de Query)
            objPrepare.setInt(1, objAppointment.getId_patient());
            objPrepare.setInt(2, objAppointment.getId_doctor());
            objPrepare.setDate(3, objAppointment.getDate_appointment());
            objPrepare.setTime(4, objAppointment.getAppointment_time());
            objPrepare.setString(5, objAppointment.getReason());
            objPrepare.setInt(6, objAppointment.getId_appointment());

            //7. Ejecutamos el Query
            int rowAffected = objPrepare.executeUpdate();

            if (rowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Appointment # " + objAppointment.getId_appointment() + " was updated successfully");
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
        Appointment objAppointment = (Appointment) object;

        //2. Variable booleana para medir el estado de la eliminación
        boolean isDeleted = false;

        //3. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Escribir la sentencia SQL
            String sql = "DELETE FROM appointment WHERE id_appointment = ?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Asignamos el valor al ?
            objPrepare.setInt(1, objAppointment.getId_appointment());

            //7. ExecuteUpdate devuelve la cantidad filas afectadas por la sentencia SQL ejecutada.

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Appointment #: " + objAppointment.getId_appointment() + " was deleted successfully.");
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

        List<Object> listAppointments = new ArrayList<>();

        try{
            String sql = "SELECT * FROM appointment \n" +
                    "INNER JOIN patient ON appointment.id_patient = patient.id_patient\n" +
                    "INNER JOIN doctor ON appointment.id_doctor = doctor.id_doctor\n" +
                    "INNER JOIN specialty ON doctor.id_specialty = specialty.id_specialty";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()){
                Appointment objAppointment = new Appointment();
                Patient objPatient = new Patient();
                Doctor objDoctor = new Doctor();
                Specialty objSpecialty = new Specialty();

                objAppointment.setId_appointment(objResult.getInt("appointment.id_appointment"));
                objAppointment.setId_patient(objResult.getInt("appointment.id_patient"));
                objAppointment.setId_doctor(objResult.getInt("appointment.id_doctor"));
                objAppointment.setDate_appointment(objResult.getDate("appointment.date_appointment"));
                objAppointment.setAppointment_time(objResult.getTime("appointment.appointment_time"));
                objAppointment.setReason(objResult.getString("appointment.reason"));
                objPatient.setName(objResult.getString("patient.name"));
                objPatient.setLast_name(objResult.getString("patient.last_name"));
                objPatient.setIdentity_document(objResult.getString("patient.identity_document"));
                objDoctor.setId_doctor(objResult.getInt("doctor.id_doctor"));
                objDoctor.setName(objResult.getString("doctor.name"));
                objDoctor.setLast_name(objResult.getString("doctor.last_name"));
                objSpecialty.setId_specialty(objResult.getInt("specialty.id_specialty"));
                objSpecialty.setName(objResult.getString("specialty.name"));

                //Agrego los objetos doctor, patient, specialty a appointment
                objAppointment.setObjPatient(objPatient);
                objAppointment.setObjDoctor(objDoctor);
                objAppointment.setObjSpecialty(objSpecialty);

                listAppointments.add(objAppointment);

            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data acquisition error " + e.getMessage());
        }

        return listAppointments;

    }

    @Override
    public Object findById(int id) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        Appointment objAppointment = null;

        try {
            //2. Sentencia SQL
            String sql = "SELECT * FROM appointment WHERE id_appointment = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos el valor ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente entonces
            while (objResult.next()) {
                objAppointment = new Appointment();

                objAppointment.setId_appointment(objResult.getInt("appointment.id_appointment"));
                objAppointment.setId_patient(objResult.getInt("appointment.id_patient"));
                objAppointment.setId_doctor(objResult.getInt("appointment.id_doctor"));
                objAppointment.setDate_appointment(objResult.getDate("appointment.date_appointment"));
                objAppointment.setAppointment_time(objResult.getTime("appointment.appointment_time"));
                objAppointment.setReason(objResult.getString("appointment.reason"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return objAppointment;

    }

    public List<Object> findAppointmentByDate(Date date_appointment) {

        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Inicializar la lista donde se guardarán los registros que devuelve la base de datos
        List<Object> listAppointments = new ArrayList<>();

        try {
            // 3. Escribir la sentencia SQL
            String sql = "SELECT * FROM appointment \n" +
                    "INNER JOIN patient ON appointment.id_patient = patient.id_patient\n" +
                    "INNER JOIN doctor ON appointment.id_doctor = doctor.id_doctor\n" +
                    "INNER JOIN specialty ON doctor.id_specialty = specialty.id_specialty \n" +
                    "WHERE date_appointment LIKE '%" + date_appointment + "%';";

            // 4. Utilizar PrepareStatement
            PreparedStatement objPrepareStatement = (PreparedStatement) objConnection.prepareStatement(sql);

            // 5. Ejecutar el Query o Prepare
            ResultSet objResult = (ResultSet) objPrepareStatement.executeQuery();

            //6. Obtener los resultados
            while (objResult.next()) {

                // Creamos una instancia de coder
                Appointment objAppointment = new Appointment();
                Patient objPatient = new Patient();
                Doctor objDoctor = new Doctor();
                Specialty objSpecialty = new Specialty();

                // Llenamos nuestro objeto con lo que devuelve la base de datos (ResultSet)
                objAppointment.setId_appointment(objResult.getInt("appointment.id_appointment"));
                objAppointment.setId_patient(objResult.getInt("appointment.id_patient"));
                objAppointment.setId_doctor(objResult.getInt("appointment.id_doctor"));
                objAppointment.setDate_appointment(objResult.getDate("appointment.date_appointment"));
                objAppointment.setAppointment_time(objResult.getTime("appointment.appointment_time"));
                objAppointment.setReason(objResult.getString("appointment.reason"));
                objPatient.setName(objResult.getString("patient.name"));
                objPatient.setLast_name(objResult.getString("patient.last_name"));
                objPatient.setIdentity_document(objResult.getString("patient.identity_document"));
                objDoctor.setId_doctor(objResult.getInt("doctor.id_doctor"));
                objDoctor.setName(objResult.getString("doctor.name"));
                objDoctor.setLast_name(objResult.getString("doctor.last_name"));
                objSpecialty.setId_specialty(objResult.getInt("specialty.id_specialty"));
                objSpecialty.setName(objResult.getString("specialty.name"));

                // Finalmente agregamos el coder a la lista
                listAppointments.add(objAppointment);

                //Agrego los objetos doctor, patient, specialty a appointment
                objAppointment.setObjPatient(objPatient);
                objAppointment.setObjDoctor(objDoctor);
                objAppointment.setObjSpecialty(objSpecialty);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerramos la conexión
        ConfigDB.closeConnection();
        return listAppointments;

    }
}

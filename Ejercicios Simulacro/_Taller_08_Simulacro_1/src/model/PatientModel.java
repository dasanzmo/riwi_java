package model;

import database.CRUD;
import database.ConfigDB;
import entity.*;
import entity.Patient;
import entity.Patient;
import entity.Patient;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientModel implements CRUD {

    @Override
    public List<Object> findAll() {

        Connection objConnection = ConfigDB.openConnection();

        List<Object> listPatient = new ArrayList<>();

        try {

            String sql = "SELECT * FROM patient ORDER BY patient.id_patient ASC ";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()) {

                Patient objPatient = new Patient();

                objPatient.setId_patient(objResult.getInt("id_patient"));
                objPatient.setName(objResult.getString("name"));
                objPatient.setLast_name(objResult.getString("last_name"));
                objPatient.setBirthdate(objResult.getDate("birthdate"));
                objPatient.setIdentity_document(objResult.getString("identity_document"));

                listPatient.add(objPatient);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition error, " + e.getMessage());
        }
        return listPatient;
    }

    @Override
    public Object insert(Object object) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Castear el objeto
        Patient objPatient = (Patient) object;

        try{
            // 3. Crear el SQL
            String sql = "INSERT INTO patient(name,last_name,birthdate,identity_document)VALUES(?,?,?,?)";

            // 4. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar los signos de interrogación
            objPrepare.setString(1, objPatient.getName());
            objPrepare.setString(2,objPatient.getLast_name());
            objPrepare.setDate(3,objPatient.getBirthdate());
            objPrepare.setString(4,objPatient.getIdentity_document());

            // 6. Ejecutamos el Query
            objPrepare.execute();

            // 7. Obtener el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            // 8. Recorremos el resultado y asignamos el id generado al id_patient
            while(objResult.next()){
                objPatient.setId_patient(objResult.getInt(1));
            }

            // 9. Cerramos el prepareStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, objPatient.getName()+ " was created successfully");
        }
        catch (Exception e){

            JOptionPane.showMessageDialog(null,"Error adding Patient, " + e.getMessage());

        }

        // 10. Cerramos la conexión
        ConfigDB.closeConnection();
        return objPatient;
    }

    @Override
    public boolean update(Object object) {
        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir el objeto
        Patient objPatient = (Patient) object;

        //3. Variable bandera para saber si se actualizó
        boolean isUpdated = false;

        try {
            //4. Creamos la sentencia SQL
            String sql = "UPDATE patient SET name = ?, last_name = ?, birthdate = ?, identity_document = ? WHERE id_patient = ? ";

            //5. Preparamos el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar Valor a los signos de interrogación (Parámetros de Query)
            objPrepare.setString(1, objPatient.getName());
            objPrepare.setString(2,objPatient.getLast_name());
            objPrepare.setDate(3,objPatient.getBirthdate());
            objPrepare.setString(4,objPatient.getIdentity_document());
            objPrepare.setInt(5,objPatient.getId_patient());

            //7. Ejecutamos el Query
            int rowAffected = objPrepare.executeUpdate();

            if (rowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, objPatient.getName() + " was updated successfully");
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
        Patient objPatient = (Patient) object;

        //2. Variable booleana para medir el estado de la eliminación
        boolean isDeleted = false;

        //3. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Escribir la sentencia SQL
            String sql = "DELETE FROM patient WHERE id_patient = ?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Asignamos el valor al ?
            objPrepare.setInt(1, objPatient.getId_patient());

            //7. ExecuteUpdate devuelve la cantidad filas afectadas por la sentencia SQL ejecutada.

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, objPatient.getName() + " was deleted successfully.");
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
        Patient objPatient = null;

        try {
            //2. Sentencia SQL
            String sql = "SELECT * FROM patient WHERE id_patient = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos el valor ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente entonces
            while (objResult.next()) {
                objPatient = new Patient();
                objPatient.setId_patient(objResult.getInt("id_patient"));
                objPatient.setName(objResult.getString("name"));
                objPatient.setLast_name(objResult.getString("last_name"));
                objPatient.setBirthdate(objResult.getDate("birthdate"));
                objPatient.setIdentity_document(objResult.getString("identity_document"));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return objPatient;

    }

    public List<Object> findPatienByDI(String document) {

        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Inicializar la lista donde se guardarán los registros que devuelve la base de datos
        List<Object> listPatients = new ArrayList<>();

        try {
            // 3. Escribir la sentencia SQL
            String sql = "SELECT * FROM patient WHERE identity_document = " + document + ";";

            // 4. Utilizar PrepareStatement
            PreparedStatement objPrepareStatement = (PreparedStatement) objConnection.prepareStatement(sql);

            // 5. Ejecutar el Query o Prepare
            ResultSet objResult = (ResultSet) objPrepareStatement.executeQuery();

            //6. Obtener los resultados
            while (objResult.next()) {

                // Creamos una instancia de coder
                Patient objPatient = new Patient();

                // Llenamos nuestro objeto con lo que devuelve la base de datos (ResultSet)
                objPatient.setId_patient(objResult.getInt(1));
                objPatient.setName(objResult.getString("patient.name"));
                objPatient.setLast_name(objResult.getString("patient.last_name"));
                objPatient.setBirthdate(objResult.getDate("patient.birthdate"));
                objPatient.setIdentity_document(objResult.getString("patient.identity_document"));

                // Finalmente agregamos el coder a la lista
                listPatients.add(objPatient);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 7. Cerramos la conexión
        ConfigDB.closeConnection();
        return listPatients;

    }
}

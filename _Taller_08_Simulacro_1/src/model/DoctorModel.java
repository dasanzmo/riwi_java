package model;

import database.CRUD;
import database.ConfigDB;
import entity.Doctor;
import entity.Specialty;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DoctorModel implements CRUD {

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();

        List<Object> listDoctors = new ArrayList<>();

        try {

            String sql = "SELECT * FROM doctor INNER JOIN specialty ON doctor.id_specialty = specialty.id_specialty ORDER BY doctor.id_doctor ASC ";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()) {

                Doctor objDoctor = new Doctor();
                Specialty objSpecialty = new Specialty();

                objDoctor.setId_doctor(objResult.getInt("doctor.id_doctor"));
                objDoctor.setName(objResult.getString("doctor.name"));
                objDoctor.setLast_name(objResult.getString("doctor.last_name"));
                objSpecialty.setId_specialty(objResult.getInt("specialty.id_specialty"));
                objSpecialty.setName(objResult.getString("specialty.name"));
                objSpecialty.setDescription(objResult.getString("specialty.description"));

                //Agrego el objeto Doctor al objeto Libro
                objDoctor.setSpecialtyDoctor(objSpecialty);

                listDoctors.add(objDoctor);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition error" + e.getMessage());
        }
        return listDoctors;
    }

    @Override
    public Object insert(Object object) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Castear el objeto
        Doctor objDoctor = (Doctor) object;

        try{
            // 3. Crear el SQL
            String sql = "INSERT INTO doctor(name,last_name,id_specialty)VALUES(?,?,?)";

            // 4. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar los signos de interrogación
            objPrepare.setString(1, objDoctor.getName());
            objPrepare.setString(2,objDoctor.getLast_name());
            objPrepare.setInt(3,objDoctor.getId_specialty());

            // 6. Ejecutamos el Query
            objPrepare.execute();

            // 7. Obtener el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            // 8. Recorremos el resultado mientras siga tenga elementos para mostrar
            while(objResult.next()){
                objDoctor.setId_doctor(objResult.getInt(1));
            }

            // 9. Cerramos el prepareStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, objDoctor.getName() + " was created successfully");
        }
        catch (Exception e){

            JOptionPane.showMessageDialog(null,"Error adding Doctor" + e.getMessage());

        }

        // 10. Cerramos la conexión
        ConfigDB.closeConnection();
        return objDoctor;
    }

    @Override
    public boolean update(Object object) {
        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir el objeto
        Doctor objDoctor = (Doctor) object;

        //3. Variable bandera para saber si se actualizó
        boolean isUpdated = false;

        try {
            //4. Creamos la sentencia SQL
            String sql = "UPDATE doctor SET name = ?, last_name = ?, id_specialty = ? WHERE id_doctor = ? ";

            //5. Preparamos el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar Valor a los signos de interrogación (Parámetros de Query)
            objPrepare.setString(1, objDoctor.getName());
            objPrepare.setString(2, objDoctor.getLast_name());
            objPrepare.setInt(3, objDoctor.getId_specialty());
            objPrepare.setInt(4,objDoctor.getId_doctor());

            //7. Ejecutamos el Query
            int rowAffected = objPrepare.executeUpdate();

            if (rowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, objDoctor.getName() + " was updated successfully");
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
        return false;
    }

    @Override
    public Object findById(int id) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        Doctor objDoctor = null;

        try {
            //2. Sentencia SQL
            String sql = "SELECT * FROM doctor WHERE id_doctor = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos el valor ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente entonces
            while (objResult.next()) {
                objDoctor = new Doctor();
                objDoctor.setId_doctor(objResult.getInt("id_doctor"));
                objDoctor.setName(objResult.getString("name"));
                objDoctor.setLast_name(objResult.getString("last_name"));
                objDoctor.setId_specialty(objResult.getInt("id_specialty"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return objDoctor;

    }

    public List<Object> findDoctorBySpecialty(int document) {
        return null;
    }
}

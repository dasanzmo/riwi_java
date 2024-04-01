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
import java.util.List;

public class SpecialtyModel implements CRUD {

    @Override
    public Object insert(Object object) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Castear el objeto
        Specialty objSpecialty = (Specialty) object;

        try{
            // 3. Crear el SQL
            String sql = "specialty(name,description)VALUES(?,?)";

            // 4. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar los signos de interrogación
            objPrepare.setString(1, objSpecialty.getName());
            objPrepare.setString(2,objSpecialty.getDescription());

            // 6. Ejecutamos el Query
            objPrepare.execute();

            // 7. Obtener el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            // 8. Recorremos el resultado mientras siga tenga elementos para mostrar
            while(objResult.next()){
                objSpecialty.setId_specialty(objResult.getInt(1));
            }

            // 9. Cerramos el prepareStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, objSpecialty.getName()+ " was created successfully");
        }
        catch (Exception e){

            JOptionPane.showMessageDialog(null,"Error adding Specialty" + e.getMessage());

        }

        // 10. Cerramos la conexión
        ConfigDB.closeConnection();
        return objSpecialty;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();

        List<Object> listSpecialty = new ArrayList<>();

        try {

            String sql = "SELECT * FROM specialty ORDER BY specialty.id_specialty ASC ";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()) {

                Specialty objSpecialty = new Specialty();

                objSpecialty.setId_specialty(objResult.getInt("specialty.id_specialty"));
                objSpecialty.setName(objResult.getString("specialty.name"));
                objSpecialty.setDescription(objResult.getString("specialty.description"));

                listSpecialty.add(objSpecialty);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition error" + e.getMessage());
        }
        return listSpecialty;
    }

    @Override
    public Object findById(int id) {
        return null;
    }

}

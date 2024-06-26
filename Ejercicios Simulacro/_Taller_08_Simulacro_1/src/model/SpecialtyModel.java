package model;

import database.CRUD;
import database.ConfigDB;
import entity.Specialty;
import entity.Specialty;
import entity.Specialty;
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
    public Object insert(Object object) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Castear el objeto
        Specialty objSpecialty = (Specialty) object;

        try{
            // 3. Crear el SQL
            String sql = "INSERT INTO specialty(name,description)VALUES(?,?)";

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
        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir el objeto
        Specialty objSpecialty = (Specialty) object;

        //3. Variable bandera para saber si se actualizó
        boolean isUpdated = false;

        try {
            //4. Creamos la sentencia SQL
            String sql = "UPDATE specialty SET name = ?, description = ? WHERE id_specialty = ? ";

            //5. Preparamos el Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar Valor a los signos de interrogación (Parámetros de Query)
            objPrepare.setString(1, objSpecialty.getName());
            objPrepare.setString(2, objSpecialty.getDescription());
            objPrepare.setInt(3,objSpecialty.getId_specialty());

            //7. Ejecutamos el Query
            int rowAffected = objPrepare.executeUpdate();

            if (rowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, objSpecialty.getName() + " was updated successfully");
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
        Specialty objSpecialty = (Specialty) object;

        //2. Variable booleana para medir el estado de la eliminación
        boolean isDeleted = false;

        //3. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Escribir la sentencia SQL
            String sql = "DELETE FROM specialty WHERE id_specialty = ?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Asignamos el valor al ?
            objPrepare.setInt(1, objSpecialty.getId_specialty());

            //7. ExecuteUpdate devuelve la cantidad filas afectadas por la sentencia SQL ejecutada.

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, objSpecialty.getName() + " was deleted successfully.");
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
        Specialty objSpecialty = null;

        try {
            //2. Sentencia SQL
            String sql = "SELECT * FROM specialty WHERE id_specialty = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos el valor ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente entonces
            while (objResult.next()) {
                objSpecialty = new Specialty();
                objSpecialty.setId_specialty(objResult.getInt("id_specialty"));
                objSpecialty.setName(objResult.getString("name"));
                objSpecialty.setDescription(objResult.getString("description"));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return objSpecialty;

    }

}

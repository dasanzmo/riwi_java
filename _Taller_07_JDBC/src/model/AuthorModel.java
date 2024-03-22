package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;

import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorModel implements CRUD {

    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();

        Author objAuthor = (Author) object;

        try {
            String sql = "INSERT INTO authors(name,nacionality)VALUES(?,?)";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objAuthor.getName());
            objPrepare.setString(2, objAuthor.getNacionality());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objAuthor.setId_autor(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, objAuthor.toString() + " was created successfully");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding Author " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return objAuthor;

    }

    @Override
    public boolean update(Object object) {

        Connection objConnection = ConfigDB.openConnection();

        Author objAuthor = (Author) object;

        boolean isUpdated = false;

        try {
            String sql = "UPDATE authors SET name = ?, nacionality = ? WHERE authors.id_author = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objAuthor.getName());
            objPrepare.setString(2,objAuthor.getNacionality());
            objPrepare.setInt(3,objAuthor.getId_autor());

            int rowAffected = objPrepare.executeUpdate();

            if(rowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"The author was updated successfully");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object object) {

        Author objAuthor = (Author) object;

        boolean isDeleted = false;

        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql = "DELETE FROM authors WHERE authors.id_author = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1,objAuthor.getId_autor());

            int affectedRows = objPrepare.executeUpdate();

            if(affectedRows > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The author was deleted successfully");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        ConfigDB.closeConnection();
        return isDeleted;

    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();

        List<Object> AuthorsList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM authors ORDER BY authors.id_author ASC;";

            PreparedStatement objPrepareStatement = (PreparedStatement) objConnection.prepareStatement(sql);

            ResultSet objResult = (ResultSet) objPrepareStatement.executeQuery();

            while (objResult.next()) {

                Author objAuthor = new Author();

                objAuthor.setId_autor(objResult.getInt("id_author"));
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNacionality(objResult.getString("Nacionality"));

                AuthorsList.add(objAuthor);
            }

            ConfigDB.closeConnection();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition error " + e.getMessage());
        }

        return AuthorsList;

    }

    @Override
    public Object findById(int id) {
        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        Author objAuthor = null;

        try {
            String sql = "SELECT * FROM authors WHERE authors.id_author = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                objAuthor = new Author();
                objAuthor.setId_autor(objResult.getInt("id_author"));
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNacionality(objResult.getString("nacionality"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objAuthor;
    }

    @Override
    public List<Object> findBookByAuthor(int id) {
        return null;
    }
}

package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class AuthorModel implements CRUD {

    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();

        Author objAuthor = (Author) object;

        try{
            String sql = "INSERT INTO authors(name,nacionality)VALUES(?,?)";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objAuthor.getName());
            objPrepare.setString(2,objAuthor.getNacionality());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objAuthor.setId_autor(objResult.getInt(1));
            }

            objPrepare.close();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error adding Author " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return objAuthor;

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
        return null;
    }

    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public List<Object> findByName(String name) {
        return null;
    }
}

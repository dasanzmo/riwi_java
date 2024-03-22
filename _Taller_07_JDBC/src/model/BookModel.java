package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;
import entity.Book;

import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookModel implements CRUD {

    @Override
    public List<Object> findAll() {

        Connection objConnection = ConfigDB.openConnection();

        List<Object> listBooks = new ArrayList<>();

        try {

            String sql = "SELECT books.*, authors.name AS 'author' FROM books INNER JOIN authors ON books.id_author = authors.id_author ORDER BY books.id_book ASC ";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()) {

                Book objBook = new Book();

                objBook.setId_book(objResult.getInt("id_book"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setDate_publish(objResult.getDate("date_pub"));
                objBook.setPrice(objResult.getDouble("price"));
                objBook.setId_author(objResult.getInt("id_author"));
                objBook.setName_author(objResult.getString("author"));

                listBooks.add(objBook);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }
        return listBooks;
    }

    @Override
    public Object insert(Object object) {

        Connection objConnection = ConfigDB.openConnection();

        Book objBook = (Book) object;

        try {
            String sql = "INSERT INTO books(title,date_pub,price,id_author)VALUES(?,?,?,?)";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objBook.getTitle());
            objPrepare.setDate(2, objBook.getDate_publish());
            objPrepare.setDouble(3, objBook.getPrice());
            objPrepare.setInt(4, objBook.getId_author());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objBook.setId_book(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, objBook.getTitle() + " was created successfully");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding Book " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return objBook;

    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();

        Book objBook = (Book) object;

        boolean isUpdated = false;

        try {
            String sql = "UPDATE books SET title = ?, date_pub = ?, price = ?, id_author = ? WHERE books.id_book = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objBook.getTitle());
            objPrepare.setDate(2,objBook.getDate_publish());
            objPrepare.setDouble(3,objBook.getPrice());
            objPrepare.setInt(4,objBook.getId_author());
            objPrepare.setInt(5,objBook.getId_book());

            int rowAffected = objPrepare.executeUpdate();

            if(rowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"The book was updated successfully");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object object) {

        // Convertimos el objeto a Book
        Book objBook = (Book) object;

        boolean isDeleted = false;

        Connection objConnection = ConfigDB.openConnection();

        try{

            // Sentencia SQL para eliminar
            String sql = "DELETE FROM books WHERE books.id_book = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1,objBook.getId_book());

            int affectedRows = objPrepare.executeUpdate();

            if(affectedRows > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The book was deleted successfully");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        ConfigDB.closeConnection();
        return isDeleted;
    }

    @Override
    public Object findById(int id) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = null;

        try {
            String sql = "SELECT books.*, authors.name AS 'author' FROM books INNER JOIN authors ON books.id_author = authors.id_author WHERE id_book = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                objBook = new Book();
                objBook.setId_book(objResult.getInt("id_book"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setDate_publish(objResult.getDate("date_pub"));
                objBook.setPrice(objResult.getDouble("price"));
                objBook.setId_author(objResult.getInt("id_author"));
                objBook.setName_author(objResult.getString("author"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objBook;
    }

    @Override
    public List<Object> findBookByAuthor(String name) {
        return null;
    }
}

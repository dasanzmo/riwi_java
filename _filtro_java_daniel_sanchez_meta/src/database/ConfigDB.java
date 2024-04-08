package database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    // Variable de conexion
    static Connection objConnection = null;

    // Método para abrir conexión entre java y DB

    public static Connection openConnection(){

        try {

            // Implementar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Variables con nuestras crendenciales de la base de datos
            String url = "jdbc:mysql://localhost:3306/demoda_outlet";
            String user = "root";
            String password = "";

            //Establezco conexión
            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Connected successfully");

        }catch (ClassNotFoundException e){
            System.out.println("Error >> Driver not installed");
        }catch (SQLException e){
            System.out.println("Error >> An error has occurred connecting to database");
        };

        return objConnection;

    }

    public static void closeConnection(){
        try{
            //validar si hay conexión activa y la cerramos
            if(objConnection != null) objConnection.close();
        } catch (SQLException e){
            System.out.println("Error >> " + e.getMessage());

        }
    }

}

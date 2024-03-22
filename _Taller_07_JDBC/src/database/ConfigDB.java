package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    // Variable de conexion
    static Connection objConnection = null;

    // Método para abrir conexión entre java y DB
    public static Connection openConnection(){
        try{
            // Implementar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Variables con nuestras crendenciales de la base de datos
            //String url = "jdbc:mysql://localhost:3306/library";
            String url = "jdbc:mysql://uipuhnfcji7ig9f6:chk7RHTZTiar2sdAjR4L@brqkoaglxajvoe6gnys6-mysql.services.clever-cloud.com:3306/brqkoaglxajvoe6gnys6";
            String user = "uipuhnfcji7ig9f6";
            String password = "chk7RHTZTiar2sdAjR4L";

            //Establezco conexión
            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Connected successfully");

        }catch (ClassNotFoundException e){
            System.out.println("Error >> Driver not installed");
        }catch (SQLException e){
            System.out.println("Error >> An error has occurred connecting to database");
        };

        return objConnection;
    };

    public static void closeConnection(){
        try{
            //validar si hay conexión activa y la cerramos
            if(objConnection != null) objConnection.close();
        } catch (SQLException e){
            System.out.println("Error >> " + e.getMessage());

        }
    }
}

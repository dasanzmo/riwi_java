package controller;

import entity.Pasajero;
import model.PasajeroModel;

import javax.swing.*;
import java.sql.Date;
import java.util.List;

public class PasajeroController {

    PasajeroModel objPasajeroModel;

    public PasajeroController() {
        this.objPasajeroModel = new PasajeroModel();
    }

    // Método para listar todos los pasajero
    public void getAll() {
        String list = this.getAll(objPasajeroModel.findAll());

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Sobreescribir un método, metodos que se llaman igual pero hacen cosas diferentes
    public String getAll(List<Object> listObject) {

        String list = "😎 Passengers list: \n\n";

        // Iteramos sobre la lista que devuelve el método find All
        for (Object obj : listObject) {

            // Convertimos o casteamos el objeto tipo Object a un pasajero
            Pasajero objPasajero = (Pasajero) obj;

            // Concatenamos la información
            list += objPasajero.toString() + "\n";

        }
        return list;

    }

    // Método para crear Pasajeros
    public void create(){

        // Creo un nuevo pasajero
        Pasajero objPasajero = new Pasajero();

        // Pidos los datos a insertar del paciente
        String nombre = JOptionPane.showInputDialog("Insert passengers's name");
        String apellido = JOptionPane.showInputDialog("Insert passengers's last name");
        String documento_identidad = JOptionPane.showInputDialog("Insert passengers's identity document");

        objPasajero.setNombre(nombre);
        objPasajero.setApellido(apellido);
        objPasajero.setDocumento_identidad(documento_identidad);

        // Inserto el objeto Pasajero y lo casteo con el tipo de objeto Pasajero
        objPasajero = (Pasajero) this.objPasajeroModel.insert(objPasajero);

    }

    // Método para actualizar Pasajeros
    public void update(){

        // Listamos los pasajeros
        String listPasajeros = this.getAll(this.objPasajeroModel.findAll());

        //Pedimos el ID
        int idUpdatePasajero = Integer.parseInt(JOptionPane.showInputDialog(listPasajeros + "\n Choose the passenger ID to edit "));

        // Verificamos el ID del pasajero
        Pasajero objPasajero = (Pasajero) this.objPasajeroModel.findById(idUpdatePasajero);

        if(objPasajero == null){
            JOptionPane.showMessageDialog(null,"The passenger doesn't exist");
        }
        else{
            String nombre = JOptionPane.showInputDialog(null,"Insert the new passenger's name", objPasajero.getNombre());
            String apellido = JOptionPane.showInputDialog(null,"Insert the new passenger's last name", objPasajero.getApellido());
            String documento_identidad = JOptionPane.showInputDialog(null,"Insert the new passenger's identity document", objPasajero.getDocumento_identidad());

            // Asigno los datos ingresados al pasajero
            objPasajero.setNombre(nombre);
            objPasajero.setApellido(apellido);
            objPasajero.setDocumento_identidad(documento_identidad);

            // Retorno el Pasajero al modelo update del Pasajero
            this.objPasajeroModel.update(objPasajero);

        }
    }

    // Método para eliminar Pasajeros
    public void delete() {

        String listPasajeros = this.getAll(this.objPasajeroModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPasajeros + "\n Enter the passenger ID to delete"));
        Pasajero objPasajero = (Pasajero) this.objPasajeroModel.findById(idDelete);

        if (objPasajero == null) {
            JOptionPane.showMessageDialog(null, " Passenger not found.");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are your sure want to delete the passenger: \n" + objPasajero.getNombre());

            //Si el usuario escogió que si entonces eliminamos.
            if (confirm == 0) {
                this.objPasajeroModel.delete(objPasajero);
            }
        }
    }

}

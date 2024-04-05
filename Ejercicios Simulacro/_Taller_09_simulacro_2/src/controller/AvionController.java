package controller;

import entity.Avion;
import model.AvionModel;

import javax.swing.*;
import java.util.List;

public class AvionController {

    AvionModel objAvionModel;

    public AvionController() {
        this.objAvionModel = new AvionModel();
    }

    // Método para listar todos los Avion
    public void getAll() {
        String list = this.getAll(objAvionModel.findAll());

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Sobreescribir un método, metodos que se llaman igual pero hacen cosas diferentes
    public String getAll(List<Object> listObject) {

        String list = "✈ Planes list: \n\n";

        // Iteramos sobre la lista que devuelve el método find All
        for (Object obj : listObject) {

            // Convertimos o casteamos el objeto tipo Object a un Avion
            Avion objAvion = (Avion) obj;

            // Concatenamos la información
            list += objAvion.toString() + "\n";

        }
        return list;

    }

    // Método para crear Aviones
    public void create(){

        // Creo un nuevo Avion
        Avion objAvion = new Avion();

        // Pidos los datos a insertar del paciente
        String modelo = JOptionPane.showInputDialog("Insert plane's model");
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Insert plane's capacity"));

        objAvion.setModelo(modelo);
        objAvion.setCapacidad(capacidad);

        // Inserto el objeto Avion y lo casteo con el tipo de objeto Avion
        objAvion = (Avion) this.objAvionModel.insert(objAvion);

    }

    // Método para actualizar Aviones
    public void update(){

        // Listamos los Aviones
        String listAviones = this.getAll(this.objAvionModel.findAll());

        //Pedimos el ID
        int idUpdateAvion = Integer.parseInt(JOptionPane.showInputDialog(listAviones + "\n Choose the plane ID to edit "));

        // Verificamos el ID del Avion
        Avion objAvion = (Avion) this.objAvionModel.findById(idUpdateAvion);

        if(objAvion == null){
            JOptionPane.showMessageDialog(null,"The plane doesn't exist");
        }
        else{
            String modelo = JOptionPane.showInputDialog("Insert new plane's model", objAvion.getModelo());
            int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Insert new plane's capacity", objAvion.getCapacidad()));

            // Asigno los datos ingresados al Avion
            objAvion.setModelo(modelo);
            objAvion.setCapacidad(capacidad);

            // Retorno el Avion al modelo update del Avion
            this.objAvionModel.update(objAvion);

        }
    }

    // Método para eliminar Aviones
    public void delete() {

        String listAvionString = "✈ Planes List \n\n";

        for (Object obj : this.objAvionModel.findAll()) {
            Avion objAvion = (Avion) obj;
            listAvionString += objAvion.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAvionString + "\n Enter the plane ID to delete"));
        Avion objAvion = (Avion) this.objAvionModel.findById(idDelete);

        if (objAvion == null) {
            JOptionPane.showMessageDialog(null, " Plane not found.");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are your sure want to delete the plane: \n" + objAvion.getModelo());

            //Si el usuario escogió que si entonces eliminamos.
            if (confirm == 0) {
                this.objAvionModel.delete(objAvion);
            }
        }
    }

}

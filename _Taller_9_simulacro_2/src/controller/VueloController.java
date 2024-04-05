package controller;

import entity.Vuelo;
import entity.Vuelo;
import model.AvionModel;
import model.VueloModel;
import model.VueloModel;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class VueloController {

    VueloModel objVueloModel;
    AvionModel objAvionModel;
    AvionController objAvionController;

    public VueloController() {
        this.objVueloModel = new VueloModel();
        this.objAvionModel = new AvionModel();
        this.objAvionController = new AvionController();
    }

    // Método para listar todos los Vuelo
    public void getAll() {
        String list = this.getAll(objVueloModel.findAll());

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Sobreescribir un método, metodos que se llaman igual pero hacen cosas diferentes
    public String getAll(List<Object> listObject) {

        String list = "✈ Flights list: \n\n";

        // Iteramos sobre la lista que devuelve el método find All
        for (Object obj : listObject) {

            // Convertimos o casteamos el objeto tipo Object a un Vuelo
            Vuelo objVuelo = (Vuelo) obj;

            // Concatenamos la información
            list += objVuelo.toString() + "\n";

        }
        return list;

    }

    // Método para crear Vueloes
    public void create(){

        // Creo un nuevo Vuelo
        Vuelo objVuelo = new Vuelo();

        // Pidos los datos a insertar del paciente
        Object[] destinos = {"Medellin","Bogotá","Cali","Santa Marta","Cartagena"};
        String destino = String.valueOf(JOptionPane.showInputDialog(null,
                "Insert flight's destination",
                null,
                JOptionPane.QUESTION_MESSAGE,null,
                destinos,
                destinos[0]));
        //String destino = JOptionPane.showInputDialog("Insert flight's destination");
        Date fecha_salida = Date.valueOf(JOptionPane.showInputDialog("Insert flight's departure date"));
        Time hora_salida = Time.valueOf(JOptionPane.showInputDialog("Insert flight's departure time"));

        // Muestro la lista de aviones para escoger una y asignarla al vuelo
        String listaAviones = objAvionController.getAll(this.objAvionModel.findAll());
        int id_avion = Integer.parseInt(JOptionPane.showInputDialog(null, listaAviones + "\n Choose the Airplane ID for this flight"));

        objVuelo.setDestino(destino);
        objVuelo.setFecha_salida(fecha_salida);
        objVuelo.setHora_salida(hora_salida);
        objVuelo.setId_avion(id_avion);

        // Inserto el objeto Vuelo y lo casteo con el tipo de objeto Vuelo
        objVuelo = (Vuelo) this.objVueloModel.insert(objVuelo);

    }

    // Método para actualizar Vuelos
    public void update(){

        // Listamos los Vueloes
        String listVuelos = this.getAll(this.objVueloModel.findAll());

        //Pedimos el ID
        int idUpdateVuelo = Integer.parseInt(JOptionPane.showInputDialog(listVuelos + "\n Choose the flight ID to edit "));

        // Verificamos el ID del Vuelo
        Vuelo objVuelo = (Vuelo) this.objVueloModel.findById(idUpdateVuelo);

        if(objVuelo == null){
            JOptionPane.showMessageDialog(null,"The flight doesn't exist");
        }
        else{

            // Pidos los datos a insertar del vuelo
            Object[] destinos = {"Medellin","Bogotá","Cali","Santa Marta","Cartagena"};
            String destino = String.valueOf(JOptionPane.showInputDialog(null,
                    "Insert flight's destination",
                    null,
                    JOptionPane.QUESTION_MESSAGE,null,
                    destinos,
                    destinos[0]));

            Date fecha_salida = Date.valueOf(JOptionPane.showInputDialog("Insert flight's departure date", objVuelo.getFecha_salida()));
            Time hora_salida = Time.valueOf(JOptionPane.showInputDialog("Insert flight's departure time", objVuelo.getHora_salida()));

            // Muestro la lista de aviones para escoger una y asignarla al vuelo
            String listaAviones = objAvionController.getAll(this.objAvionModel.findAll());
            int id_avion = Integer.parseInt(JOptionPane.showInputDialog(null, listaAviones + "\n Choose the Airplane ID for this flight", objVuelo.getId_avion()));

            objVuelo.setDestino(destino);
            objVuelo.setFecha_salida(fecha_salida);
            objVuelo.setHora_salida(hora_salida);
            objVuelo.setId_avion(id_avion);

            // Retorno el Vuelo al modelo update del Vuelo
            this.objVueloModel.update(objVuelo);

        }
    }

    // Método para eliminar Vueloes
    public void delete() {

        String listVueloString = "✈ Flights List \n\n";

        for (Object obj : this.objVueloModel.findAll()) {
            Vuelo objVuelo = (Vuelo) obj;
            listVueloString += objVuelo.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listVueloString + "\n Enter the flight ID to delete"));
        Vuelo objVuelo = (Vuelo) this.objVueloModel.findById(idDelete);

        if (objVuelo == null) {
            JOptionPane.showMessageDialog(null, " Flight not found.");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are your sure want to delete the flight to: \n" + objVuelo.getDestino());

            //Si el usuario escogió que si entonces eliminamos.
            if (confirm == 0) {
                this.objVueloModel.delete(objVuelo);
            }
        }
    }

}

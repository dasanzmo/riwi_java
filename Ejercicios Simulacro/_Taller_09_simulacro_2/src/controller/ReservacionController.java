package controller;

import entity.Reservacion;
import entity.Vuelo;
import model.PasajeroModel;
import model.ReservacionModel;
import model.VueloModel;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class ReservacionController {

    ReservacionModel objReservacionModel;
    VueloModel objVueloModel;
    VueloController objVueloController;
    PasajeroModel objPasajeroModel;
    PasajeroController objPasajeroController;

    public ReservacionController() {
        this.objReservacionModel = new ReservacionModel();
        this.objVueloModel = new VueloModel();
        this.objVueloController = new VueloController();
        this.objPasajeroModel = new PasajeroModel();
        this.objPasajeroController = new PasajeroController();
    }

    // Método para listar todas las reservaciones
    public void getAll() {
        String list = this.getAll(objReservacionModel.findAll());

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Sobreescribir un método, metodos que se llaman igual pero hacen cosas diferentes
    public String getAll(List<Object> listObject) {

        String list = "📅 Bookings list: \n\n";

        // Iteramos sobre la lista que devuelve el método find All
        for (Object obj : listObject) {

            // Convertimos o casteamos el objeto tipo Object a una reserva
            Reservacion objReservacion = (Reservacion) obj;

            // Concatenamos la información
            list += objReservacion.toString() + "\n";

        }
        return list;

    }


    // Método para crear reservaciones
    public void create(){

        // Creo una nueva Reservacion
        Reservacion objReservacion = new Reservacion();

        // Pidos los datos a insertar de la reservacion
        //String destino = JOptionPane.showInputDialog("Insert flight's destination");

        // Muestro la lista de vuelos para escoger uno y asignarlo a la reservacion
        String listaVuelos = objVueloController.getAll(this.objVueloModel.findAll());
        int id_vuelo = Integer.parseInt(JOptionPane.showInputDialog(null, listaVuelos + "\n Choose the flight ID for this booking"));

        // Muestro la lista de pasajeros para escoger uno y asignarlo a la reservacion
        String listaPasajeros = objPasajeroController.getAll(this.objPasajeroModel.findAll());
        int id_pasajero = Integer.parseInt(JOptionPane.showInputDialog(null, listaPasajeros + "\n Choose the passenger ID for this booking"));

        Date fecha_reservacion = Date.valueOf(JOptionPane.showInputDialog("Insert the booking date"));

        String asiento = JOptionPane.showInputDialog("Insert the booking seat");

        // llamo el método de validar asiento
        boolean flag = this.objReservacionModel.validateSeat(asiento, id_vuelo);

        if(!flag){
            // Asigno los valores al objeto Reservacion y sus métodos set
            objReservacion.setId_vuelo(id_vuelo);
            objReservacion.setId_pasajero(id_pasajero);
            objReservacion.setFecha_reservacion(fecha_reservacion);
            objReservacion.setAsiento(asiento);

            // Inserto el objeto Reservacion y lo casteo con el tipo de objeto Reservacion
            objReservacion = (Reservacion) this.objReservacionModel.insert(objReservacion);
        }
    }

    // Método para actualizar reservaciones
    public void update(){

        // Listamos los Vueloes
        String listReservaciones = this.getAll(this.objReservacionModel.findAll());

        //Pedimos el ID
        int idUpdateReservacion = Integer.parseInt(JOptionPane.showInputDialog(listReservaciones + "\n Choose the booking ID to edit "));

        // Verificamos el ID del Vuelo
        Reservacion objReservacion = (Reservacion) this.objReservacionModel.findById(idUpdateReservacion);

        if(objReservacion == null){
            JOptionPane.showMessageDialog(null,"The booking doesn't exist");
        }
        else{

            // Pidos los datos a insertar del vuelo

            Date fecha_reservacion = Date.valueOf(JOptionPane.showInputDialog("Insert the new booking date", objReservacion.getFecha_reservacion()));
            String asiento = JOptionPane.showInputDialog("Insert the new booking seat",objReservacion.getAsiento());

            // Muestro la lista de vuelos para escoger uno y asignarlo a la reservacion
            String listaVuelos = objVueloController.getAll(this.objVueloModel.findAll());
            int id_vuelo = Integer.parseInt(JOptionPane.showInputDialog(null, listaVuelos + "\n Choose the new flight ID for this booking ",objReservacion.getId_vuelo()));

            // Muestro la lista de pasajeros para escoger uno y asignarlo a la reservacion
            String listaPasajeros = objPasajeroController.getAll(this.objPasajeroModel.findAll());
            int id_pasajero = Integer.parseInt(JOptionPane.showInputDialog(null, listaPasajeros + "\n Choose the new passenger ID for this booking ",objReservacion.getId_pasajero()));

            // llamo el método de validar asiento
            boolean flag = this.objReservacionModel.validateSeat(asiento, id_vuelo);


            if(!flag){
                // Asigno los valores al objeto Reservacion y sus métodos set
                objReservacion.setId_vuelo(id_vuelo);
                objReservacion.setId_pasajero(id_pasajero);
                objReservacion.setFecha_reservacion(fecha_reservacion);
                objReservacion.setAsiento(asiento);

                // Retorno el Vuelo al modelo update del Vuelo
                this.objReservacionModel.update(objReservacion);
            }

        }
    }

    // Método para eliminar reservaciones
    public void delete() {

        String listBookingsString = "✈ Booking List \n\n";

        for (Object obj : this.objReservacionModel.findAll()) {
            Reservacion objReservacion = (Reservacion) obj;
            listBookingsString += objReservacion.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listBookingsString + "\n Enter the booking ID to delete"));
        Reservacion objReservacion = (Reservacion) this.objReservacionModel.findById(idDelete);

        if (objReservacion == null) {
            JOptionPane.showMessageDialog(null, " Booking not found.");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are your sure want to delete the booking #" + objReservacion.getId_reservacion());

            //Si el usuario escogió que si entonces eliminamos.
            if (confirm == 0) {
                this.objReservacionModel.delete(objReservacion);
            }
        }
    }



}

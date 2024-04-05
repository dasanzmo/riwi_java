package entity;

import java.sql.Date;

public class Reservacion {
    int id_reservacion;
    int id_pasajero;
    int id_vuelo;
    Date fecha_reservacion;
    String asiento;
    private Pasajero objPasajero;
    private Vuelo objVuelo;
    private Avion objAvion;

    public Reservacion() {
    }

    public Reservacion(int id_reservacion, int id_pasajero, int id_vuelo, Date fecha_reservacion, String asiento) {
        this.id_reservacion = id_reservacion;
        this.id_pasajero = id_pasajero;
        this.id_vuelo = id_vuelo;
        this.fecha_reservacion = fecha_reservacion;
        this.asiento = asiento;
    }

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public int getId_pasajero() {
        return id_pasajero;
    }

    public void setId_pasajero(int id_pasajero) {
        this.id_pasajero = id_pasajero;
    }

    public int getId_vuelo() {
        return id_vuelo;
    }

    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }

    public Date getFecha_reservacion() {
        return fecha_reservacion;
    }

    public void setFecha_reservacion(Date fecha_reservacion) {
        this.fecha_reservacion = fecha_reservacion;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public Pasajero getObjPasajero() {
        return objPasajero;
    }

    public void setObjPasajero(Pasajero objPasajero) {
        this.objPasajero = objPasajero;
    }

    public Vuelo getObjVuelo() {
        return objVuelo;
    }

    public void setObjVuelo(Vuelo objVuelo) {
        this.objVuelo = objVuelo;
    }

    public Avion getObjAvion() {
        return objAvion;
    }

    public void setObjAvion(Avion objAvion) {
        this.objAvion = objAvion;
    }

    @Override
    public String toString() {
        return "Booking number: " + id_reservacion + '\'' +
                ", Passenger: '" + objPasajero.getNombre() + " " + objPasajero.getApellido() + '\'' +
                ", Flight number: '" + id_vuelo + '\'' +
                ", Flight destination: '" + objVuelo.getDestino() + '\'' +
                ", Booking date: '" + fecha_reservacion + '\'' +
                ", Seat: '" + asiento + '\'' +
                ", Airplane: '" + objAvion.getModelo() + '\'';
    }
}

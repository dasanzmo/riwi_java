package entity;

import java.sql.Date;
import java.sql.Time;

public class Vuelo {
    int id_vuelo;
    String destino;
    Date fecha_salida;
    Time hora_salida;
    int id_avion;
    private Avion objAvion;

    public Vuelo() {
    }

    public Vuelo(int id_vuelo, String destino, Date fecha_salida, Time hora_salida, int id_avion) {
        this.id_vuelo = id_vuelo;
        this.destino = destino;
        this.fecha_salida = fecha_salida;
        this.hora_salida = hora_salida;
        this.id_avion = id_avion;
    }

    public int getId_vuelo() {
        return id_vuelo;
    }

    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Time getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(Time hora_salida) {
        this.hora_salida = hora_salida;
    }

    public int getId_avion() {
        return id_avion;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    public Avion getObjAvion() {
        return objAvion;
    }

    public void setObjAvion(Avion objAvion) {
        this.objAvion = objAvion;
    }

    @Override
    public String toString() {
        return "Flight Number: " + id_vuelo +
                ", Flight destination to: " + destino  +
                ", Departure date: " + fecha_salida +
                ", Departure time=" + hora_salida +
                ", Airplane: " + objAvion.getModelo();
    }
}

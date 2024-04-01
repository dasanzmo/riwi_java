package entity;

import java.sql.Time;
import java.util.Date;

public class Appointment {

    private int id_appointment;
    private int id_patient;
    private int id_doctor;
    private Date date_appointment;
    private Time appointment_time;
    private String reason;

    private Doctor objDoctor;
    private Patient objPatient;

    public Appointment() {
    }


    public Appointment(int id_appointment, int id_patient, int id_doctor, Date date_appointment, Time appointment_time, String reason) {
        this.id_appointment = id_appointment;
        this.id_patient = id_patient;
        this.id_doctor = id_doctor;
        this.date_appointment = date_appointment;
        this.appointment_time = appointment_time;
        this.reason = reason;
    }

    public int getId_appointment() {
        return id_appointment;
    }

    public void setId_appointment(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public Date getDate_appointment() {
        return date_appointment;
    }

    public void setDate_appointment(Date date_appointment) {
        this.date_appointment = date_appointment;
    }

    public Time getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(Time appointment_time) {
        this.appointment_time = appointment_time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Doctor getObjDoctor() {
        return objDoctor;
    }

    public void setObjDoctor(Doctor objDoctor) {
        this.objDoctor = objDoctor;
    }

    public Patient getObjPatient() {
        return objPatient;
    }

    public void setObjPatient(Patient objPatient) {
        this.objPatient = objPatient;
    }

    @Override
    public String toString() {
        return "Appointment: " +
                "Id: " + id_appointment + '\'' +
                ", Patient: '" + objPatient.getName() + '\'' +
                ", Doctor: '" + objDoctor.getName() + '\'' +
                ", Date appointment: '" + date_appointment + '\'' +
                ", Appointment time: '" + appointment_time + '\'' +
                ", Reason: '" + reason + '\'';
    }
}

package controller;

import entity.Appointment;
import entity.Doctor;
import model.AppointmentModel;
import model.DoctorModel;
import model.PatientModel;
import model.SpecialtyModel;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class AppointmentController {
    AppointmentModel objAppointmentModel;
    PatientModel objPatientModel;
    PatientController objPatientController;
    DoctorModel objDoctorModel;
    DoctorController objDoctorController;
    SpecialtyModel objSpecialtyModel;
    SpecialtyController objSpecialtyController;

    public AppointmentController() {
        this.objAppointmentModel = new AppointmentModel();
        this.objPatientModel = new PatientModel();
        this.objPatientController = new PatientController();
        this.objDoctorModel = new DoctorModel();
        this.objDoctorController = new DoctorController();
        this.objSpecialtyModel = new SpecialtyModel();
        this.objSpecialtyController = new SpecialtyController();
    }

    // Método para listar todos los doctores
    public void getAll() {
        String list = this.getAll(objAppointmentModel.findAll());

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Sobreescribir un método, metodos que se llaman igual pero hacen cosas diferentes
    public String getAll(List<Object> listObject) {

        String list = "Appointments list \n\n";

        // Iteramos sobre la lista que devuelve el método find All
        for (Object obj : listObject) {

            // Convertimos o casteamos el objeto tipo Object a un doctor
            Appointment objAppointment = (Appointment) obj;

            // Concatenamos la información
            list += objAppointment.toString() + "\n";

        }
        return list;

    }

    // Método para crear Citas
    public void create() {

        // Creo una nueva cita
        Appointment objAppointment = new Appointment();

        // Pidos los datos a insertar de la cita

        // Muestro la lista de doctores para escoger uno y asignarlo a la cita
        String listDoctors = objDoctorController.getAll(this.objDoctorModel.findAll());
        int id_doctor = Integer.parseInt(JOptionPane.showInputDialog(null, listDoctors + "\n Choose the doctor ID for this appointment"));

        // Muestro la lista de pacientes para escoger uno y asignarlo a la cita
        String listPatients = objPatientController.getAll(this.objPatientModel.findAll());
        int id_patient = Integer.parseInt(JOptionPane.showInputDialog(null, listPatients + "\n Choose the patient ID for this appointment"));

        Date appointment_date = Date.valueOf(JOptionPane.showInputDialog("Insert Appointment's date"));
        Time appointment_time = Time.valueOf(JOptionPane.showInputDialog("Insert Appointment's time"));
        String reason = JOptionPane.showInputDialog("Insert Appointment's reason");

        objAppointment.setId_doctor(id_doctor);
        objAppointment.setId_patient(id_patient);
        objAppointment.setDate_appointment(appointment_date);
        objAppointment.setAppointment_time(appointment_time);
        objAppointment.setReason(reason);

        // Inserto el objeto Doctor y lo casteo con el tipo de objeto Doctor
        objAppointment = (Appointment) this.objAppointmentModel.insert(objAppointment);

    }

    // Método para actualizar Citas
    public void update() {

        // Listamos las citas
        String listAppointments = this.getAll(this.objAppointmentModel.findAll());

        //Pedimos el ID
        int idUpdateAppointment = Integer.parseInt(JOptionPane.showInputDialog(listAppointments + "\n Choose the Appointment ID to edit "));

        // Verificamos el ID del doctor
        Appointment objAppointment = (Appointment) this.objAppointmentModel.findById(idUpdateAppointment);

        if (objAppointment == null) {
            JOptionPane.showMessageDialog(null, "The appointment doesn't exist");
        } else {
            // Muestro la lista de doctores para escoger uno y asignarlo a la cita
            String listDoctors = objDoctorController.getAll(this.objDoctorModel.findAll());
            int id_doctor = Integer.parseInt(JOptionPane.showInputDialog(null, listDoctors + "\n Choose the new doctor ID for this appointment", objAppointment.getId_doctor()));

            // Muestro la lista de pacientes para escoger uno y asignarlo a la cita
            String listPatients = objPatientController.getAll(this.objPatientModel.findAll());
            int id_patient = Integer.parseInt(JOptionPane.showInputDialog(null, listPatients + "\n Choose the new patient ID for this appointment", objAppointment.getId_patient()));

            Date appointment_date = Date.valueOf(JOptionPane.showInputDialog("Insert new Appointment's date", objAppointment.getDate_appointment()));
            Time appointment_time = Time.valueOf(JOptionPane.showInputDialog("Insert new Appointment's time", objAppointment.getAppointment_time()));
            String reason = JOptionPane.showInputDialog("Insert new Appointment's reason", objAppointment.getReason());

            objAppointment.setId_doctor(id_doctor);
            objAppointment.setId_patient(id_patient);
            objAppointment.setDate_appointment(appointment_date);
            objAppointment.setAppointment_time(appointment_time);
            objAppointment.setReason(reason);

            // Retorno el Doctor al modelo update del Doctor
            this.objAppointmentModel.update(objAppointment);

        }
    }

    // Método para eliminar Citas
    public void delete() {

        String listAppointmentString = "Appointments List \n";

        for (Object obj : this.objAppointmentModel.findAll()) {
            Appointment objAppointment = (Appointment) obj;
            listAppointmentString += objAppointment.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAppointmentString + "\n Enter the appointment ID to delete"));
        Appointment objAppointment = (Appointment) this.objAppointmentModel.findById(idDelete);

        if (objAppointment == null) {
            JOptionPane.showMessageDialog(null, " Doctor not found.");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are your sure want to delete this Appointment: \n" + "# " + objAppointment.getId_appointment());

            //Si el usuario escogió que si entonces eliminamos.
            if (confirm == 0) {
                this.objAppointmentModel.delete(objAppointment);
            }
        }
    }

    // Método para consultar citas por fecha
    public void AppointmentsByDate() {

        String list = "Appointments By Date \n";

        Date appointment_date = Date.valueOf(JOptionPane.showInputDialog("Insert date to find appointments"));

        if (objAppointmentModel.findAppointmentByDate(appointment_date).isEmpty()) {
            list += "No appointments found in this date";
        } else {

            // Iteramos sobre la lista que devuelve el método find All
            for (Object obj : this.objAppointmentModel.findAppointmentByDate(appointment_date)) {

                // Convertimos o casteamos el objeto tipo Objetct a un coder
                Appointment objAppointment = (Appointment) obj;

                // Concatenamos la información
                list += objAppointment.toString() + "\n";

            }

        }

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }

}

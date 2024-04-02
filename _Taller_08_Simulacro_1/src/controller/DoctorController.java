package controller;

import entity.Doctor;
import entity.Patient;
import entity.Specialty;
import model.DoctorModel;
import model.SpecialtyModel;

import javax.print.Doc;
import javax.swing.*;
import java.sql.Date;
import java.util.List;

public class DoctorController {

    DoctorModel objDoctorModel;
    SpecialtyModel objSpecialtyModel;
    SpecialtyController objSpecialtyController;

    public DoctorController() {

        // Crear una instancia del model de Doctor
        this.objDoctorModel = new DoctorModel();

        // Crear una instancia del model de Especialidad
        this.objSpecialtyModel = new SpecialtyModel();

        // Crear una instancia del controlador de la especialidad
        this.objSpecialtyController = new SpecialtyController();

    }

    // Método para listar todos los doctores
    public void getAll() {
        String list = this.getAll(objDoctorModel.findAll());

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Sobreescribir un método, metodos que se llaman igual pero hacen cosas diferentes
    public String getAll(List<Object> listObject) {

        String list = "Doctors list \n";

        // Iteramos sobre la lista que devuelve el método find All
        for (Object obj : listObject) {

            // Convertimos o casteamos el objeto tipo Object a un doctor
            Doctor objDoctor = (Doctor) obj;

            // Concatenamos la información
            list += objDoctor.toString() + "\n";

        }
        return list;

    }

    // Método para crear Doctores
    public void create(){

        // Creo un uevo doctor
        Doctor objDoctor = new Doctor();

        // Pidos los datos a insertar del doctor
        String name = JOptionPane.showInputDialog("Insert Doctor's name");
        String last_name = JOptionPane.showInputDialog("Insert Doctor's last name");

        // Muestro la lista de especialidades para escoger una y asignarla al doctor
        String listSpecialty = objSpecialtyController.getAll(this.objSpecialtyModel.findAll());
        int id_specialty = Integer.parseInt(JOptionPane.showInputDialog(null, listSpecialty + "\n Choose the specialty ID for this doctor"));

        objDoctor.setName(name);
        objDoctor.setLast_name(last_name);
        objDoctor.setId_specialty(id_specialty);

        // Inserto el objeto Doctor y lo casteo con el tipo de objeto Doctor
        objDoctor = (Doctor) this.objDoctorModel.insert(objDoctor);

    }

    // Método para actualizar Doctores
    public void update(){

        // Listamos los doctores
        String listDoctors = this.getAll(this.objDoctorModel.findAll());

        //Pedimos el ID
        int idUpdateDoctor = Integer.parseInt(JOptionPane.showInputDialog(listDoctors + "\n Choose the Doctor ID to edit "));

        // Verificamos el ID del doctor
        Doctor objDoctor = (Doctor) this.objDoctorModel.findById(idUpdateDoctor);

        if(objDoctor == null){
            JOptionPane.showMessageDialog(null,"The doctor doesn't exist");
        }
        else{
            String name = JOptionPane.showInputDialog(null,"Enter the new doctor name:", objDoctor.getName());
            String last_name = JOptionPane.showInputDialog(null,"Enter the new doctor last name:", objDoctor.getLast_name());

            // LLamo la lista de especialidades para mostrar al usuario el Id de la especialidad que quiere actualizar en el doctor
            String listSpecialty = objSpecialtyController.getAll(this.objSpecialtyModel.findAll());
            int id_specialty = Integer.parseInt(JOptionPane.showInputDialog(null,listSpecialty + "\n Enter the new doctor specialty:", objDoctor.getId_specialty()));

            // Asigno los datos ingresados al doctor
            objDoctor.setName(name);
            objDoctor.setLast_name(last_name);
            objDoctor.setId_specialty(id_specialty);

            // Retorno el Doctor al modelo update del Doctor
            this.objDoctorModel.update(objDoctor);

        }
    }

    // Método para eliminar Doctores
    public void delete() {

        String listDoctorString = "Doctors List \n";

        for (Object obj : this.objDoctorModel.findAll()) {
            Doctor objDoctor = (Doctor) obj;
            listDoctorString += objDoctor.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listDoctorString + "\n Enter the doctor ID to delete"));
        Doctor objDoctor = (Doctor) this.objDoctorModel.findById(idDelete);

        if (objDoctor == null) {
            JOptionPane.showMessageDialog(null, " Doctor not found.");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are your sure want to delete the doctor: \n" + objDoctor.getName());

            //Si el usuario escogió que si entonces eliminamos.
            if (confirm == 0) {
                this.objDoctorModel.delete(objDoctor);
            }
        }
    }

    // Método para consultar doctores por especialidad
    public void doctorsBySpecialty() {

        String list = "";

        String listSpecialties = this.objSpecialtyController.getAll(this.objSpecialtyModel.findAll());
        int id = Integer.parseInt(JOptionPane.showInputDialog(listSpecialties + "\n Insert ID Specialty to find patient"));

        Specialty objSpecialty = (Specialty) this.objSpecialtyModel.findById(id);

        if (objSpecialty == null) {
            list += "Specialty not found";
        } else {
            list = "Doctors by Specialty " + objSpecialty.getName() + "\n";

            // Iteramos sobre la lista que devuelve el método find All
            for (Object obj : this.objDoctorModel.findDoctorBySpecialty(id)) {

                // Convertimos o casteamos el objeto tipo Objetct a un patient
                Doctor objDoctor = (Doctor) obj;

                // Concatenamos la información
                list += objDoctor.toString() + "\n";

            }

        }

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }

}

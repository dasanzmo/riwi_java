package controller;

import entity.Doctor;
import entity.Specialty;
import model.DoctorModel;
import model.SpecialtyModel;

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

}

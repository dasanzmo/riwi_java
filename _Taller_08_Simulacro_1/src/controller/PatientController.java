package controller;

import entity.Patient;
import entity.Patient;
import entity.Patient;
import entity.Patient;
import model.PatientModel;

import javax.swing.*;
import java.sql.Date;
import java.util.List;

public class PatientController {

    PatientModel objPatientModel;

    public PatientController() {
        this.objPatientModel = new PatientModel();
    }

    // Método para listar todos los doctores
    public void getAll() {
        String list = this.getAll(objPatientModel.findAll());

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Sobreescribir un método, metodos que se llaman igual pero hacen cosas diferentes
    public String getAll(List<Object> listObject) {

        String list = "Patients list \n";

        // Iteramos sobre la lista que devuelve el método find All
        for (Object obj : listObject) {

            // Convertimos o casteamos el objeto tipo Object a un doctor
            Patient objPatient = (Patient) obj;

            // Concatenamos la información
            list += objPatient.toString() + "\n";

        }
        return list;

    }

    // Método para crear Pacientes
    public void create(){

        // Creo un nuevo paciente
        Patient objPatient = new Patient();

        // Pidos los datos a insertar del paciente
        String name = JOptionPane.showInputDialog("Insert Patient's name");
        String last_name = JOptionPane.showInputDialog("Insert Patient's last name");
        Date birthdate = Date.valueOf(JOptionPane.showInputDialog("Insert Patient's birthdate"));
        String identify_document = JOptionPane.showInputDialog("Insert Patient's identify document");

        objPatient.setName(name);
        objPatient.setLast_name(last_name);
        objPatient.setBirthdate(birthdate);
        objPatient.setIdentity_document(identify_document);

        // Inserto el objeto Patient y lo casteo con el tipo de objeto Patient
        objPatient = (Patient) this.objPatientModel.insert(objPatient);

    }

    // Método para actualizar Pacientes
    public void update(){

        // Listamos los doctores
        String listPatients = this.getAll(this.objPatientModel.findAll());

        //Pedimos el ID
        int idUpdatePatient = Integer.parseInt(JOptionPane.showInputDialog(listPatients + "\n Choose the Patient ID to edit "));

        // Verificamos el ID del doctor
        Patient objPatient = (Patient) this.objPatientModel.findById(idUpdatePatient);

        if(objPatient == null){
            JOptionPane.showMessageDialog(null,"The patient doesn't exist");
        }
        else{
            String name = JOptionPane.showInputDialog(null,"Insert the new Patient's name", objPatient.getName());
            String last_name = JOptionPane.showInputDialog(null,"Insert the new Patient's last name", objPatient.getLast_name());
            Date birthdate = Date.valueOf(JOptionPane.showInputDialog(null,"Insert the new Patient's birthdate", objPatient.getBirthdate()));
            String identify_document = JOptionPane.showInputDialog(null,"Insert the new Patient's identify document", objPatient.getIdentity_document());

            // Asigno los datos ingresados al paciente
            objPatient.setName(name);
            objPatient.setLast_name(last_name);
            objPatient.setBirthdate(birthdate);
            objPatient.setIdentity_document(identify_document);

            // Retorno el Patient al modelo update del Patient
            this.objPatientModel.update(objPatient);

        }
    }

    // Método para eliminar Patientes
    public void delete() {

        String listPatientString = "Patients List \n";

        for (Object obj : this.objPatientModel.findAll()) {
            Patient objPatient = (Patient) obj;
            listPatientString += objPatient.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPatientString + "\n Enter the patient ID to delete"));
        Patient objPatient = (Patient) this.objPatientModel.findById(idDelete);

        if (objPatient == null) {
            JOptionPane.showMessageDialog(null, " Patient not found.");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are your sure want to delete the patient: \n" + objPatient.getName());

            //Si el usuario escogió que si entonces eliminamos.
            if (confirm == 0) {
                this.objPatientModel.delete(objPatient);
            }
        }
    }
}

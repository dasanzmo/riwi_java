package controller;

import entity.Doctor;
import entity.Specialty;
import model.SpecialtyModel;

import javax.swing.*;
import java.util.List;

public class SpecialtyController {
    SpecialtyModel objSpecialtyModel;
    public void Specialty_Controller(){
        this.objSpecialtyModel = new SpecialtyModel();
    }

    // Método para listar todas las especialidades
    public void getAll() {
        String list = this.getAll(objSpecialtyModel.findAll());

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Sobreescribir un método, metodos que se llaman igual pero hacen cosas diferentes
    public String getAll(List<Object> listObject) {

        String list = "Specialty list \n";

        // Iteramos sobre la lista que devuelve el método find All
        for (Object obj : listObject) {

            // Convertimos o casteamos el objeto tipo Object a un doctor
            Specialty objSpecialty = (Specialty) obj;

            // Concatenamos la información
            list += objSpecialty.toString() + "\n";

        }
        return list;

    }

    // Método para crear Especialidades
    public void create(){

        // Creo una nueva especialidad
        Specialty objSpecialty = new Specialty();

        // Pidos los datos a insertar del doctor
        String name = JOptionPane.showInputDialog("Insert Specialty's name");
        String decription = JOptionPane.showInputDialog("Insert Specialty's description");

        objSpecialty.setName(name);
        objSpecialty.setDescription(decription);

        // Inserto el objeto Especialidad y lo casteo con el tipo de objeto Especialidad
        objSpecialty = (Specialty) this.objSpecialtyModel.insert(objSpecialty);

    }

}

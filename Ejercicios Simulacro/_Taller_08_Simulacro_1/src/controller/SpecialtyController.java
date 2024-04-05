package controller;

import entity.Specialty;
import entity.Specialty;
import entity.Specialty;
import model.SpecialtyModel;

import javax.swing.*;
import java.util.List;

public class SpecialtyController {

    SpecialtyModel objSpecialtyModel ;

    public SpecialtyController(){
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

            // Convertimos o casteamos el objeto tipo Object a un specialty
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

        // Pidos los datos a insertar del specialty
        String name = JOptionPane.showInputDialog("Insert Specialty's name");
        String decription = JOptionPane.showInputDialog("Insert Specialty's description");

        objSpecialty.setName(name);
        objSpecialty.setDescription(decription);

        // Inserto el objeto Especialidad y lo casteo con el tipo de objeto Especialidad
        objSpecialty = (Specialty) this.objSpecialtyModel.insert(objSpecialty);

    }

    // Método para actualizar Especialidades
    public void update(){

        // Listamos las espeicalidades
        String listSpecialties = this.getAll(this.objSpecialtyModel.findAll());

        //Pedimos el ID
        int idUpdateSpecialty = Integer.parseInt(JOptionPane.showInputDialog(listSpecialties + "\n Choose the Specialty ID to edit "));

        // Verificamos el ID de la especialidad
        Specialty objSpecialty = (Specialty) this.objSpecialtyModel.findById(idUpdateSpecialty);

        if(objSpecialty == null){
            JOptionPane.showMessageDialog(null,"The specialty doesn't exist");
        }
        else{
            String name = JOptionPane.showInputDialog(null,"Enter the new specialty name:", objSpecialty.getName());
            String last_name = JOptionPane.showInputDialog(null,"Enter the new specialty description:", objSpecialty.getDescription());

            // Asigno los datos ingresados al specialty
            objSpecialty.setName(name);
            objSpecialty.setDescription(last_name);

            // Retorno el Specialty al modelo update del Specialty
            this.objSpecialtyModel.update(objSpecialty);

        }
    }

    // Método para eliminar Specialtyes
    public void delete() {

        String listSpecialtyString = "Specialties List \n";

        for (Object obj : this.objSpecialtyModel.findAll()) {
            Specialty objSpecialty = (Specialty) obj;
            listSpecialtyString += objSpecialty.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listSpecialtyString + "\n Enter the specialty ID to delete"));
        Specialty objSpecialty = (Specialty) this.objSpecialtyModel.findById(idDelete);

        if (objSpecialty == null) {
            JOptionPane.showMessageDialog(null, " Specialty not found.");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are your sure want to delete the specialty: \n" + objSpecialty.getName());

            //Si el usuario escogió que si entonces eliminamos.
            if (confirm == 0) {
                this.objSpecialtyModel.delete(objSpecialty);
            }
        }
    }

}

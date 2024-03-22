package controller;

import entity.Coder;
import model.CoderModel;

import javax.swing.*;
import java.util.List;

public class CoderController {

    CoderModel objCoderModel;

    public CoderController() {

        // Crear una instancia del model
        this.objCoderModel = new CoderModel();

    }

    // Método para listar todos los coder
    public void getAll() {
        String list = this.getAll(objCoderModel.findAll());

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Sobreescribir un método, metodos que se llaman igual pero hacen cosas diferentes
    public String getAll(List<Object> listObject) {

        String list = "Coders list \n";

        // Iteramos sobre la lista que devuelve el método find All
        for (Object obj : listObject) {

            // Convertimos o casteamos el objeto tipo Object a un coder
            Coder objCoder = (Coder) obj;

            // Concatenamos la información
            list += objCoder.toString() + "\n";

        }
        return list;

    }

    // Método para crear coders
    public void create() {
        Coder objCoder = new Coder();

        String name = JOptionPane.showInputDialog("Insert name: ");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Insert age: "));
        String clan = JOptionPane.showInputDialog("Insert clan: ");

        objCoder.setName(name);
        objCoder.setAge(age);
        objCoder.setClan(clan);

        objCoder = (Coder) this.objCoderModel.insert(objCoder);

        JOptionPane.showMessageDialog(null, objCoder.toString());
    }

    // Método para eliminar coders
    public void delete() {
        String listCoderString = "🤷‍♂️ CODER LIST \n";

        for (Object obj : this.objCoderModel.findAll()) {
            Coder objCoder = (Coder) obj;
            listCoderString += objCoder.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listCoderString + " Enter the ID of the coder to delete"));
        Coder objCoder = (Coder) this.objCoderModel.findById(idDelete);

        if (objCoder == null) {
            JOptionPane.showMessageDialog(null, " Coder not found.");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are your sure want to delete  the coder: \n" + objCoder.toString());

            //Si el usuario escogió que si entonces eliminamos.
            if (confirm == 0) {
                this.objCoderModel.delete(objCoder);
            }
        }
    }

    // Método para actualizar coders
    public void update() {

        //Listamos
        String listCoders = this.getAll(this.objCoderModel.findAll());

        //Pedimos el ID
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listCoders + "\n Insert the coder ID to edit "));

        //Verificamos el ID
        Coder objCoder = (Coder) this.objCoderModel.findById(idUpdate);

        if (objCoder == null) {
            JOptionPane.showMessageDialog(null, "Coder not found");
        } else {
            String name = JOptionPane.showInputDialog(null, "Enter new coder name", objCoder.getName());
            String clan = JOptionPane.showInputDialog(null, "Enter new coder clan", objCoder.getClan());
            int age = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new coder age", String.valueOf(objCoder.getAge())));

            objCoder.setName(name);
            objCoder.setAge(age);
            objCoder.setClan(clan);

            this.objCoderModel.update(objCoder);
        }

    }

    public void getCoderByName() {
        String list = "Coder list By Name \n";

        String nameSearch = JOptionPane.showInputDialog("Insert name to find");

        if (objCoderModel.findByName(nameSearch).isEmpty()) {
            list += "No coders found";
        } else {

            // Iteramos sobre la lista que devuelve el método find All
            for (Object obj : this.objCoderModel.findByName(nameSearch)) {

                // Convertimos o casteamos el objeto tipo Objetct a un coder
                Coder objCoder = (Coder) obj;

                // Concatenamos la información
                list += objCoder.toString() + "\n";

            }

        }

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }
}

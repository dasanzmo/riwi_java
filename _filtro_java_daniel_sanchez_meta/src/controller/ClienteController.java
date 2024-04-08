package controller;

import com.mysql.cj.xdevapi.Client;
import entity.Cliente;
import model.ClienteModel;

import javax.swing.*;
import java.util.List;

public class ClienteController {

    ClienteModel objClienteModel;

    public ClienteController() {
        this.objClienteModel = new ClienteModel();
    }

    // Método para listar todos los Clientes
    public void getAll() {
        String list = this.getAll(objClienteModel.findAll());

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Sobreescribir un método, metodos que se llaman igual pero hacen cosas diferentes
    public String getAll(List<Object> listObject) {

        String list = "😎 Clients list: \n\n";

        // Iteramos sobre la lista que devuelve el método find All
        for (Object obj : listObject) {

            // Convertimos o casteamos el objeto tipo Object a un Clientes
            Cliente objCliente = (Cliente) obj;

            // Concatenamos la información
            list += objCliente.toString() + "\n";

        }
        return list;

    }

    // Método para crear clientes
    public void create(){

        // Creo un nuevo pasajero
        Cliente objCliente = new Cliente();

        // Pidos los datos a insertar del paciente
        String nombre = JOptionPane.showInputDialog("Insert client's name");
        String apellido = JOptionPane.showInputDialog("Insert client's last name");
        String email = JOptionPane.showInputDialog("Insert client's email");

        objCliente.setNombre(nombre);
        objCliente.setApellido(apellido);
        objCliente.setEmail(email);

        // Inserto el objeto Cliente y lo casteo con el tipo de objeto Cliente
        objCliente = (Cliente) this.objClienteModel.insert(objCliente);

    }

    // Método para actualizar clientes
    public void update(){

        // Listamos los pasajeros
        String listClientes = this.getAll(this.objClienteModel.findAll());

        //Pedimos el ID
        int idUpdateCliente = Integer.parseInt(JOptionPane.showInputDialog(listClientes + "\n Choose the client ID to edit "));

        // Verificamos el ID del pasajero
        Cliente objCliente = (Cliente) this.objClienteModel.findById(idUpdateCliente);

        if(objCliente == null){
            JOptionPane.showMessageDialog(null,"The client doesn't exist");
        }
        else{
            String nombre = JOptionPane.showInputDialog(null,"Insert the new client's name", objCliente.getNombre());
            String apellido = JOptionPane.showInputDialog(null,"Insert the new client's last name", objCliente.getApellido());
            String email = JOptionPane.showInputDialog(null,"Insert the new client's identity document", objCliente.getEmail());

            // Asigno los datos ingresados al cliente
            objCliente.setNombre(nombre);
            objCliente.setApellido(apellido);
            objCliente.setEmail(email);

            // Retorno el Cliente al modelo update del Cliente
            this.objClienteModel.update(objCliente);

        }
    }

    // Método para eliminar clientes
    public void delete() {

        String listClientes = this.getAll(this.objClienteModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listClientes + "\n Enter the client ID to delete"));
        Cliente objCliente = (Cliente) this.objClienteModel.findById(idDelete);

        if (objCliente == null) {
            JOptionPane.showMessageDialog(null, " Client not found.");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are your sure want to delete the client: \n" + objCliente.getNombre());

            //Si el usuario escogió que si entonces eliminamos.
            if (confirm == 0) {
                this.objClienteModel.delete(objCliente);
            }
        }
    }

    // Método para buscar clientes por nombre
    public void getClientsByName() {
        String list = "Clients list By Name \n";

        String nameSearch = JOptionPane.showInputDialog("Insert client name to find");

        if (objClienteModel.findClientsByName(nameSearch).isEmpty()) {
            list += "No clients found with '" + nameSearch + "'";
        } else {

            // Iteramos sobre la lista que devuelve el método find All
            for (Object obj : this.objClienteModel.findClientsByName(nameSearch)) {

                // Convertimos o casteamos el objeto tipo Objetct a un coder
                Cliente objCliente = (Cliente) obj;

                // Concatenamos la información
                list += objCliente.toString() + "\n";

            }

        }

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }
}

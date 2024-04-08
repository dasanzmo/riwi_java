package controller;

import entity.Producto;
import entity.Producto;
import model.ProductoModel;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class ProductoController {

    ProductoModel objProductoModel;

    public ProductoController() {
        this.objProductoModel = new ProductoModel();
    }

    // Método para listar todos los productos
    public void getAll() {
        String list = this.getAll(objProductoModel.findAll());

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }

    // Método para crear productos
    public void create(){

        // Creo un nuevo Producto
        Producto objProducto = new Producto();

        // Pidos los datos a insertar del producto

        String nombre = JOptionPane.showInputDialog("Insert product name");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Insert product price"));
        int stock = Integer.parseInt((JOptionPane.showInputDialog("Insert product stock")));

        // Muestro la lista de tiendas para escoger una y asignarla al vuelo
        String listaTiendas = this.getAll(this.objProductoModel.findAll());
        int id_tienda = Integer.parseInt(JOptionPane.showInputDialog(null, listaTiendas + "\n Choose the store ID for this product", objProducto.getId_tienda()));

        objProducto.setNombre(nombre);
        objProducto.setPrecio(precio);
        objProducto.setStock(stock);
        objProducto.setId_tienda(id_tienda);

        // Inserto el objeto Producto y lo casteo con el tipo de objeto Producto
        objProducto = (Producto) this.objProductoModel.insert(objProducto);

    }

    // Método para actualizar productos
    public void update(){

        // Listamos los Productos
        String listProductos = this.getAll(this.objProductoModel.findAll());

        //Pedimos el ID
        int idUpdateProducto = Integer.parseInt(JOptionPane.showInputDialog(listProductos + "\n Choose the flight ID to edit "));

        // Verificamos el ID del Producto
        Producto objProducto = (Producto) this.objProductoModel.findById(idUpdateProducto);

        if(objProducto == null){
            JOptionPane.showMessageDialog(null,"The product doesn't exist");
        }
        else{

            // Pidos los datos a insertar del producto

            String nombre = JOptionPane.showInputDialog("Insert new product name", objProducto.getNombre());
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Insert new product price", objProducto.getPrecio()));
            int stock = Integer.parseInt((JOptionPane.showInputDialog("Insert new product stock", objProducto.getStock())));

            // Muestro la lista de tiendas para escoger una y asignarla al vuelo
            String listaTiendas = this.getAll(this.objProductoModel.findAll());
            int id_tienda = Integer.parseInt(JOptionPane.showInputDialog(null, listaTiendas + "\n Choose the store ID for this product", objProducto.getId_tienda()));

            objProducto.setNombre(nombre);
            objProducto.setPrecio(precio);
            objProducto.setStock(stock);
            objProducto.setId_tienda(id_tienda);

            // Retorno el Producto al modelo update del Producto
            this.objProductoModel.update(objProducto);

        }
    }

    // Método para eliminar Productos
    public void delete() {

        String listProductoString = "✈ Products List \n\n";

        for (Object obj : this.objProductoModel.findAll()) {
            Producto objProducto = (Producto) obj;
            listProductoString += objProducto.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listProductoString + "\n Enter the product ID to delete"));
        Producto objProducto = (Producto) this.objProductoModel.findById(idDelete);

        if (objProducto == null) {
            JOptionPane.showMessageDialog(null, " Producto not found.");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are your sure want to delete the product: \n" + objProducto.getNombre());

            //Si el usuario escogió que si entonces eliminamos.
            if (confirm == 0) {
                this.objProductoModel.delete(objProducto);
            }
        }
    }

    //Sobreescribir un método, metodos que se llaman igual pero hacen cosas diferentes
    public String getAll(List<Object> listObject) {

        String list = "✈ Products list: \n\n";

        // Iteramos sobre la lista que devuelve el método find All
        for (Object obj : listObject) {

            // Convertimos o casteamos el objeto tipo Object a un Producto
            Producto objProducto = (Producto) obj;

            // Concatenamos la información
            list += objProducto.toString() + "\n";

        }
        return list;

    }

    // Método para buscar clientes por nombre
    public void getProductsByName() {
        String list = "Products list By Name \n";

        String nameSearch = JOptionPane.showInputDialog("Insert product name to find");

        if (objProductoModel.findProductsByName(nameSearch).isEmpty()) {
            list += "No products found with this name: '" + nameSearch + "'";
        } else {

            // Iteramos sobre la lista que devuelve el método find All
            for (Object obj : this.objProductoModel.findProductsByName(nameSearch)) {

                // Convertimos o casteamos el objeto tipo Objetct a un coder
                Producto objProducto = (Producto) obj;

                // Concatenamos la información
                list += objProducto.toString() + "\n";

            }

        }

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }

    // Método para actualizar productos
    public void updateStock(){

        // Listamos los Productos
        String listProductos = this.getAll(this.objProductoModel.findAll());

        //Pedimos el ID
        int idUpdateProducto = Integer.parseInt(JOptionPane.showInputDialog(listProductos + "\n Choose the flight ID to edit "));

        // Verificamos el ID del Producto
        Producto objProducto = (Producto) this.objProductoModel.findById(idUpdateProducto);

        if(objProducto == null){
            JOptionPane.showMessageDialog(null,"The product doesn't exist");
        }
        else{

            // Pidos los datos a insertar del producto

            String nombre = JOptionPane.showInputDialog("Insert new product name", objProducto.getNombre());
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Insert new product price", objProducto.getPrecio()));
            int stock = Integer.parseInt((JOptionPane.showInputDialog("Insert new product stock", objProducto.getStock())));

            // Muestro la lista de tiendas para escoger una y asignarla al vuelo
            String listaTiendas = this.getAll(this.objProductoModel.findAll());
            int id_tienda = Integer.parseInt(JOptionPane.showInputDialog(null, listaTiendas + "\n Choose the store ID for this product", objProducto.getId_tienda()));

            objProducto.setNombre(nombre);
            objProducto.setPrecio(precio);
            objProducto.setStock(stock);
            objProducto.setId_tienda(id_tienda);

            // Retorno el Producto al modelo update del Producto
            this.objProductoModel.update(objProducto);

        }
    }
}

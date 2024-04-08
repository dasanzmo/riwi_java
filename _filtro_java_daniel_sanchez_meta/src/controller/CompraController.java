package controller;

import entity.Compra;
import entity.Producto;
import model.ClienteModel;
import model.CompraModel;
import model.ProductoModel;

import javax.swing.*;
import java.sql.Date;
import java.util.List;



public class CompraController {

    CompraModel objCompraModel;
    ProductoModel objProductoModel;
    ProductoController objProductoController;
    ClienteModel objClienteModel;
    ClienteController objClienteController;

    public CompraController() {
        this.objCompraModel = new CompraModel();
        this.objProductoModel = new ProductoModel();
        this.objProductoController = new ProductoController();
        this.objClienteModel = new ClienteModel();
        this.objClienteController = new ClienteController();
    }

    // Método para listar todas las compras
    public void getAll() {
        String list = this.getAll(objCompraModel.findAll());

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Sobreescribir un método, metodos que se llaman igual pero hacen cosas diferentes
    public String getAll(List<Object> listObject) {

        String list = "📅 Shopping list: \n\n";

        // Iteramos sobre la lista que devuelve el método find All
        for (Object obj : listObject) {

            // Convertimos o casteamos el objeto tipo Object a una reserva
            Compra objCompra = (Compra) obj;

            // Concatenamos la información
            list += objCompra.toString() + "\n";

        }
        return list;

    }

    // Método para crear compras
    public void create(){

        // Creo una nueva Compra
        Compra objCompra = new Compra();
        Producto objProducto = new Producto();

        // Pidos los datos a insertar de la compra

        // Muestro la lista de productos para escoger uno y asignarlo a la compra
        String listaProductos = objProductoController.getAll(this.objProductoModel.findAll());
        int id_producto = Integer.parseInt(JOptionPane.showInputDialog(null, listaProductos + "\n Choose the product ID for this purchase"));

        // Muestro la lista de clientes para escoger uno y asignarlo a la compra
        String listaClientes = objClienteController.getAll(this.objClienteModel.findAll());
        int id_cliente = Integer.parseInt(JOptionPane.showInputDialog(null, listaClientes + "\n Choose the client ID for this purchase"));

        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Insert product quantity"));

        // llamo el método de validar stock
        boolean flag = this.objCompraModel.validateStock(cantidad, id_producto);

        if(!flag){
            // Asigno los valores al objeto Compra y sus métodos set
            objCompra.setId_producto(id_producto);
            objCompra.setId_cliente(id_cliente);
            objCompra.setCantidad(cantidad);

            // Inserto el objeto Compra y lo casteo con el tipo de objeto Compra
            objCompra = (Compra) this.objCompraModel.insert(objCompra);
            this.objCompraModel.updateStock(objCompra);

            // Capturo de la información de la compra creada
            Compra objCompraFactura = (Compra) this.objCompraModel.findById(objCompra.getId_compra());

            // Llamo el método para generar la factura
            this.generateInvoice(objCompraFactura);

        }
    }

    // Método para actualizar compras
    public void update(){

        // Listamos los Vueloes
        String listCompras = this.getAll(this.objCompraModel.findAll());

        //Pedimos el ID
        int idUpdateCompra = Integer.parseInt(JOptionPane.showInputDialog(listCompras + "\n Choose the purchase ID to edit "));

        // Verificamos el ID del Vuelo
        Compra objCompra = (Compra) this.objCompraModel.findById(idUpdateCompra);

        if(objCompra == null){
            JOptionPane.showMessageDialog(null,"The purchase doesn't exist");
        }
        else{

            // Pidos los datos a insertar de la compra

            // Muestro la lista de productos para escoger uno y asignarlo a la compra
            String listaProductos = objProductoController.getAll(this.objProductoModel.findAll());
            int id_producto = Integer.parseInt(JOptionPane.showInputDialog(null, listaProductos + "\n Choose the flight ID for this purcjase"));

            // Muestro la lista de clientes para escoger uno y asignarlo a la compra
            String listaClientes = objClienteController.getAll(this.objClienteModel.findAll());
            int id_cliente = Integer.parseInt(JOptionPane.showInputDialog(null, listaClientes + "\n Choose the passenger ID for this purcjase"));

            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Insert the new product quantity"));

            // llamo el método de validar stock
            boolean flag = this.objCompraModel.validateStock(cantidad, id_producto);


            if(!flag){

                // Asigno los valores al objeto Compra y sus métodos set
                objCompra.setId_producto(id_producto);
                objCompra.setId_cliente(id_cliente);
                objCompra.setCantidad(cantidad);

                // Retorno el Vuelo al modelo update del Vuelo
                this.objCompraModel.update(objCompra);
            }

        }
    }

    // Método para eliminar compras
    public void delete() {

        String listBookingsString = "✈ Purchases List \n\n";

        for (Object obj : this.objCompraModel.findAll()) {
            Compra objCompra = (Compra) obj;
            listBookingsString += objCompra.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listBookingsString + "\n Enter the purchase ID to delete"));
        Compra objCompra = (Compra) this.objCompraModel.findById(idDelete);

        if (objCompra == null) {
            JOptionPane.showMessageDialog(null, " Purchase not found.");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are your sure want to delete the purchase #" + objCompra.getId_compra());

            //Si el usuario escogió que si entonces eliminamos.
            if (confirm == 0) {
                this.objCompraModel.delete(objCompra);
            }
        }
    }

    // Método para consultar citas por fecha
    public void PurchasesByProduct() {

        String list = "Purchases By Product \n";

        // Muestro la lista de productos para escoger uno y asignarlo a la compra
        String listaProductos = objProductoController.getAll(this.objProductoModel.findAll());
        int id_producto = Integer.parseInt(JOptionPane.showInputDialog(null, listaProductos + "\n Choose the product ID to find purchases"));

        if (objCompraModel.findPurchasesByProduct(id_producto).isEmpty()) {
            list += "No purchases found with this product";
        } else {

            // Iteramos sobre la lista que devuelve el método find All
            for (Object obj : this.objCompraModel.findPurchasesByProduct(id_producto)) {

                // Convertimos o casteamos el objeto tipo Objetct a una compra
                Compra objCompra = (Compra) obj;

                // Concatenamos la información
                list += objCompra.toString() + "\n";

            }

        }

        // Mostramos toda la lista
        JOptionPane.showMessageDialog(null, list);
    }

    public void generateInvoice(Object object){
        Compra objCompra = (Compra) object;

        double subtotal = objCompra.getObjProducto().getPrecio() * objCompra.getCantidad();
        double iva = (subtotal * 19) /100;
        double total = subtotal + iva;

        JOptionPane.showMessageDialog(null,
                "---------------- PURCHASE #" + objCompra.getId_compra() + " INVOICE -----------------" + "\n" +
                        "Purchase Date : " + objCompra.getFecha_compra() + "\n" +
                        "Products: " + objCompra.getObjProducto().getNombre() + "\n" +
                        "Price: " + objCompra.getObjProducto().getPrecio() + "\n" +
                        "Quantity: " + objCompra.getCantidad() + "\n" +
                        "Client name: " + objCompra.getObjCliente().getNombre() + "\n" +
                        "Client last name: " + objCompra.getObjCliente().getApellido() + "\n" +
                        "Client email: " + objCompra.getObjCliente().getEmail() + "\n" +
                        "Store: " + objCompra.getObjTienda().getNombre() + "\n" +
                        "Store Location: " + objCompra.getObjTienda().getUbicacion() + "\n" +
                        "-------------------------------------------------------------" + "\n" +
                        "SUBTOTAL: $" + subtotal + "\n" +
                        "-------------------------------------------------------------" + "\n" +
                        "TOTAL: $" + total

        );
    }
}

import controller.ClienteController;
import controller.CompraController;
import controller.ProductoController;
import database.ConfigDB;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ConfigDB.openConnection();

        ClienteController objClienteController = new ClienteController();
        ProductoController objProductoController = new ProductoController();
        CompraController objCompraController = new CompraController();

        String option;

        do {
            option = JOptionPane.showInputDialog("""
                        ------ 👨🏻‍✈️ D'MODA OUTLET 👩🏻‍✈️ ------ \n
                        1. 🛒 Admin Purchases
                        2. 🧴 Admin Products
                        3. 👩🏻 Admin Clients
                        4. 🔎 Search
                        5. 🚪 Exit
                        \n
                    """);

            switch (option) {

                // Passengers Menu
                case "1":
                    String optionA;
                    do {
                        optionA = JOptionPane.showInputDialog("""
                                    ----- SHOPPING MENU ----- \n
                                    1. 🛒 List purchases
                                    2. ➕ Add purchase
                                    3. ✏ Update purchase
                                    4. ❌ Delete purchase
                                    5. 🔎 Search purchases by product
                                    6. 👈 Go Back
                                    \n
                                """);

                        // Switch Shopping Admin
                        switch (optionA) {
                            case "1":
                                objCompraController.getAll();
                                break;
                            case "2":
                                objCompraController.create();
                                break;
                            case "3":
                                objCompraController.update();
                                break;
                            case "4":
                                objCompraController.delete();
                                break;
                            case "5":
                                objCompraController.PurchasesByProduct();
                                break;
                        }
                    } while (!optionA.equals("6"));
                    break;

                case "2":
                    String optionB;
                    do {
                        optionB = JOptionPane.showInputDialog("""
                                    ----- PRODUCTS MENU ----- \n
                                    1. 🧴 List Products
                                    2. ➕ Add Product
                                    3. ✏ Update Product
                                    4. ❌ Delete Product
                                    5. 🔎 Search products by name
                                    6. 👈 Go Back
                                    \n
                                """);

                        // Switch Products Admin
                        switch (optionB) {
                            case "1":
                                objProductoController.getAll();
                                break;
                            case "2":
                                objProductoController.create();
                                break;
                            case "3":
                                objProductoController.update();
                                break;
                            case "4":
                                objProductoController.delete();
                                break;
                            case "5":
                                objProductoController.getProductsByName();
                                break;
                        }
                    } while (!optionB.equals("6"));
                    break;

                case "3":
                    String optionC;
                    do {
                        optionC = JOptionPane.showInputDialog("""
                                    ----- CLIENTS MENU ----- \n
                                    1. 👩🏻 List clients
                                    2. ➕ Add client
                                    3. ✏ Update client
                                    4. ❌ Delete client
                                    5. 🔎 Search clients by Name
                                    6. 👈 Go Back
                                    \n
                                """);

                        // Switch Flights Admin
                        switch (optionC) {
                            case "1":
                                objClienteController.getAll();
                                break;
                            case "2":
                                objClienteController.create();
                                break;
                            case "3":
                                objClienteController.update();
                                break;
                            case "4":
                                objClienteController.delete();
                                break;
                            case "5":
                                objClienteController.getClientsByName();
                                break;
                        }
                    } while (!optionC.equals("6"));
                    break;
            }

        } while (!option.equals("5"));
    }
}
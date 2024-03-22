import controller.CoderController;
import database.ConfigDB;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ConfigDB.openConnection();

        CoderController objCoderController = new CoderController();
        String option;

        do {
            option = JOptionPane.showInputDialog("""
                        MENÚ
                        1. List Coders
                        2. Insert Coders
                        3. Update Coder
                        4. Delete Coder
                        5. Search coder by name
                        6. Exit
                    """);

            switch (option){
                case "1": // List All Coders
                    objCoderController.getAll();
                    break;
                case "2":
                    objCoderController.create();
                    break;
                case "3":
                    objCoderController.update();
                    break;
                case "4":
                    objCoderController.delete();
                    break;
                case "5":
                    objCoderController.getCoderByName();
                    break;
            }

        } while (!option.equals("6"));
    }
}
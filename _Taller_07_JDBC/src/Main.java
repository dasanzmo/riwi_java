import contoller.AuthorController;
import database.ConfigDB;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ConfigDB.openConnection();

        AuthorController objAuthorController = new AuthorController();
        String option = "";

        do {
            option = JOptionPane.showInputDialog("""
                        MENÚ
                        1. Authors
                        2. Books
                        3. List Books by Author
                        4. Exit
                    """);

            switch (option) {
                case "1": // Authors Menu

                    String optionA = "";
                    do {
                        optionA = JOptionPane.showInputDialog("""
                                    MENÚ
                                    1. List Authors
                                    2. Insert Author
                                    3. Update Author
                                    4. Delete Author
                                    5. Exit
                                """);

                        switch (optionA) {
                            case "2":
                                objAuthorController.create();
                                break;
                        }
                    } while (!optionA.equals("5"));

                    break;
            }

        } while (!option.equals("4"));
    }
}
import contoller.AuthorController;
import contoller.BookController;
import database.ConfigDB;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        ConfigDB.openConnection();

        AuthorController objAuthorController = new AuthorController();
        BookController objBookController = new BookController();

        String option;

        do {
            option = JOptionPane.showInputDialog("""
                        MENÚ
                        1. Authors Admin
                        2. Books Admin
                        3. List Books by Author
                        4. Exit
                    """);

            switch (option) {

                // Authors Menu
                case "1":

                    String optionA;
                    do {
                        optionA = JOptionPane.showInputDialog("""
                                    MENÚ
                                    1. List Authors
                                    2. Insert Author
                                    3. Update Author
                                    4. Delete Author
                                    5. Exit
                                """);

                        // Switch Authors Admin
                        switch (optionA) {
                            case "1":
                                objAuthorController.getAll();
                                break;
                            case "2":
                                objAuthorController.create();
                                break;
                            case "3":
                                objAuthorController.update();
                                break;
                            case "4":
                                objAuthorController.delete();
                                break;
                        }
                    } while (!optionA.equals("5"));
                    break;

                // Books Menu
                case "2":

                    String optionB;
                    do {
                        optionB = JOptionPane.showInputDialog("""
                                    MENÚ
                                    1. List Books
                                    2. Insert Book
                                    3. Update Book
                                    4. Delete Book
                                    5. Exit
                                """);

                        // Switch Books Admin
                        switch (optionB) {
                            case "1":
                                objBookController.getAll();
                                break;
                            case "2":
                                objBookController.create();
                                break;
                            case "3":
                                objBookController.update();
                                break;
                            case "4":
                                objBookController.delete();
                                break;
                        }
                    } while (!optionB.equals("5"));
                    break;
            }

        } while (!option.equals("4"));
    }
}
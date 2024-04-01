import controller.DoctorController;
import controller.SpecialtyController;
import database.ConfigDB;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        ConfigDB.openConnection();

        DoctorController objDoctorController = new DoctorController();
        SpecialtyController objSpecialtyController = new SpecialtyController();

        String option;

        do {
            option = JOptionPane.showInputDialog("""
                        MENÚ
                        1. 🩺 Admin Doctors
                        2. 📃 Admin Specialties
                        3. 👨‍🦳 Admin Patients
                        4. 🕛 Admin Appointments
                        5. 🔎 Search
                        6. 🚪 Exit
                    """);

            switch (option) {

                // Doctors Menu
                case "1":

                    String optionA;
                    do {
                        optionA = JOptionPane.showInputDialog("""
                                    MENÚ
                                    1. 👨🏻‍⚕️ List Doctors
                                    2. ➕ Add Doctor
                                    3. ✏ Update Doctor
                                    4. ❌ Delete Doctor
                                    5. 🚪 Exit
                                """);

                        // Switch Authors Admin
                        switch (optionA) {
                            case "1":
                                objDoctorController.getAll();
                                break;
                            case "2":
                                objDoctorController.create();
                                break;
                            case "3":
                                objDoctorController.update();
                                break;
                            case "4":

                                break;
                        }
                    } while (!optionA.equals("5"));
                    break;

                // Specialties Menu
                case "2":

                    String optionB;
                    do {
                        optionB = JOptionPane.showInputDialog("""
                                    MENÚ
                                    1. List Specialties
                                    2. ➕ Add Specialty
                                    3. ✏ Update Specialty
                                    4. ❌ Delete Specialty
                                    5. 🚪 Exit
                                """);

                        // Switch Books Admin
                        switch (optionB) {
                            case "1":
                                objSpecialtyController.getAll();
                                break;
                            case "2":
                                objSpecialtyController.create();
                                break;
                            case "3":

                                break;
                            case "4":

                                break;
                        }
                    } while (!optionB.equals("5"));
                    break;

                // Books by Author
                case "3":

                    break;

            }

        } while (!option.equals("6"));

    }
}
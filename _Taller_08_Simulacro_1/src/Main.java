import controller.DoctorController;
import controller.PatientController;
import controller.SpecialtyController;
import database.ConfigDB;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        ConfigDB.openConnection();

        DoctorController objDoctorController = new DoctorController();
        SpecialtyController objSpecialtyController = new SpecialtyController();
        PatientController objPatientController = new PatientController();

        String option;

        do {
            option = JOptionPane.showInputDialog("""
                        MENÚ
                        1. 🩺 Admin Doctors
                        2. 📃 Admin Specialties
                        3. 👨🏻 Admin Patients
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
                                    5. 👈🏻 Back
                                """);

                        // Switch Doctors Admin
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
                                objDoctorController.delete();
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
                                    1. 📃 List Specialties
                                    2. ➕ Add Specialty
                                    3. ✏ Update Specialty
                                    4. ❌ Delete Specialty
                                    5. 👈🏻 Back
                                """);

                        // Switch Specialties Admin
                        switch (optionB) {
                            case "1":
                                objSpecialtyController.getAll();
                                break;
                            case "2":
                                objSpecialtyController.create();
                                break;
                            case "3":
                                objSpecialtyController.update();
                                break;
                            case "4":
                                objSpecialtyController.delete();
                                break;
                        }
                    } while (!optionB.equals("5"));
                    break;

                // Books by Author
                case "3":

                    String optionC;
                    do {
                        optionC = JOptionPane.showInputDialog("""
                                    MENÚ
                                    1. 👨🏻 List Patients
                                    2. ➕ Add Patient
                                    3. ✏ Update Patient
                                    4. ❌ Delete Patient
                                    5. 👈🏻 Back
                                """);

                        // Switch Patients Admin
                        switch (optionC) {
                            case "1":
                                objPatientController.getAll();
                                break;
                            case "2":
                                objPatientController.create();
                                break;
                            case "3":
                                objPatientController.update();
                                break;
                            case "4":
                                objPatientController.delete();
                                break;
                        }
                    } while (!optionC.equals("5"));
                    break;

            }

        } while (!option.equals("6"));

    }
}
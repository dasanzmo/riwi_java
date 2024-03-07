import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner objScannerEjercicios = new Scanner(System.in);

        // Ejercicio 4
        // Calculadora de Índice de Masa Corporal (IMC)
//        String estaturaInput = JOptionPane.showInputDialog(null,"Ingresa tu estatura");
//        String pesoInput = JOptionPane.showInputDialog(null,"Ingresa tu peso");
//
//        Double estatura = Double.parseDouble(estaturaInput);
//        Double peso = Double.parseDouble(pesoInput);
//
//        Double IMC = (peso / (estatura * estatura));
//        JOptionPane.showMessageDialog(null,"Tu IMC es: " + IMC);


        // Ejercicio 3
        // Conversor de Unidades
//        String kmInput = JOptionPane.showInputDialog(null,"Ingresa los Kms");
//
//        Double kilometros = Double.parseDouble(kmInput);
//        Double millas = kilometros / 1.60934;
//
//        JOptionPane.showMessageDialog(null,kilometros + " Km" + " = " + millas + " m");

        // Ejercicio 2
        // Verificador de Edad

//        String edad = JOptionPane.showInputDialog(null, "Ingresa tu edad");
//
//        int edadUsuario = Integer.parseInt(edad);
//
//        if(edadUsuario > 18){
//            JOptionPane.showMessageDialog(null,"Eres mayor de edad");
//        }
//        else {
//            JOptionPane.showMessageDialog(null, "Eres menor de edad");
//        }
//
//        objScannerEjercicios.close();

/*
        // Ejercicio 1
        // Calculadora básica
        Scanner objScannerCalc = new Scanner(System.in);

        String option = JOptionPane.showInputDialog(null, "-- MENÚ DE OPERACIONES --" +
                "\n 1. Sumar \n 2. Restar \n 3. Multiplicar \n 4. Dividir");

        //Obtener los dos números a operar
        String num1 = JOptionPane.showInputDialog(null,"Ingrese el primer número");
        String num2 = JOptionPane.showInputDialog(null,"Ingrese el segundo número");

        // Convertir los números que están en String a enteros
        double a = Double.parseDouble(num1);
        double b = Double.parseDouble(num2);

        switch (option){
            case "1":
                JOptionPane.showMessageDialog(null, a + " + " + b + " = " + (a+b));
                break;
            case "2":
                JOptionPane.showMessageDialog(null, a + " - " + b + " = " + (a-b));
                break;
            case "3":
                JOptionPane.showMessageDialog(null, a + " * " + b + " = " + (a*b));
                break;
            case "4":
                if(b == 0){
                    JOptionPane.showMessageDialog(null, "El número b debe ser diferente de 0");
                }
                else {
                    JOptionPane.showMessageDialog(null, a + " / " + b + " = " + (a/b));
                }
                break;

            default:
                JOptionPane.showMessageDialog(null,"Opción no válida");
        }
*/


/*      //System.out.println nos permite imprimir un mensaje por consola
        System.out.println("Hello world!");

        //Instanciar la clase Scanner nos permite crear un objeto para leer datos ingresados desde la consola
        Scanner objScanner = new Scanner(System.in);

        System.out.println("Ingresa tu nombre: ");
        String nombre = objScanner.nextLine();

        System.out.println("Ingresa tu edad: ");
        int edad = objScanner.nextInt();

        System.out.println("Ingresa tu altura: ");
        double altura = objScanner.nextDouble();

        //Shortcut para imprimir en consola ? sout
        System.out.println("Datos: " + "Nombre: " + nombre + ", Edad: " + edad + ", Altura: " + altura);

        // Cerramos el objeto Scanner para con seguir consumiendo memoria
        objScanner.close();

        if (edad > 18){
            System.out.println("Eres mayor de edad");
            if (altura > 1.80) {
                System.out.println("Eres una persona alta");
            } else if (altura < 1.80 && altura > 1.70){
                System.out.println("Eres una persona de estratura promedio");
            } else {
                System.out.println("Eres una persona de estatura baja");
            }
        }
        else {
            System.out.println(", Eres menor de edad");
        }*/
    }

}

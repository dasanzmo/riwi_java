
import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


//        1. Contador Simple:Escribe un programa que utilice un bucle for para contar del 1 al 10 e
//           imprimir cada número en la consola.
//
//        int contNum = 0;
//        for (int i = 1; i <= 10; i++) {
//            contNum++;
//            System.out.println(i);
//        }
//        System.out.println("La cantidad de números de 1 al 10 es: " + contNum);

//        2. Suma de Números: Utiliza un bucle while para sumar los números del 1 al 100 e imprimir
//            el resultado.

//        int suma = 0;
//        int num = 1;
//
//        while (num <= 100) {
//            suma += num;
//            num++;
//        }
//        JOptionPane.showMessageDialog(null, "La suma de los números del 1 al 100 es: " + suma);


//        3. Tabla de Multiplicar: Utiliza un bucle for anidado para imprimir la tabla de multiplicar
//           del 1 al 10.
//
//        for (int i = 1; i <= 10; i++) {
//            System.out.println("Tabla del" + i);
//
//            for (int j = 0; j <= 10; j++) {
//                System.out.println(i + "x" + j + "=" + (i * j));
//            }
//        }

//        4. Suma de Números Pares: Escribe un programa que sume solo los números pares del 1 al
//           100 usando un bucle for y luego imprime el resultado.
//
//        int sumaPares = 0;
//        for(int i = 1; i <= 100; i++){
//            if(i%2 == 0){
//                sumaPares += i;
//            }
//
//        }
//        System.out.println("La suma de los números pares del 1 al 100 es: " + sumaPares);


//        5. Validación de Entrada de Usuario: Escribe un programa que solicite al usuario que
//        ingrese su edad. Si el usuario ingresa un valor que no es un número válido o está fuera de
//        un rango razonable (por ejemplo, 0-120), el programa debe pedirle al usuario que intente de
//        nuevo. Utiliza un bucle para seguir solicitando la entrada hasta que sea válida.

//        while (true) {
//            int edad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa tu edad"));
//            if (edad > 120 || edad < 0) {
//                JOptionPane.showMessageDialog(null, "El número es inválido");
//            } else {
//                JOptionPane.showMessageDialog(null, "Tu edad es: " + edad);
//                break;
//            }
//        }


//        6. Verificar un Número Primo: Escribe un programa que utilice un bucle para verificar si un
//        número dado es primo o no.

//        while (true) {
//            int num = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa un número"));
//
//            if (num == 0) {
//                JOptionPane.showMessageDialog(null, "El programa se cerrará");
//                break;
//            }
//
//            boolean primo = true;
//            if (num <= 1) {
//                primo = false;
//            } else {
//                for (int div = 2; div * div <= num; div++) {
//                    if (num % div == 0) {
//                        primo = false;
//                        break;
//                    }
//                }
//            }
//
//            if (primo){
//                JOptionPane.showMessageDialog(null,"El número " + num +  " es primo");
//            }
//            else {
//                JOptionPane.showMessageDialog(null,"El número " + num +  " no es primo");
//            }
//        };


//        9.  Desarrolla un sistema de menú interactivo que muestre
//            diferentes opciones al usuario (por ejemplo, 1. Ver saldo 2. Depositar dinero 3. Retirar
//            dinero 4. Salir). Utiliza un bucle para permitir al usuario interactuar con el menú hasta que
//            elija salir.

//        String option;
//        double saldo = 0;
//
//        do {
//            option = JOptionPane.showInputDialog(null,
//                    "\n\n MENÚ DE OPCIONES \n\n" +
//                            "1. Ver saldo \n" +
//                            "2. Depositar dinero \n" +
//                            "3. Retirar \n" +
//                            "4. Salir \n" +
//                            "Ingrese una opción \n");
//
//            switch (option) {
//                case "1":
//                    JOptionPane.showMessageDialog(null, "Tu saldo es " + "$" + saldo);
//                    break;
//
//                case "2":
//                    String precioString = JOptionPane.showInputDialog(null, "Ingresa el valor a depositar \n" +
//                            "$"
//                    );
//
//                    try {
//                        double precio = Double.parseDouble(precioString);
//                        saldo += precio;
//
//                        JOptionPane.showMessageDialog(null, "Dinero ingresado correctamente");
//                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(null, "El valor no es válido");
//                    }
//                    break;
//
//                case "3":
//
//                    try {
//                        double valorRetirar = Double.parseDouble(JOptionPane.showInputDialog(null,
//                                "¿Cuánto quieres retirar?"));
//
//                        if (valorRetirar <= saldo) {
//                            saldo -= valorRetirar;
//                            JOptionPane.showMessageDialog(null, "Tu retiro fue exitoso");
//                        } else {
//                            JOptionPane.showMessageDialog(null, "Saldo insuficiente");
//                        }
//
//                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(null, "El valor no es válido");
//                    }
//
//                    break;
//            }
//
//        } while (!option.equals("4"));

    }
}


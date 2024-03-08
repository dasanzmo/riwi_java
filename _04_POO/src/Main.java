import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

          //EJERCICIO 1

//        String titulo = JOptionPane.showInputDialog(null,"Escribe el título del libro");
//        String autor = JOptionPane.showInputDialog(null,"Escribe el autor del libro");
//        String publicacion = JOptionPane.showInputDialog(null,"Escribe el año de publicación");
//        Libro objLibro = new Libro(titulo, autor, publicacion, false);
//
//        System.out.println(objLibro.getTitulo());
//
//        objLibro.setTitulo("El rastro");
//
//        System.out.println(objLibro.getTitulo());

        // EJERCICIO 2
//        Empleado objEmpleado = new Empleado("Daniel","CEO", 7000000 ,1);
//        objEmpleado.aumentarSalario(20);
//
//        System.out.println(objEmpleado.getSalario());

        // OTRA FORMA EJERCICIO 2

        Empleado objEmpleado = new Empleado();
        int id =0;
        Scanner objScanner = new Scanner(System.in);

        System.out.println("Ingresa el nombre del empleado");
        objEmpleado.setNombre(objScanner.next());

        System.out.println("Ingresa el salario base del empleado");
        objEmpleado.setSalario(objScanner.nextDouble());

        System.out.println("Ingresa el cargo");
        objEmpleado.setPosicion(objScanner.next());

        System.out.println("Ingresa el porcentaje de aumento");
        objEmpleado.aumentarSalario(objScanner.nextDouble());
        objEmpleado.setId(id);
        id++;

        System.out.println("El salario final del empleado con su respectivo aumento es de: " + objEmpleado.getSalario());

    }
}
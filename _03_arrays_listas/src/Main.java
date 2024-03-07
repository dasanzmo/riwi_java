import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner objScanner = new Scanner(System.in);

        // Creamos nuesstro array que guardara las notas de los estudiantes
        double[] notas = new double[10];

        // Variable para guardar la suma total
        double sumaTotal = 0;

        for (int i = 0; i < 10; i++) {
            System.out.println("Ingrese la nota del estudiante #" + (i + 1));
            try {
                notas[i] = objScanner.nextDouble();
                sumaTotal += notas[i];
            } catch (Exception e) {
                System.out.println("Nota no válida");
                break;
            }

        }

        // Variable para guardar el promedio general de las notas
        double promedio = sumaTotal / notas.length;

        System.out.println("El promedio de todas las notas es: " + promedio);

    }
}
public class Main {
    public static void main(String[] args) {

/*      Ejercicio 2: Sistema de Registro de Empleados
        Objetivo: Desarrollar un sistema para manejar los empleados de una empresa, aplicando
        encapsulamiento, herencia y polimorfismo, y utilizando ArrayList para almacenar los
        empleados.

        Principios de POO aplicados: Encapsulamiento, Herencia, Polimorfismo.

        Requisitos:

        - Clase Persona: Con propiedades básicas como nombre y edad.
        - Clase Empleado: Hereda de Persona y añade propiedades como idEmpleado y salario. Usa
        encapsulamiento adecuadamente.
        - Clase EmpleadoTemporal y Clase EmpleadoPermanente: Heredan de Empleado y
        representan diferentes tipos de empleados.
        - Clase GestionEmpleados: Utiliza un ArrayList para gestionar objetos del tipo Empleado.
        Implementa métodos para añadir, eliminar y mostrar empleados. Utiliza polimorfismo para
        manejar diferentes tipos de empleados.*/


        GestionEmpleados objGestion = new GestionEmpleados();

        Empleado emp1 = new EmpleadoPermanente("Daniel",32,001,5000000, 5);
        Empleado emp2 = new EmpleadoTemporal("Susana", 25, 002, 2500000, "30/04/2025");

        objGestion.agregarEmpleado(emp1);
        objGestion.agregarEmpleado(emp2);

        objGestion.mostrarEmpleados();
        objGestion.eliminarEmpleado(002);

        System.out.println("Lista después de eliminar");
        objGestion.mostrarEmpleados();
    }
}
package ejercicio_04;

public class Main {
    public static void main (String[] args){

//        Figuras Geométricas: Crea una clase abstracta Figura con un método
//        abstracto calcularArea(). Luego, extiende esta clase con algunas figuras
//        específicas como Circulo, Rectangulo, y Cuadrado, implementando el
//        cálculo del área en cada una. Utiliza el concepto de herencia y polimorfismo.

        // TEST 1: Calcular el área de un círculo
        Circulo objCirculo = new Circulo(10);
        System.out.println("El área del círculo es: " + objCirculo.calcularArea());

        // TEST 2: Calcular el área de un rectángulo
        Rectangulo objRectangulo = new Rectangulo(20,40);
        System.out.println("El área del rectángulo es: " + objRectangulo.calcularArea());

        // TEST 3: Calcular el área de un cuadrado
        Cuadrado objCuadrado = new Cuadrado(40);
        System.out.println("El área del cuadrado es: " + objCuadrado.calcularArea());

    }
}

import java.util.Scanner;

public class Ejercicio_02 {

    public static void main(String[] args) {

        //Creamos la matriz que nos permitirá guardar el estado de todos los asientos
        boolean[][] listaAsientos = new boolean[5][10];

        // Objeto Scanner para poder obtener información del usuario por consola
        Scanner objScanner = new Scanner(System.in);

        //Variable para guardar la opción del usuario
        int opcion = 0;

        do {
            objScanner.nextLine();
            System.out.println("----- ENTER PARA CONTINUAR -----");
            objScanner.nextLine();

            System.out.println("----- RESERVA DE SILLAS TEATRO -----");
            System.out.println("1. Reservar asiento");
            System.out.println("2. Cancelar asiento");
            System.out.println("3. Mostrar asientos disponibles");
            System.out.println("4. Contabilizar el total de asientos disponibles");
            System.out.println("5. Salir");

            //Leemos la opción elegida por el usuario
            opcion = objScanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingresa la fila (1-5)");
                    int fila = objScanner.nextInt() - 1;
                    System.out.println("Ingresa la columna (1-10)");
                    int asiento = objScanner.nextInt() - 1;

                    if (!listaAsientos[fila][asiento]) {
                        listaAsientos[fila][asiento] = true;
                        System.out.println("Asiento reservado correctamente 😎");
                    } else {
                        System.out.println("Este asiento ya se encuentra reservado 😒");
                    }

                    break;


                case 2:
                    System.out.println("Ingresa la fila (1-5)");
                    fila = objScanner.nextInt() - 1;
                    System.out.println("Ingrese el asiento (1-10)");
                    asiento = objScanner.nextInt() - 1;
                    if (listaAsientos[fila][asiento]) {
                        listaAsientos[fila][asiento] = false;
                        System.out.println("Reserva de asiento cancelada correctamente ✔");
                    } else {
                        System.out.println("Este asiento ya está libre");
                    }
                    break;

                case 3:  // Muestra los asientos disponibles

                    System.out.println("Asientos disponibles (fila-asiento)");
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            if (!listaAsientos[i][j]) {
                                System.out.println("(" + (i + 1) + " - " + (j + 1) + ")");
                            }
                        }
                    }
                    break;

                case 4: //Contabilizar el total de asientos ocupados y disponibles.
                    int ocupados =0, disponibles = 0;

                    for (boolean[] filaAsiento: listaAsientos){
                        for (boolean asientoOcupado: filaAsiento){
                            if (asientoOcupado){
                                ocupados++;
                            }else {
                                disponibles++;
                            }
                        }
                    }

                    System.out.println("Total de asientos ocupados: "+ ocupados);
                    System.out.println("Total de asientos disponibles: "+ disponibles);


                    break;
            }


        } while (opcion != 5);
    }
};




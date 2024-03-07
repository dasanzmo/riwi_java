import javax.swing.*;
import java.util.ArrayList;

public class Ejercicio_05 {
    public static void main(String[] args) {

        // Creación de la lista de canciones
        ArrayList<String> playlist = new ArrayList<>();

        int option = 0;
        int cancionActual = 0;

        do {

            try {
                option = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "----- PLAYLIST RIWI MUSIC ----- \n" +
                                "1. Agregar canción \n" +
                                "2. Remover canción \n" +
                                "3. Mostrar canción actual y siguientes en la lista \n" +
                                "4. Saltar a la siguiente canción \n" +
                                "5. Salir \n"
                ));

                switch (option) {
                    case 1: // Añadir una canción

                        // Pedimons la nueva canción al usuario
                        String nuevaCancion = JOptionPane.showInputDialog("Ingrese el nombre de la canción a agregar");

                        // Agregamos la nueva canción a la playlist
                        playlist.add(nuevaCancion.toLowerCase());

                        JOptionPane.showMessageDialog(null, nuevaCancion + " fue agregada correctamente 🎵🎵");

                        break;

                    case 2: // Eliminar una canción de la playlist

                        // Pedimos al usuario el nombre de la canción a eliminar
                        String cancionEliminar = JOptionPane.showInputDialog("Ingrese el nombre de la canción a eliminar");

                        // Eliminar la canción que tenga ese nombre
                        if (playlist.remove(cancionEliminar.toLowerCase())) {
                            JOptionPane.showMessageDialog(null, cancionEliminar + " eliminada correctamente ❌");
                        } else {
                            JOptionPane.showMessageDialog(null, cancionEliminar + " no existe en la playlist ❌");
                        }

                        break;

                    case 3: // Mostrar la canción actual y la siguiente en la lista

                        // Preguntar si la playlist está vacía
                        if (playlist.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "La playlist está vacía");
                        } else {

                            // Creamos una variable para guardar en texto todas las canciones mayores a la actual
                            String listaTotal = "";

                            //Agregamos la canción actual
                            listaTotal += "Canción actual \n " + "🎵 " + playlist.get(cancionActual) + "\n" +
                                    "\n\n Siguientes en la lista: \n";

                            for (int i = cancionActual; i < playlist.size() - 1; i++) {
                                // Por cada iteración concatenamos a la variable listaTotal
                                listaTotal += "🎵 " + playlist.get(i + 1) + "\n";
                            }

                            // Imprimimos las canciones
                            JOptionPane.showMessageDialog(null, listaTotal);
                        }

                        break;

                    case 4: // Saltar a la siguiente canción

                        // Validar que si haya una siguiente canción
                        if (cancionActual + 1 < playlist.size()) {
                            cancionActual++;
                            JOptionPane.showMessageDialog(null,playlist.get(cancionActual) + " reproducida correctamente");
                        }else{
                            JOptionPane.showMessageDialog(null, "Playlist finalizada");
                            cancionActual = 0;
                        }

                        break;
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Carácteres no válidos");
            }

        } while (option != 5);


    }
}

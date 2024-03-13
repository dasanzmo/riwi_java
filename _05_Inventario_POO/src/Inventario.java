import javax.swing.*;
import java.util.ArrayList;

public class Inventario {

    private ArrayList<Productos> ListaProductos;

    public Inventario() {
        this.ListaProductos = new ArrayList<>();
    }

    // Métodos
    public void agregarProducto(Productos producto) {
        this.ListaProductos.add(producto);
    }

    public boolean eliminarProducto(int id) {
        return ListaProductos.removeIf(producto -> producto.getId() == id);
    }

    public String buscarPorNombre(String nombreBuscar) {
        String busqueda = "";
        for (Productos objProducto : this.ListaProductos) {
            if (objProducto.getNombre().equalsIgnoreCase(nombreBuscar)) {
                busqueda = objProducto.toString();
            }
            else {
                busqueda = "No hay resultados para la búsqueda";
            }
        }
        return busqueda;
    }

    public String listarProductos() {

        String mensaje = "";

        if(!this.ListaProductos.isEmpty()){
            for (Productos producto : this.ListaProductos) {
                mensaje += "ID: " + producto.getId() + "\n" + "Nombre: " + producto.getNombre() + "\n" + "Precio: "
                        + producto.getPrecio() + "\n\n";
            }
        }
        else {
            mensaje = "No hay productos para mostrar";
        }

        return mensaje;
    }



}


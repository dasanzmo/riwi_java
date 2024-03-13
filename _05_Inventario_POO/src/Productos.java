public class Productos {

    private int id;
    private String nombre;
    private double precio;

    public Productos(int id, String nombre,  double precio) {
        this.nombre = nombre;
        this.id = id;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "nombre=" + nombre +
                ", id=" + id +
                ", precio=" + precio +
                '}';
    }
}

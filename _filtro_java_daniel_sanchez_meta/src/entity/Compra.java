package entity;

import java.sql.Date;

public class Compra {

    private int id_compra;
    private int id_cliente;
    private int id_producto;
    private Date fecha_compra;
    private int cantidad;

    private Cliente objCliente;
    private Producto objProducto;
    private Tienda objTienda;

    public Compra() {
    }

    public Compra(int id_compra, int id_cliente, int id_producto, Date fecha_compra, int cantidad) {
        this.id_compra = id_compra;
        this.id_cliente = id_cliente;
        this.id_producto = id_producto;
        this.fecha_compra = fecha_compra;
        this.cantidad = cantidad;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Cliente getObjCliente() {
        return objCliente;
    }

    public void setObjCliente(Cliente objCliente) {
        this.objCliente = objCliente;
    }

    public Producto getObjProducto() {
        return objProducto;
    }

    public void setObjProducto(Producto objProducto) {
        this.objProducto = objProducto;
    }

    public Tienda getObjTienda() {
        return objTienda;
    }

    public void setObjTienda(Tienda objTienda) {
        this.objTienda = objTienda;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id_compra=" + id_compra +
                ", fecha_compra=" + fecha_compra +
                ", producto=" + objProducto.getNombre() +
                ", cantidad=" + cantidad +
                ", cliente=" + objCliente.getNombre() + " " + objCliente.getApellido() +
                ", tienda=" + objTienda.getNombre() + ", " + objTienda.getUbicacion() +
                '}';
    }
}

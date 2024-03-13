import javax.swing.*;

public class Main {
    public static void main(String[] args) {

//        System.out.println(objInventario.buscarPorNombre("Cuaderno"));

        Inventario objInventario = new Inventario();

        int option;
        int idP = 0;

        do{
            option = Integer.parseInt(JOptionPane.showInputDialog(null, "1. Agregar Producto \n" +
                    "2. Eliminar producto \n" +
                    "3. Buscar producto \n" +
                    "4. Listar productos \n" +
                    "5. Salir \n","Bienvenidos a Riwi Market",JOptionPane.INFORMATION_MESSAGE));

            switch (option){
                case 1:

                    String nombreP = JOptionPane.showInputDialog(null,"Ingresa el nombre del producto");
                    double precioP = Double.parseDouble(JOptionPane.showInputDialog(null,"Ingresa el precio del producto"));

                    Productos prod = new Productos(idP, nombreP, precioP);
                    idP++;
                    objInventario.agregarProducto(prod);

                    break;

                case 2:
                    int prodEliminar = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID del producto a eliminar"));
                    try{
                        objInventario.eliminarProducto(prodEliminar);
                        JOptionPane.showMessageDialog(null,"El producto " + prodEliminar + " ha sido eliminado");
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(null,"Ops, ha ocurrido un error");
                    }
                    break;

                case 3:
                    String nombreBuscar = JOptionPane.showInputDialog(null,"Buscar por nombre");
                    JOptionPane.showMessageDialog(null,objInventario.buscarPorNombre(nombreBuscar));
                    break;

                case 4:
                    JOptionPane.showMessageDialog(null,objInventario.listarProductos(),"Lista de productos",JOptionPane.INFORMATION_MESSAGE);
                    break;

                case 5:
                    break;
            }
        }while(option != 5);

    }
}
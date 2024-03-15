import java.util.ArrayList;

public class GestionEmpleados {

    private ArrayList<Empleado> empleados;

    public GestionEmpleados() {
        empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado empleado){
        empleados.add(empleado);
    }

    public boolean eliminarEmpleado(int idEmpleado){
        return empleados.removeIf(emp -> emp.getIdEmpleado() == idEmpleado);

    }

    public void mostrarEmpleados(){
        for(Empleado empTmp: empleados){
            System.out.println("ID Empleado" + empTmp.getIdEmpleado() +
                    "Nombre" + empTmp.getNombre() +
                    "Salario" + empTmp.getSalario()
            );
        }
    }
}



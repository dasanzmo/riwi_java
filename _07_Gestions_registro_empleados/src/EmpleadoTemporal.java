public class EmpleadoTemporal extends Empleado{

    private String fechaFinContrato;

    public EmpleadoTemporal(String nombre, int edad, int idEmpleado, double salario, String fechaFinContrato) {
        super(nombre, edad, idEmpleado, salario);
        this.fechaFinContrato = fechaFinContrato;
    }
}

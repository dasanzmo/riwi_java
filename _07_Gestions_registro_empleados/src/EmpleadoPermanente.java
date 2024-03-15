public class EmpleadoPermanente extends Empleado{

    private int añosAntiguedad;

    public EmpleadoPermanente(String nombre, int edad, int idEmpleado, double salario, int añosAntiguedad) {
        super(nombre, edad, idEmpleado, salario);
        this.añosAntiguedad = añosAntiguedad;
    }

    public int getAñosAntiguedad() {
        return añosAntiguedad;
    }

    public void setAñosAntiguedad(int añosAntiguedad) {
        this.añosAntiguedad = añosAntiguedad;
    }
}

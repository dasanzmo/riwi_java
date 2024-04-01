package entity;

public class Doctor {

    private int id_doctor;
    private String name;
    private String last_name;
    private int id_specialty;
    private Specialty specialtyDoctor;

    public Doctor() {
    }

    public Doctor(int id_doctor, String name, String last_name, int id_specialty) {
        this.id_doctor = id_doctor;
        this.name = name;
        this.last_name = last_name;
        this.id_specialty = id_specialty;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Specialty getSpecialtyDoctor() {
        return specialtyDoctor;
    }


    public int getId_specialty() {
        return id_specialty;
    }

    public void setId_specialty(int id_specialty) {
        this.id_specialty = id_specialty;
    }

    public void setSpecialtyDoctor(Specialty specialtyDoctor) {
        this.specialtyDoctor = specialtyDoctor;
    }

    @Override
    public String toString() {
        return  "Id: " + id_doctor +
                ", Name: '" + name + '\'' +
                ", Last name: '" + last_name + '\'' +
                ", Specialty: '" + specialtyDoctor.getName() + '\'';
    }
}

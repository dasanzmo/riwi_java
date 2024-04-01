package entity;

public class Specialty {

    private int id_specialty;
    private String name;
    private String description;

    public Specialty() {
    }

    public Specialty(int id_specialty, String name, String description) {
        this.id_specialty = id_specialty;
        this.name = name;
        this.description = description;
    }

    public int getId_specialty() {
        return id_specialty;
    }

    public void setId_specialty(int id_specialty) {
        this.id_specialty = id_specialty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  "Id: " + id_specialty +
                ", Name: '" + name + '\'' +
                ", Description: '" + description + '\'';
    }
}

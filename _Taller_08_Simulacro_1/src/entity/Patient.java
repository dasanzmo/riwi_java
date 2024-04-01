package entity;

import java.util.Date;

public class Patient {

    private int id_patient;
    private String name;
    private String last_name;
    private Date birthdate;
    private String identity_document;

    public Patient() {
    }

    public Patient(int id_patient, String name, String last_name, Date birthdate, String identity_document) {
        this.id_patient = id_patient;
        this.name = name;
        this.last_name = last_name;
        this.birthdate = birthdate;
        this.identity_document = identity_document;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getIdentity_document() {
        return identity_document;
    }

    public void setIdentity_document(String identity_document) {
        this.identity_document = identity_document;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id_patient=" + id_patient +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", birthdate=" + birthdate +
                ", identity_document='" + identity_document + '\'' +
                '}';
    }
}

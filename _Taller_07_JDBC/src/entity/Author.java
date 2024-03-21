package entity;

public class Author {
    private int id_author;
    private String name;
    private String nacionality;

    public Author() {
    }

    public Author(int id_autor, String name, String nacionality) {
        this.id_author = id_autor;
        this.name = name;
        this.nacionality = nacionality;
    }

    public int getId_autor() {
        return id_author;
    }

    public void setId_autor(int id_autor) {
        this.id_author = id_autor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNacionality() {
        return nacionality;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id_author=" + id_author +
                ", name='" + name + '\'' +
                ", nacionality='" + nacionality + '\'' +
                '}';
    }
}

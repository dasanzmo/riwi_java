package entity;

import java.sql.Date;

public class Book {

    private int id_book;
    private String title;
    private Date date_publish;
    private double price;
    private int id_author;
    private Author objAuthor;

    public Book() {
    }

    public Book(int id_book, String title, Date date_publish, double price, int id_author) {
        this.id_book = id_book;
        this.title = title;
        this.date_publish = date_publish;
        this.price = price;
        this.id_author = id_author;
    }

    public Author getObjAuthor() {
        return objAuthor;
    }

    public void setObjAuthor(Author objAuthor) {
        this.objAuthor = objAuthor;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate_publish() {
        return date_publish;
    }

    public void setDate_publish(Date date_publish) {
        this.date_publish = date_publish;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    @Override
    public String toString() {
        return "\n-----------------------" +
                "\n" + this.getTitle().toUpperCase() + "\n" +
                "id book: " + id_book + "\n" +
                "date publish: " + date_publish + "\n" +
                "price: " + price + "\n" +
                "id author: " + id_author + "\n" +
                "author: " + objAuthor.getName() + "\n" +
                "-----------------------";
    }
}

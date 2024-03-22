package entity;

import java.sql.Date;

public class Book {

    private int id_book;
    private String title;
    private Date date_publish;
    private double price;
    private int id_author;

    private String name_author;

    public Book() {
    }

    public Book(int id_book, String title, Date date_publish, double price, int id_author, String name_author) {
        this.id_book = id_book;
        this.title = title;
        this.date_publish = date_publish;
        this.price = price;
        this.id_author = id_author;
        this.name_author = name_author;
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

    public String getName_author() {
        return name_author;
    }

    public void setName_author(String name_author) {
        this.name_author = name_author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id_book=" + id_book +
                ", title='" + title + '\'' +
                ", date_publish=" + date_publish +
                ", price=" + price +
                ", id_author=" + id_author +
                ", name_author=" + name_author +
                '}';
    }
}

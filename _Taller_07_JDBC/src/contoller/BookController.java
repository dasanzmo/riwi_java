package contoller;

import entity.Author;
import entity.Book;
import model.AuthorModel;
import model.BookModel;

import javax.swing.*;
import java.sql.Date;
import java.util.List;

public class BookController {

    AuthorModel objAuthorModel;
    BookModel objBookModel;
    AuthorController objAuthorController = new AuthorController();
    public BookController(){
        this.objBookModel = new BookModel();
        this.objAuthorModel = new AuthorModel();
    }

    public void getAll(){
        String list = this.getAll(objBookModel.findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public String getAll(List<Object> listObject){

        String list = "Books list \n";

        for(Object obj: listObject){

            Book objBook = (Book) obj;

            list += objBook.toString() + "\n";

        }
        return list;
    }

    public void create(){

        // Creo un nuevo libro
        Book objBook = new Book();

        // Pido los datos a insertar del libro
        String title = JOptionPane.showInputDialog("Insert book title: ");
        Date date_pub = Date.valueOf(JOptionPane.showInputDialog("Insert book publish date: "));
        double price = Double.parseDouble(JOptionPane.showInputDialog("Insert book price: "));

        // LLamo la lista de autores para mostrar al usuario el Id del autor que quiere relacionar con el libro
        String listAuthors = objAuthorController.getAll(this.objAuthorModel.findAll());
        int id_author = Integer.parseInt(JOptionPane.showInputDialog(null,listAuthors + "\n Insert the author ID of this book"));

        objBook.setTitle(title);
        objBook.setDate_publish(date_pub);
        objBook.setPrice(price);
        objBook.setId_author(id_author);

        objBook = (Book) this.objBookModel.insert(objBook);

        //JOptionPane.showMessageDialog(null,"Author: " + objAuthor.toString() + "is created successfully");

    }

    public void update(){

        // Listamos los libros
        String listBooks = this.getAll(this.objBookModel.findAll());

        // Pido el ID del libro a editar
        int idUpdateBook = Integer.parseInt(JOptionPane.showInputDialog(null,listBooks + "\n Insert the Book ID to edit"));

        // Verificamos el ID del libro
        Book objBook = (Book) this.objBookModel.findById(idUpdateBook);

        // Valido si el libro existe
        if (objBook == null){
            JOptionPane.showMessageDialog(null,"Book not found");
        }
        else{

            // Pido los nuevos datos
            String title = JOptionPane.showInputDialog(null,"Insert new book title: ", objBook.getTitle());
            Date date_pub = Date.valueOf(JOptionPane.showInputDialog(null,"Insert new book publish date: ", objBook.getDate_publish()));
            double price = Double.parseDouble(JOptionPane.showInputDialog(null,"Insert new book price: ", objBook.getPrice()));

            // LLamo la lista de autores para mostrar al usuario el Id del autor que quiere actualizar en el libro
            String listAuthors = objAuthorController.getAll(this.objAuthorModel.findAll());
            int id_author = Integer.parseInt(JOptionPane.showInputDialog(null,listAuthors + "\n Insert the new author ID of this book", objBook.getId_author()));

            // Asigno los datos ingresados al libro
            objBook.setTitle(title);
            objBook.setDate_publish(date_pub);
            objBook.setPrice(price);
            objBook.setId_author(id_author);

            // Retorno el libro al modelo update del libro
            this.objBookModel.update(objBook);
        }

    }

    public void delete(){
        String listBooks = this.getAll(objBookModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(null,listBooks + "\n Insert the book ID to delete"));

        Book objBook = (Book) this.objBookModel.findById(idDelete);

        if(objBook == null){
            JOptionPane.showMessageDialog(null,"Book not found");
        }
        else{
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure to delete this book? " + objBook.getTitle());

            if(confirm == 0){
                this.objBookModel.delete(objBook);
            }
        }
    }

}

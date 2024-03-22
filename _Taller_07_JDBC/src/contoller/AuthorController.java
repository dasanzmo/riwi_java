package contoller;

import entity.Author;
import model.AuthorModel;

import javax.swing.*;
import java.util.List;

public class AuthorController {
    AuthorModel objAuthorModel;

    public AuthorController(){
        this.objAuthorModel = new AuthorModel();
    }

    public void getAll(){

        String list = this.getAll(objAuthorModel.findAll());

        JOptionPane.showMessageDialog(null,list);

    }

    public String getAll(List<Object> listObject) {

        String list = "Authors list \n";

        // Iteramos sobre la lista que devuelve el método find All
        for (Object obj : listObject) {

            // Convertimos o casteamos el objeto tipo Object a un coder
            Author objAuthor = (Author) obj;

            // Concatenamos la información
            list += objAuthor.toString() + "\n";

        }
        return list;

    }

    public void create(){
        Author objAuthor = new Author();

        String name = JOptionPane.showInputDialog("Insert author name: ");
        String nacionality = JOptionPane.showInputDialog("Insert author nacionality: ");

        objAuthor.setName(name);
        objAuthor.setNacionality(nacionality);

        objAuthor = (Author) this.objAuthorModel.insert(objAuthor);

        //JOptionPane.showMessageDialog(null,"Author: " + objAuthor.toString() + "is created successfully");

    }

    public void update(){

        String listAuthors = this.getAll(this.objAuthorModel.findAll());

        int idUpdateAuthor = Integer.parseInt(JOptionPane.showInputDialog(null,listAuthors + "\n Insert the Author ID to edit"));

        Author objAuthor = (Author) this.objAuthorModel.findById(idUpdateAuthor);

        if (objAuthor == null){
            JOptionPane.showMessageDialog(null,"Coder not found");
        }
        else{
            String name = JOptionPane.showInputDialog(null,"Insert new name", objAuthor.getName());
            String nacionality = JOptionPane.showInputDialog(null,"Insert new nacionality", objAuthor.getNacionality());

            objAuthor.setName(name);
            objAuthor.setNacionality(nacionality);

            this.objAuthorModel.update(objAuthor);
        }

    }

    public void delete(){

        String listAuthors = this.getAll(objAuthorModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(null,listAuthors + "\n Insert the author ID to delete"));

        Author objAuthor = (Author) this.objAuthorModel.findById(idDelete);

        if(objAuthor == null){
            JOptionPane.showMessageDialog(null,"Author not found");
        }
        else{
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure to delete the author? " + objAuthor.getName());

            if(confirm == 0){
                this.objAuthorModel.delete(objAuthor);
            }
        }

    }

}

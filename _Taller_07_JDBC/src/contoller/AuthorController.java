package contoller;

import entity.Author;
import model.AuthorModel;

import javax.swing.*;

public class AuthorController {
    AuthorModel objAuthorModel;

    public AuthorController(){
        this.objAuthorModel = new AuthorModel();
    }

    public void getAll(){

    }

    public void create(){
        Author objAuthor = new Author();

        String name = JOptionPane.showInputDialog("Insert author name: ");
        String nacionality = JOptionPane.showInputDialog("Insert author nacionality: ");

        objAuthor.setName(name);
        objAuthor.setNacionality(nacionality);

        objAuthor = (Author) this.objAuthorModel.insert(objAuthor);

        JOptionPane.showMessageDialog(null,objAuthor.toString());

    }

}

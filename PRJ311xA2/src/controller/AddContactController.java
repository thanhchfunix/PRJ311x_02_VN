package controller;

import entity.Contact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.List;

public class AddContactController {



    public void  setAddContactController(ContactController contactController) {
        throw new UnsupportedOperationException("Remove this line and implement your code here!");
    }


    private List<Contact> contacts;

    public  void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @FXML
    void initialize()throws  Exception {

    }

    public  void saveContact() throws Exception {
        throw new UnsupportedOperationException("Remove this line and implement your code here!");
    }
    public  void saveContact(ActionEvent evt)throws  Exception {
        throw new UnsupportedOperationException("Remove this line and implement your code here!");
    }
}

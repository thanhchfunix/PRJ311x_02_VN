package controller;

import entity.Contact;
import entity.Group;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;


public class ContactController {

    @FXML
    AnchorPane mainAnchorPane;

    @FXML
    Button btnAdd, btnDelete, btnGroup, btnUpdate, btnSearch;

    @FXML
    void initialize() {
    }

    //output all contact to tblContact
    public  void showContact(List<Contact> c) {
        throw new UnsupportedOperationException("Remove this line and implement your code here!");
    }
    //output all groups to dropdownlist
    public  void showGroup(List<Group> g) {
        throw new UnsupportedOperationException("Remove this line and implement your code here!");
    }
    //do corresponding actions for search, delete, update and add contact
    public void searchContact(ActionEvent evt) throws Exception{
        throw new UnsupportedOperationException("Remove this line and implement your code here!");

    }
    //manage the groups
    public void groupPanel() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../ui/group.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) mainAnchorPane.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    //update a contact
    public  void updateContact() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../ui/updateContact.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) mainAnchorPane.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Update contact");
        stage.show();
    }
    //delete a selected contact
    public  void deleteContact() throws  Exception {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Do you wanna delete selected contact?");
        Optional<ButtonType> option = alert.showAndWait();
    }

    public void addContact(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../ui/addContact.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.setTitle("Add contact");
        stage.show();
    }

}

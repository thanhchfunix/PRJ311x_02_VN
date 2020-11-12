package controller;

import dao.GroupDAO;
import entity.Contact;
import entity.Group;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateContactController {

    @FXML
    private TextField firstName, lastName, phone, email;
    @FXML
    private DatePicker dob;
    @FXML
    private ComboBox<Group> cbGroup;
    @FXML
    private Label lblFirstName, lblLastName, lblPhone, lblEmail, lbldob;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClose;
    private List<Contact> contacts;
    private Contact updatedContact;

    private ContactController contactController;

    public void  setContactController(ContactController contactController) {
        this.contactController = contactController;
    }

    public  void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public  void setUpdatedContact(Contact updatedContact) throws  Exception{
        this.updatedContact = updatedContact;
        //output current information of updated contact
        firstName.setText(updatedContact.getFirstName());
        lastName.setText(updatedContact.getLastName());
        email.setText(updatedContact.getEmail());
        phone.setText(updatedContact.getPhone());
        //fill all group to dropdownlist
        cbGroup.getItems().clear();
        for (Group x: new GroupDAO().loadGroup("data/group.txt")) {
            cbGroup.getItems().add(x);
        }
        cbGroup.getSelectionModel().select(new Group(updatedContact.getGroup()));
        Date date = new SimpleDateFormat("MM-dd-yyyy").parse(updatedContact.getDob());
        dob.setValue(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }


    @FXML
    void initialize()throws  Exception {
        lblFirstName.setText("");
        lblLastName.setText("");
        lblEmail.setText("");
        lblPhone.setText("");
        lbldob.setText("");
    }

    public  void updateContact(ActionEvent evt)throws  Exception {
        if (evt.getSource() == btnAdd) {
            saveContact();
        } else if (evt.getSource() == btnClose) {
            final Node source = (Node) evt.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }

    }

    public  void saveContact() throws Exception {
        String fname = this.firstName.getText().trim();
        if (fname.isEmpty()) {
            lblFirstName.setText("First name can not be empty");
            return;
        }
        lblFirstName.setText("");
        String lname = this.lastName.getText().trim();
        if (lname.isEmpty()) {
            lblLastName.setText("Last name can not be empty");
            return;
        }
        lblLastName.setText("");
        String mobile = this.phone.getText().trim();
        if (mobile.isEmpty() || !mobile.matches("\\d+")) {
            lblPhone.setText("Phone contains digit only");
            return;
        }
        lblPhone.setText("");
        String mail = this.email.getText().trim();
        Pattern emailNamePtrn = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[_A-Za-z0-9]+(\\.[_A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mtch = emailNamePtrn.matcher(mail);
        if (!mtch.matches()) {
            lblEmail.setText("Email is invalid");
            return;
        }
        lblEmail.setText("");
        String birthdate = (this.dob.getValue().toString());
        String group = cbGroup.getSelectionModel().getSelectedItem().getName();
        Contact c = new Contact(fname, lname, mobile, mail, birthdate, group);
        int i = this.contactController.contactDAO.indexOf(this.contacts, this.updatedContact);
        int j = this.contactController.contactDAO.indexOf(this.contacts, c);
        if (j == -1 || i == j) {
            contactController.contactDAO.updateContact(contacts, c, i);
            contactController.showContact(contacts);
            contactController.contactDAO.saveToFile(contacts, "data/contact.txt");//update datasources
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Contact has been updated");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setContentText("Information of contact is existed");
            alert.showAndWait();
        }
    }

}

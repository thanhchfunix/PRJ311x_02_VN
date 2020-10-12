package com;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ClientChatController {
    @FXML
    private TextField txtAttach;

    @FXML
    private BorderPane clientChat;
    public void btnAttachPerformed(ActionEvent e) {
        Stage stage = (Stage) clientChat.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose a file");
        File file = fc.showOpenDialog(stage);
        if (file != null) {
            txtAttach.setText((String) file.getAbsolutePath());
        }
    }
}

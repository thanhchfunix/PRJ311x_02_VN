package com;

import business.ClientThread;
import com.entity.Client;
import com.entity.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by thanhch on 10/31/2020
 */
public class ClientChatController {
    @FXML
    private TextField txtAttach;//path file
    @FXML
    private TextArea txtContent;
    @FXML
    private TextField txtUsername, txtHostIP, txtPort, txtMessage;
    @FXML
    private Button btnConnect;

    ClientThread clientThread = null;

    //action button connect
    public void btnConnectActionPerformed(ActionEvent evt) {
        if (clientThread == null) {
            try {
                Client c = new Client(txtUsername.getText(), "");
                Server server = new Server(txtHostIP.getText(), Integer.valueOf(txtPort.getText()));
                clientThread = new ClientThread(server, txtContent);
                Thread t = new Thread(clientThread);
                t.start();

                //send username to server
                clientThread.send(":" + c.getUsername());

                txtContent.appendText("Connected to server");
                btnConnect.setDisable(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //action button send
    public void btnSendActionPerformed(ActionEvent evt) {
        try {
            clientThread.send(txtMessage.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

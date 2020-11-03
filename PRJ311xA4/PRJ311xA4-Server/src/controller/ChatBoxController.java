package controller;

import business.ClientHandler;
import business.ServerThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


/**
 * Created by thanhch on 10/31/2020
 */
public class ChatBoxController {
    @FXML
    private TextArea txtContent;
    @FXML
    private TextField txtMessage;
    @FXML
    private Button btnSend;

    private String username;
    private ClientHandler cs;

    public void setUsername(String username) {
        this.username = username;
        cs = ServerThread.clients.get(username);
        cs.setTxtContent(txtContent);
        new Thread(cs).start();
    }

    public void btnSendActionPerformed(ActionEvent evt) {
        try {
            cs.send(txtMessage.getText());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

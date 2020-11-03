package controller;

import business.ClientHandler;
import business.ServerThread;
import com.entity.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by thanhch on 10/31/2020
 */
public class ServerBoxController {

    public static ObservableList<Client> lstClients = FXCollections.observableArrayList();

    @FXML
    private ListView<Client> clients;

    public void initialize() {
        this.clients.setItems(lstClients);
    }

    @FXML
    public void lstClientsMouseClicked(MouseEvent evt) {
        try {
            if (evt.getClickCount() == 2) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/ChatBox.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                String clientName = ((Client) clients.getSelectionModel().getSelectedItem()).getUsername();
                stage.setTitle("Chat with " + clientName);
                stage.show();

                ChatBoxController controller = loader.getController();

                controller.setUsername(clientName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

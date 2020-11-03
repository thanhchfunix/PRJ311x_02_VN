package business;

import com.entity.Client;
import javafx.scene.control.TextArea;
import lombok.Data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

/**
 * Created by thanhch on 10/31/2020
 */
@Data
public class ClientHandler implements Runnable, Serializable {
    private Socket socket;
    private Client client;
    private DataInputStream dis;
    private DataOutputStream dos;
    private TextArea txtContent;

    public void setTxtContent(TextArea txtContent) {
        this.txtContent = txtContent;
    }

    public ClientHandler(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            while (true) {
                //read message from the server
                Object line = dis.readUTF();
                if (line != null) {
                    txtContent.appendText("\n" + client.getUsername() + ":" + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Object line) throws Exception {
        //send a message to the server
        dos.writeUTF(line.toString());
        txtContent.appendText("\nMe:" + line);
    }
}

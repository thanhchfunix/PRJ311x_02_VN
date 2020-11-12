package business;

import com.entity.Client;
import javafx.scene.control.TextArea;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

/**
 * Created by thanhch on 10/31/2020
 */

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

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

    public TextArea getTxtContent() {
        return txtContent;
    }
}

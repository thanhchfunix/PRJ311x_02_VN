/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import com.entity.Server;
import javafx.scene.control.TextArea;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;


public class ClientThread implements Runnable, Serializable {

    //for I/O
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;
    private Server server;
    private TextArea txtContent;

    public ClientThread(Server server, TextArea txtContent) {
        try {
            this.txtContent = txtContent;
            this.server = server;
            socket = new Socket(server.getHost(), server.getPort());
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object line = dis.readUTF();
                if (line != null) {
                    txtContent.appendText("\n" + server.getHost() + ":" + line);
                }
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public void send(Object line) throws Exception {
        dos.writeUTF(line.toString());
        if (!line.toString().startsWith(":"))
            txtContent.appendText("\nMe:" + line);
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

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public TextArea getTxtContent() {
        return txtContent;
    }

    public void setTxtContent(TextArea txtContent) {
        this.txtContent = txtContent;
    }
}

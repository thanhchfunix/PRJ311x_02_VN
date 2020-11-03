/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import com.entity.Server;
import javafx.scene.control.TextArea;
import lombok.Data;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author TrongDuyDao
 */
@Data
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

}

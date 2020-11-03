package business;

import com.entity.Client;
import com.entity.Server;
import controller.ServerBoxController;

import java.io.DataInputStream;
import java.io.IOException;;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by thanhch on 10/31/2020
 */
public class ServerThread implements Runnable, Serializable {
    private Server chatServer;
    private ServerSocket server;
    private Socket socket;
    public static HashMap<String, ClientHandler> clients = new HashMap<>();

    public ServerThread(Server chatServer) {
        this.chatServer = chatServer;
        try {
            server = new ServerSocket(chatServer.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                //output clients to listview
                socket = server.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                String username = dis.readUTF();
                Client c = new Client();
                //clear old data
                if (username != null) {
                    String x = username.substring(username.indexOf(":") + 1);
                    c.setUsername(x);
                    c.setSocket(socket);
                    //output list of users to listview
                    ServerBoxController.lstClients.add(c);
                    //create a thread to handler the connection
                    ClientHandler ch = new ClientHandler(socket, c);
                    clients.put(x, ch);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

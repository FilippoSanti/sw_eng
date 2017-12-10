package controller;

import model.Robot;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static Socket socket;

    // Read an object from the client
    public static void getOOStream() throws IOException {

        ArrayList<Robot> robotList = new ArrayList<Robot>();
        try {

            // Create the server socket and accept new connections
            ServerSocket serverSocket = new ServerSocket(25000);
            System.out.println("Server Started and listening to the port 25000 - Objects are welcome :)");
            ObjectInputStream ois = null;

            while (true) {

                //Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                ois = new ObjectInputStream(is);

                // Read the object - 100% illegal
                robotList = (ArrayList<Robot>) ois.readObject();

                for (int i = 0; i < robotList.size(); i++) {
                    System.out.println(robotList.get(i).getSignal2());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) throws IOException {
        getOOStream();
    }
}
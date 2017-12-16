package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import model.Robot;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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

                // Write the results in the DB
                DBManager.addObjectToDB(DBManager.dbConnect(), robotList);


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
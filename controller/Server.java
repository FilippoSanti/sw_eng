package controller;
import com.mongodb.client.MongoDatabase;
import model.Robot;

import java.io.*;
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
            System.out.println("Server Started and listening to the port 25000");
            ObjectInputStream ois = null;

            // Create a single connection for every request
            MongoDatabase db = DBManager.dbConnect();

            while (true) {

                //Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                ois = new ObjectInputStream(is);

                // Read the object - 100% illegal
                robotList = (ArrayList<Robot>) ois.readObject();

                // Measure execution time
                long startTime = System.currentTimeMillis();

                // Write the results in the DB
                DBManager.addObjectToDB(db, robotList);

                long stopTime = System.currentTimeMillis();
                long elapsedTime = stopTime - startTime;
                System.out.println("Saved " + robotList.size() + " robots in " +elapsedTime + "ms");

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
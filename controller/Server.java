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
            ServerSocket serverSocket = new ServerSocket(25001);
            System.out.println("Server Started and listening to the port 25000");
            ObjectInputStream ois = null;

            while (true) {

                //Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                ois = new ObjectInputStream(is);

                // Read the object - 100% illegal
                robotList = (ArrayList<Robot>) ois.readObject();

                // Measure execution time
                long startTime = System.currentTimeMillis();


                File f = new File("params.tmp");

                // Server-side check, if we find that the params.tmp is missing
                // It means we have to save some robots to the db before generating signals for them...
                if (!f.exists()) {

                    // Save robots in the db
                    DBManager.saveDataToDB(DBManager.dbConnect(), robotList);
                } else {
                    //update existing robots signals
                    DBManager.runUpdateTests(DBManager.dbConnect(), robotList);
                }

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
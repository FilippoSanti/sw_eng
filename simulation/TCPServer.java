package thread_test;

import model.Robot;

import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {

    private static Socket socket;

    // Template to respond to a client
    public static void respondToClient() throws IOException {

        //Multiplying the number by 2 and forming the return message
        String returnMessage;
        try {
            int numberInIntFormat = Integer.parseInt(String.valueOf(9888));
            int returnValue = numberInIntFormat * 2;
            returnMessage = String.valueOf(returnValue) + "\n";
        } catch (NumberFormatException e) {

            //Input was not a number. Sending proper message back to client.
            returnMessage = "Please send a proper number\n";
        }

        //Sending the response back to the client.
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(returnMessage);
        System.out.println("Message sent to the client is " + returnMessage);
        bw.flush();

    }

    public static void printData (ArrayList<Integer> arl) {

        for (int j=1; j < 630000; j++)
        {
            System.out.println(arl.get(j-1));
            if (j%9==0) System.out.println("");
        }
    }

    // Read an object from the client
    public static void getOOStream () throws IOException {

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

                // Read the object
                robotList = (ArrayList<Robot>) ois.readObject();

                for (int i = 0; i < robotList.size(); i++) {
                    System.out.println(robotList.get(i).getCluster());
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
package thread_test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer
{

    private static Socket socket;

    // Template to respond to a client
    public static void respondToClient() throws IOException {

        //Multiplying the number by 2 and forming the return message
        String returnMessage;
        try
        {
            int numberInIntFormat = Integer.parseInt(String.valueOf(9888));
            int returnValue = numberInIntFormat*2;
            returnMessage = String.valueOf(returnValue) + "\n";
        }
        catch(NumberFormatException e)
        {
            //Input was not a number. Sending proper message back to client.
            returnMessage = "Please send a proper number\n";
        }

        //Sending the response back to the client.
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(returnMessage);
        System.out.println("Message sent to the client is "+returnMessage);
        bw.flush();

    }

    public static void main(String[] args)
    {
        try
        {
            int port = 25000;
            ArrayList<Integer> arl = new ArrayList<Integer>();

            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Started and listening to the port 25000");

            //Server is running always. This is done using this while(true) loop
            while(true)
            {
                //Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                for (int i = 0; i < 630000; i++) {
                    arl.add(br.read());
                    System.out.println(arl.get(i));
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
    }
}
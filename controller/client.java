package thread_test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class client
{

    private static Socket socket;

    public static void main(String args[])
    {

        int signal = 0;

        try
        {
            String host = "localhost";
            int port = 25000;
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);

            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            // Send 7 signals for each robot (approximately 630000 signals)

            long startTime = System.currentTimeMillis();

            for (int i = 0; i < 90000; i++) {

                for (int j = 0; j < 7; j++) {
                    Random rn = new Random();
                    boolean b = rn.nextBoolean();
                    if (b) signal = 1; else signal = 0;

                    bw.write(signal);
                    bw.flush();
                    System.out.println("Message sent to the server : "+signal);
                }
            }

            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println("Execution time: " +elapsedTime + "ms");

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
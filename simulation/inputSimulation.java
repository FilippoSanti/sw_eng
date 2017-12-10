package simulation;

import model.Robot;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class inputSimulation {

    private static Socket socket;

    public static void main(String args[]) throws IOException {

        // Send the robots to conquer the world
        spawnRobots();
    }

    public static void spawnRobots() throws IOException {

        ArrayList<Robot> robotList = new ArrayList<Robot>();
        String host = "localhost";
        int port = 25000;
        InetAddress address = InetAddress.getByName(host);
        socket = new Socket(address, port);

        // Open the stream
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        // Array of signals for each robot
        int signals[] = new int[7];

        // Variables to identify a robot and its cluster
        int robot, cluster, signal, value, nRobots;
        nRobots = robot = cluster = signal = value = 0;

        // Measure execution time
        long startTime = System.currentTimeMillis();

        // Create the clusters, every cluster has 900 robots
        for (cluster = 1; cluster <= 100; cluster++) {

            // Spawn 900 robots for each cluster
            for (robot = 1; robot <= 900; robot++) {

                nRobots++;

                // Every robot can send up to 7 signals
                for (signal = 0; signal < 7; signal++) {

                    // Generate signal S1
                    int randomNum = ThreadLocalRandom.current().nextInt(0, 1000);
                    if (randomNum > 998) signals[0] = 0;
                    else signals[0] = 1;

                    // Generate signal S2
                    int randomNum1 = ThreadLocalRandom.current().nextInt(0, 200);
                    if (randomNum1 > 198) signals[1] = 0;
                    else signals[1] = 1;

                    // Generate signal S3
                    int randomNum2 = ThreadLocalRandom.current().nextInt(0, 100);
                    if (randomNum2 > 98) signals[1] = 0;
                    else signals[2] = 1;

                    // Generate signal S4
                    int randomNum3 = ThreadLocalRandom.current().nextInt(0, 200);
                    if (randomNum3 > 198) signals[1] = 0;
                    else signals[3] = 1;

                    // Generate signal S5
                    int randomNum4 = ThreadLocalRandom.current().nextInt(0, 100);
                    if (randomNum4 > 98) signals[1] = 0;
                    else signals[4] = 1;

                    // Generate signal S6
                    int randomNum5 = ThreadLocalRandom.current().nextInt(0, 100);
                    if (randomNum5 > 98) signals[1] = 0;
                    else signals[5] = 1;

                    // Generate signal S7
                    int randomNum6 = ThreadLocalRandom.current().nextInt(0, 100);
                    if (randomNum6 > 98) signals[1] = 0;
                    else signals[6] = 1;

                    // best assignment ever made
                    signals[signal] = value;
                }

                Robot robotObj = new Robot(nRobots, cluster, signals[0], signals[1], signals[2], signals[3],
                        signals[4], signals[5], signals[6]);

                robotList.add(robotObj);

            }
        }

        // Write the entire robot list :)
        oos.writeObject(robotList);
        oos.close();

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }
}
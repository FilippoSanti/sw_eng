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
        spawnRobots(100);
    }

    public static void spawnRobots(int nCluster) throws IOException {

        // Array of robots
        ArrayList<Robot> robotList = new ArrayList<Robot>();

        // Hostname and port configuration
        String host = "localhost";
        int port = 25000;

        // Array of signals for each robot
        int signals[] = new int[7];
        int randomNum, randomRobots;

        // Variables to identify a robot and its cluster
        int robot, cluster, signal, value, nRobots;
        nRobots = robot = cluster = signal = value = 0;

        InetAddress address = InetAddress.getByName(host);
        socket = new Socket(address, port);

        // Open the stream
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        // Measure execution time
        long startTime = System.currentTimeMillis();

        // Create the clusters, every cluster has 900 robots
        for (cluster = 1; cluster <= nCluster; cluster++) {

            // Generate the robots for each cluster in a specified range (500-1000)
            randomRobots = ThreadLocalRandom.current().nextInt(500, 1000);

            // Spawn 900 robots for each cluster
            for (robot = 1; robot <= randomRobots; robot++) {

                // Increment the counter to keep track of the robots
                nRobots++;

                // Every robot can send up to 7 signals
                for (signal = 0; signal < 7; signal++) {

                    // Generate signal S1
                    randomNum = ThreadLocalRandom.current().nextInt(0, 1000);
                    if (randomNum > 998) signals[0] = 0;
                    else signals[0] = 1;

                    // Generate signal S2
                    randomNum = ThreadLocalRandom.current().nextInt(0, 200);
                    if (randomNum > 198) signals[1] = 0;
                    else signals[1] = 1;

                    // Generate signal S3
                    randomNum = ThreadLocalRandom.current().nextInt(0, 100);
                    if (randomNum > 98) signals[1] = 0;
                    else signals[2] = 1;

                    // Generate signal S4
                    randomNum = ThreadLocalRandom.current().nextInt(0, 200);
                    if (randomNum > 198) signals[1] = 0;
                    else signals[3] = 1;

                    // Generate signal S5
                    randomNum = ThreadLocalRandom.current().nextInt(0, 100);
                    if (randomNum > 98) signals[1] = 0;
                    else signals[4] = 1;

                    // Generate signal S6
                    randomNum = ThreadLocalRandom.current().nextInt(0, 100);
                    if (randomNum > 98) signals[1] = 0;
                    else signals[5] = 1;

                    // Generate signal S7
                    randomNum = ThreadLocalRandom.current().nextInt(0, 100);
                    if (randomNum > 98) signals[1] = 0;
                    else signals[6] = 1;
                }

                // Create the robot object
                Robot robotObj = new Robot(nRobots, cluster, signals[0], signals[1], signals[2], signals[3],
                        signals[4], signals[5], signals[6]);

                // Add the robot to a list
                robotList.add(robotObj);
            }
        }

        // Write the entire robot list trought sockets
        oos.writeObject(robotList);
        oos.close();

        // Record the execution time and display it on screen
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime + "ms");
    }
}
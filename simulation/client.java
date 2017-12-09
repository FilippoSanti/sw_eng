package thread_test;

import model.Robot;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class client {

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
        int robot, cluster, signal, value;
        robot = cluster = signal = value = 0;

        // Measure execution time
        long startTime = System.currentTimeMillis();

        // Create the clusters, every cluster has 900 robots
        for (cluster = 0; cluster < 100; cluster++) {

            // Spawn 900 robots for each cluster
            for (robot = 0; robot < 900; robot++) {

                // Every robot can send up to 7 signals
                for (signal = 0; signal < 7; signal++) {

                    // Generate random signals
                    Random rn = new Random();
                    boolean b = rn.nextBoolean();

                    if (b) value = 1;
                    else value = 0;

                    // best assignment ever made
                    signals[signal] = value;
                }

                Robot robotObj = new Robot(robot, cluster, signals[0], signals[1], signals[2], signals[3],
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
package simulation;

import model.Robot;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import static java.sql.Types.NULL;

public class inputSimulation extends TimerTask {

    private static Socket socket;

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

        // Timestamp for each signal
        Date signal1Time, signal2Time, signal3Time, signal4Time, signal5Time, signal6Time, signal7Time;

        signal1Time = signal2Time = signal3Time = signal4Time = signal5Time = signal6Time = signal7Time = null;

        InetAddress address = InetAddress.getByName(host);
        socket = new Socket(address, port);

        // Open the stream
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        // Measure execution time
        long startTime = System.currentTimeMillis();

        // Create the clusters, every cluster has 900 robots
        for (cluster = 1; cluster <= nCluster; cluster++) {

            // Generate the robots for each cluster in a specified range (500-1000)
            randomRobots = ThreadLocalRandom.current().nextInt(600, 1200);

            // Spawn 900 robots for each cluster
            for (robot = 1; robot <= randomRobots; robot++) {

                // Increment the counter to keep track of the robots
                nRobots++;

                // Every robot can send up to 7 signals
                for (signal = 0; signal < 7; signal++) {

                    // Generate signal S1 and get its timestamp
                    randomNum = ThreadLocalRandom.current().nextInt(0, 1000);
                    if (randomNum > 998) signals[0] = 0;
                    else signals[0] = 1;

                    signal1Time = new Date(System.currentTimeMillis());

                    // Generate signal S2 and get its timestamp
                    randomNum = ThreadLocalRandom.current().nextInt(0, 200);
                    if (randomNum > 198) signals[1] = 0;
                    else signals[1] = 1;

                    signal2Time = new Date(System.currentTimeMillis());

                    // Generate signal S3 and get its timestamp
                    randomNum = ThreadLocalRandom.current().nextInt(0, 100);
                    if (randomNum > 98) signals[2] = 0;
                    else signals[2] = 1;

                    signal3Time = new Date(System.currentTimeMillis());

                    // Generate signal S4 and get its timestamp
                    randomNum = ThreadLocalRandom.current().nextInt(0, 200);
                    if (randomNum > 198) signals[3] = 0;
                    else signals[3] = 1;

                    signal4Time = new Date(System.currentTimeMillis());

                    // Generate signal S5 and get its timestamp
                    randomNum = ThreadLocalRandom.current().nextInt(0, 100);
                    if (randomNum > 98) signals[4] = 0;
                    else signals[4] = 1;

                    signal5Time = new Date(System.currentTimeMillis());

                    // Generate signal S6 and get its timestamp
                    randomNum = ThreadLocalRandom.current().nextInt(0, 100);
                    if (randomNum > 98) signals[5] = 0;
                    else signals[5] = 1;

                    signal6Time = new Date(System.currentTimeMillis());

                    // Generate signal S7 and get its timestamp
                    randomNum = ThreadLocalRandom.current().nextInt(0, 100);
                    if (randomNum > 98) signals[6] = 0;
                    else signals[6] = 1;

                    signal7Time = new Date(System.currentTimeMillis());

                }

                // Create the robot object
                Robot robotObj = new Robot(nRobots, cluster, signals[0], signals[1], signals[2], signals[3],
                        signals[4], signals[5], signals[6], signal1Time, signal2Time, signal3Time, signal4Time, signal5Time, signal6Time, signal7Time);

                // Add the robot to a list
                robotList.add(robotObj);
            }
        }

        // Write the entire robot list thought sockets
        oos.writeObject(robotList);
        oos.close();

        // Record the execution time and display it on screen
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Generated " + robotList.size() + " robots in " +elapsedTime + "ms");
    }

    @Override
    public void run() {
        try {
            spawnRobots(100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
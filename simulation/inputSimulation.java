package simulation;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import controller.DBManager;
import controller.dataAnalyzer;
import model.Robot;
import model.Signals;
import org.bson.Document;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class inputSimulation {

    private static Socket socket;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // If the config file doesn't exist, we create it
        // Otherwise we proceed to the signal generation
        // The createRobots() function is used only for the first run
        File f = new File("params.tmp");

        if (!f.exists()) {

            System.out.println("O_O There are no robots in the system O_O - STARTING SIMULATION NOW");
            // Create the robots and return the dimension of each cluster to generate signals
            List<Integer> robotsCount = createRobots(DBManager.dbConnect(), 100);
            saveDataToList(robotsCount);
            System.out.println("All done, ready to generate signals...");

        } else System.out.println("Robots already created...starting the generation process");


        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {

                List<Integer> paramList = null;
                try {
                    paramList = getDataFromList();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {

                    // Start generating signals
                    generateSignals(paramList);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5000);
    }

    // Generate random signals for the robots given the cluster parameters
    public static void generateSignals(List<Integer> paramList) throws IOException, ClassNotFoundException {

        int cluster, nRobots;
        cluster = nRobots = 0;
        ArrayList<Robot> robotList = new ArrayList<Robot>();

        // Iterate for every cluster of robots
        for (int i = 0; i < paramList.size(); i++) {

            // Keep track of the clusters
            cluster++;

            // Go trough each robot
            for (int j = 0; j < paramList.get(i); j++) {

                // Read values from sensors
                Signals sig = readSensors();
                int signals[] = sig.getSignalValues();
                Date dates[] = sig.getDateValues();

                nRobots++;

                int s1[] = null, s2[] = null, s3[] = null,
                        s4[] = null, s5[] = null, s6[] = null, s7[] = null;

                Date d1[] = null, d2[] = null, d3[] = null,
                        d4[] = null, d5[] = null, d6[] = null, d7[] = null;

                s1[0] = signals[0];
                s2[0] = signals[1];
                s3[0] = signals[2];
                s4[0] = signals[3];
                s5[0] = signals[4];
                s6[0] = signals[5];
                s7[0] = signals[6];

                d1[0] = dates[0];
                d2[0] = dates[1];
                d3[0] = dates[2];
                d4[0] = dates[3];
                d5[0] = dates[4];
                d6[0] = dates[5];
                d7[0] = dates[6];


                // Create the robot object
                Robot robotObj = new Robot(nRobots, cluster, s1, s2, s3, s4,
                        s5, s6, s7, d1,d2,d3,d4,d5,d6, d7);

                // Add the robot to a list
                robotList.add(robotObj);

            }
        }

        System.out.println("Generated " +robotList.size()*7 + " signals for " + robotList.size() + "robots");

        // Analyze the generated list to find out
        // Which signals have changed
        // dataAnalyzer.detectSignalChanges(robotList);
        sendDataToServer(robotList);
    }

    // Generate the first set of Robots
    public static List<Integer> createRobots(MongoDatabase db, int nCluster) throws IOException {

        // Drop the previous collection to make
        // execution easier
        MongoCollection<Document> collection = db.getCollection("robot");
        collection.drop();

        // Array of robots
        ArrayList<Robot> robotList = new ArrayList<Robot>();

        // Array with the robots for each cluster
        List<Integer> robotsCount = new ArrayList<Integer>();

        // Variables to identify a robot and its cluster
        int robot, cluster, signal, value, nRobots, randomRobots;

        nRobots = robot = cluster = signal = value = 0;

        // Measure execution time
        long startTime = System.currentTimeMillis();

        // Create the clusters, every cluster has 900 robots
        for (cluster = 1; cluster <= nCluster; cluster++) {

            // Generate the robots for each cluster in a specified range (500-1000)
            randomRobots = ThreadLocalRandom.current().nextInt(600, 1200);

            // Add the count of the robots for each cluster to return it
            robotsCount.add(randomRobots);

            // Spawn 900 robots for each cluster
            for (robot = 1; robot <= randomRobots; robot++) {

                // Increment the counter to keep track of the robots
                nRobots++;

                // Read values from sensors
                Signals sig = readSensors();
                int signals[] = sig.getSignalValues();
                Date dates[] = sig.getDateValues();

                // Declare the dates and signals arrays
                int s1[] = null, s2[] = null, s3[] = null,
                        s4[] = null, s5[] = null, s6[] = null, s7[] = null;

                Date d1[] = null, d2[] = null, d3[] = null,
                        d4[] = null, d5[] = null, d6[] = null, d7[] = null;

                // Store the dates and signals values into the arrays
                s1[0] = signals[0];
                s2[0] = signals[1];
                s3[0] = signals[2];
                s4[0] = signals[3];
                s5[0] = signals[4];
                s6[0] = signals[5];
                s7[0] = signals[6];

                d1[0] = dates[0];
                d2[0] = dates[1];
                d3[0] = dates[2];
                d4[0] = dates[3];
                d5[0] = dates[4];
                d6[0] = dates[5];
                d7[0] = dates[6];

                // Create the robot object
                Robot robotObj = new Robot(nRobots, cluster, s1, s2, s3, s4, s5, s6, s7,
                        d1, d2, d3, d4, d5, d6, d7);

                // Add the robot to a list
                robotList.add(robotObj);
            }
        }

        // Record the execution time and display it on screen
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("");
        System.out.println("Generated " + robotList.size() + " robots in " + elapsedTime + "ms");

        sendDataToServer(robotList);

        return robotsCount;
    }

    // Save the parameters to a tmp file
    public static void saveDataToList(List<Integer> robotParams) throws IOException {

        FileOutputStream fos = new FileOutputStream("params.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(robotParams);
        oos.close();
    }

    // Get the parameters from the tmp file
    public static List<Integer> getDataFromList() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream("params.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Integer> robotParams = (List<Integer>) ois.readObject();
        ois.close();

        return robotParams;
    }

    // Read values from the 'sensors'
    public static Signals readSensors() {

        Signals sigArray = null;
        Date signalTime = null;
        Date dates[] = new Date[7];
        int signalsArray[] = new int[7];

        int randomNum;

        // Every robot can send up to 7 signals
        for (int s = 0; s < 7; s++) {

            // Generate signal S1 and get its timestamp
            randomNum = ThreadLocalRandom.current().nextInt(0, 1000);
            if (randomNum > 998) signalsArray[0] = 0;
            else signalsArray[0] = 1;

            signalTime = new Date(System.currentTimeMillis());
            dates[0] = signalTime;

            // Generate signal S2 and get its timestamp
            randomNum = ThreadLocalRandom.current().nextInt(0, 200);
            if (randomNum > 198) signalsArray[1] = 0;
            else signalsArray[1] = 1;

            signalTime = new Date(System.currentTimeMillis());
            dates[1] = signalTime;

            // Generate signal S3 and get its timestamp
            randomNum = ThreadLocalRandom.current().nextInt(0, 100);
            if (randomNum > 98) signalsArray[2] = 0;
            else signalsArray[2] = 1;

            signalTime = new Date(System.currentTimeMillis());
            dates[2] = signalTime;

            // Generate signal S4 and get its timestamp
            randomNum = ThreadLocalRandom.current().nextInt(0, 200);
            if (randomNum > 198) signalsArray[3] = 0;
            else signalsArray[3] = 1;

            signalTime = new Date(System.currentTimeMillis());
            dates[3] = signalTime;

            // Generate signal S5 and get its timestamp
            randomNum = ThreadLocalRandom.current().nextInt(0, 100);
            if (randomNum > 98) signalsArray[4] = 0;
            else signalsArray[4] = 1;

            signalTime = new Date(System.currentTimeMillis());
            dates[4] = signalTime;

            // Generate signal S6 and get its timestamp
            randomNum = ThreadLocalRandom.current().nextInt(0, 100);
            if (randomNum > 98) signalsArray[5] = 0;
            else signalsArray[5] = 1;

            signalTime = new Date(System.currentTimeMillis());
            dates[5] = signalTime;

            // Generate signal S7 and get its timestamp
            randomNum = ThreadLocalRandom.current().nextInt(0, 100);
            if (randomNum > 98) signalsArray[6] = 0;
            else signalsArray[6] = 1;

            signalTime = new Date(System.currentTimeMillis());
            dates[6] = signalTime;
        }

        return new Signals(signalsArray, dates);
    }

    // Send the list to the server via sockets
    // TODO: use threads to send and recieve data faster
    public static void sendDataToServer(ArrayList<Robot> robotList) throws IOException {

        // Hostname and port configuration
        String host = "localhost";
        int port = 25001;

        InetAddress address = InetAddress.getByName(host);
        socket = new Socket(address, port);

        // Open the stream
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        // Measure execution time
        long startTime = System.currentTimeMillis();
        System.out.println("");
        System.out.println("Sending robots trough TCP...");

        // Measure execution time
        startTime = System.currentTimeMillis();

        // Write the entire robot list thought sockets
        oos.writeObject(robotList);
        oos.close();

        // Record the execution time and display it on screen
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("TCP transfer time: " + elapsedTime + "ms");

    }
}
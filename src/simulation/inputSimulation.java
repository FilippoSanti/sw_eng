package simulation;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import controller.DBManager;
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
                List<int[]> intList = sig.getSignalValues();
                List<Date[]> dateList = sig.getDateValues();

                // Create the robot object
                Robot robotObj = new Robot(nRobots, cluster, intList.get(0), intList.get(1), intList.get(2), intList.get(3),
                        intList.get(4), intList.get(5), intList.get(6), dateList.get(0),dateList.get(1), dateList.get(2),
                        dateList.get(3), dateList.get(4), dateList.get(5), dateList.get(6));

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
                List<int[]> intList = sig.getSignalValues();
                List<Date[]> dateList = sig.getDateValues();

                // Create the robot object
                Robot robotObj = new Robot(nRobots, cluster, intList.get(0), intList.get(1), intList.get(2), intList.get(3),
                        intList.get(4), intList.get(5), intList.get(6), dateList.get(0),dateList.get(1), dateList.get(2),
                        dateList.get(3), dateList.get(4), dateList.get(5), dateList.get(6));

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

        List<int[]> intList = new ArrayList<int[]>();
        List<Date[]> dateList = new ArrayList<Date[]>();

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

        // Assign the first value of the generates arrays to a list of arrays
        for (int i = 0; i < 7; i++) {

            // Add an int array
            int tempIntArray[] = new int[1];
            tempIntArray[0] = signalsArray[i];
            intList.add(tempIntArray);

            // Add a date array
            Date tempDateArray[] = new Date[1];
            tempDateArray[0] = dates[i];
            dateList.add(tempDateArray);
        }


        return new Signals(intList, dateList);
    }

    // Send the list to the server via sockets
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
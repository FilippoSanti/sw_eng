package controller;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import model.Robot;
import org.bson.Document;
import org.bson.conversions.Bson;
import simulation.inputSimulation;

import java.io.IOException;
import java.util.*;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.push;
import static com.mongodb.client.model.Updates.set;


public class DBManager {

    /* Conenct to the DB */
    public static MongoDatabase dbConnect() {

        MongoClientURI uri = new MongoClientURI("mongodb://admin:testadmin123@192.168.1.202/?authSource=unnamedb");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase("unnamedb");

        return db;

    }

    /* Insert robots in the DB */
    public static void saveDataToDB(MongoDatabase database, ArrayList<Robot> robotList) throws IOException {

        MongoCollection<Document> collection = database.getCollection("robot");
        List<Document> documents = new ArrayList<Document>();

        for (int i = 0; i < robotList.size(); i++) {

            // Save every robot into a temporary variable and add it to a list
            Document roboTemp = new Document("id", robotList.get(i).getId())
                    .append("cluster", robotList.get(i).getCluster())

                    .append("signal1", Arrays.asList(robotList.get(i).getSignal1()))
                    .append("signal2", Arrays.asList(robotList.get(i).getSignal2()))
                    .append("signal3", Arrays.asList(robotList.get(i).getSignal3()))
                    .append("signal4", Arrays.asList(robotList.get(i).getSignal4()))
                    .append("signal5", Arrays.asList(robotList.get(i).getSignal5()))
                    .append("signal6", Arrays.asList(robotList.get(i).getSignal6()))
                    .append("signal7", Arrays.asList(robotList.get(i).getSignal7()))

                    .append("signal1Time", Arrays.asList(robotList.get(i).getSignal1Time()))
                    .append("signal2Time", Arrays.asList(robotList.get(i).getSignal2Time()))
                    .append("signal3Time", Arrays.asList(robotList.get(i).getSignal3Time()))
                    .append("signal4Time", Arrays.asList(robotList.get(i).getSignal4Time()))
                    .append("signal5Time", Arrays.asList(robotList.get(i).getSignal5Time()))
                    .append("signal6Time", Arrays.asList(robotList.get(i).getSignal6Time()))
                    .append("signal7Time", Arrays.asList(robotList.get(i).getSignal7Time()));

            documents.add(roboTemp);
        }

        // Write the documents into the DB
        collection.insertMany(documents);

    }

    /* Get the list of robots from the database */
    public static ArrayList<Robot> getDataFromDB(MongoDatabase database) {

        MongoCollection<Document> collection = database.getCollection("robot");

        List<Document> robots = (List<Document>) collection.find().into(
                new ArrayList<Document>());

        ArrayList<Robot> robotList = new ArrayList<Robot>();

        int s1[] = null, s2[] = null, s3[] = null,
                s4[] = null, s5[] = null, s6[] = null, s7[] = null;

        Date[] d1 = null, d2 = null, d3 = null,
                d4 = null, d5 = null, d6 = null, d7 = null;

        for (Document robot : robots) {

            // Get every list for every signal array
            List<Integer> s1l = (List<Integer>) robot.get("signal1");
            List<Integer> s2l = (List<Integer>) robot.get("signal2");
            List<Integer> s3l = (List<Integer>) robot.get("signal3");
            List<Integer> s4l = (List<Integer>) robot.get("signal4");
            List<Integer> s5l = (List<Integer>) robot.get("signal5");
            List<Integer> s6l = (List<Integer>) robot.get("signal6");
            List<Integer> s7l = (List<Integer>) robot.get("signal7");

            // Store the date arrays
            List<Date> d1l = (List<Date>) robot.get("signal1Time");
            List<Date> d2l = (List<Date>) robot.get("signal2Time");
            List<Date> d3l = (List<Date>) robot.get("signal3Time");
            List<Date> d4l = (List<Date>) robot.get("signal4Time");
            List<Date> d5l = (List<Date>) robot.get("signal5Time");
            List<Date> d6l = (List<Date>) robot.get("signal6Time");
            List<Date> d7l = (List<Date>) robot.get("signal7Time");

            // Convert the lists to arrays
            s1 = listToIntArray(s1l);
            s2 = listToIntArray(s2l);
            s3 = listToIntArray(s3l);
            s4 = listToIntArray(s4l);
            s5 = listToIntArray(s5l);
            s6 = listToIntArray(s6l);
            s7 = listToIntArray(s7l);

            // Convert the lists to arrays
            d1 = listToDateArray(d1l);
            d2 = listToDateArray(d2l);
            d3 = listToDateArray(d3l);
            d4 = listToDateArray(d4l);
            d5 = listToDateArray(d5l);
            d6 = listToDateArray(d6l);
            d7 = listToDateArray(d7l);

            Robot roboTemp = new Robot(robot.getInteger("id"), robot.getInteger("cluster"), s1, s2, s3, s4, s5, s6, s7,
                    d1, d2, d3, d4, d5, d6, d7);

            robotList.add(roboTemp);
        }

        return robotList;
    }

    /* Update elements in the DB*/
    public static void runUpdateTests(MongoDatabase db, ArrayList<Robot> robotList) throws IOException, ClassNotFoundException {

        int robot = 0;

        // Get count of the documents stored in the db
        long robotCount = db.getCollection("robot").count();

        MongoCollection<Document> collection = db.getCollection("robot");

        long startTime = System.currentTimeMillis();

        List<Integer> robotParams = inputSimulation.getDataFromList();

        System.out.println("Robotparams.size = " +robotParams.get(1));
        long count = 0;

        for (int q = 0; q < robotParams.size(); q++) {
            count += robotParams.get(q);
        }

        for (int j = 0; j < count; j++) {

            collection.updateOne(eq("id", j), push("signal1", robotList.get(j).getSignal1()));
            System.out.println(j);

        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Updated " + robotParams.size() + " clusters containing " + robotCount + " robot entries in " + elapsedTime + "ms");

    }

    /* Return the size of a collection */
    public static long getCollectionSize(MongoDatabase db, String collName) {
        long collection = db.getCollection(collName).count();

        return collection;
    }

    /* Check if a collection exists */
    public static boolean collectionExists(MongoDatabase db) {
        boolean collectionExists = db.listCollectionNames()
                .into(new ArrayList<String>()).contains("robot");

        return collectionExists;
    }

    /* Convert a list to an int array */
    public static int[] listToIntArray(List<Integer> list) {

        int size = list.size();
        int[] result = new int[size];

        // Convert the list to an int array
        Integer[] temp = list.toArray(new Integer[size]);
        for (int n = 0; n < size; ++n) {
            result[n] = temp[n];
        }
        return result;
    }

    /* Convert a list to a date array */
    public static Date[] listToDateArray(List<Date> list) {

        int size = list.size();
        Date[] result = new Date[size];

        // Convert the list to an int array
        Date[] temp = list.toArray(new Date[size]);
        for (int n = 0; n < size; ++n) {
            result[n] = temp[n];
        }
        return result;
    }
}
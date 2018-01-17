package controller;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Robot;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DBManager {

    /* Conenct to the DB */
    public static MongoDatabase dbConnect() {

        MongoClientURI uri = new MongoClientURI("mongodb://admin:testadmin123@localhost/?authSource=unnamedb");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase("unnamedb");

        return db;
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

    /* Insert robots in the DB */
    public static void saveDataToDB(MongoDatabase database, ArrayList<Robot> robotList) throws IOException {

        MongoCollection<Document> collection = database.getCollection("robot");

        List<Document> documents = getSignalList(robotList);

        // Write the documents into the DB
        collection.insertMany(documents);

    }

    /* Get the list of signals from che robot object */
    public static List<Document> getSignalList(ArrayList<Robot> robotList) {

        List<Document> documents = new ArrayList<Document>();

        for (int i = 0; i < robotList.size(); i++) {

            Document roboTemp = new Document("id", robotList.get(i).getId());
            roboTemp.append("cluster", robotList.get(i).getCluster());

            // Scan the array ang get the signal
            for (int y = 0; y < robotList.get(i).getSignal1().length; y++) {

                List<Integer> ArrayIntList = new ArrayList<Integer>();
                ArrayIntList.add(robotList.get(i).getSignal1()[y]);
                roboTemp.append("signal1", ArrayIntList);

            }

            for (int y = 0; y < robotList.get(i).getSignal2().length; y++) {

                List<Integer> ArrayIntList = new ArrayList<Integer>();
                ArrayIntList.add(robotList.get(i).getSignal2()[y]);
                roboTemp.append("signal2", ArrayIntList);
            }


            for (int y = 0; y < robotList.get(i).getSignal3().length; y++) {
                List<Integer> ArrayIntList = new ArrayList<Integer>();
                ArrayIntList.add(robotList.get(i).getSignal3()[y]);
                roboTemp.append("signal3", ArrayIntList);
            }


            for (int y = 0; y < robotList.get(i).getSignal4().length; y++) {
                List<Integer> ArrayIntList = new ArrayList<Integer>();
                ArrayIntList.add(robotList.get(i).getSignal4()[y]);
                roboTemp.append("signal4", ArrayIntList);
            }

            for (int y = 0; y < robotList.get(i).getSignal5().length; y++) {
                List<Integer> ArrayIntList = new ArrayList<Integer>();
                ArrayIntList.add(robotList.get(i).getSignal5()[y]);
                roboTemp.append("signal5", ArrayIntList);
            }

            for (int y = 0; y < robotList.get(i).getSignal6().length; y++) {
                List<Integer> ArrayIntList = new ArrayList<Integer>();
                ArrayIntList.add(robotList.get(i).getSignal6()[y]);
                roboTemp.append("signal6", ArrayIntList);
            }

            for (int y = 0; y < robotList.get(i).getSignal7().length; y++) {
                List<Integer> ArrayIntList = new ArrayList<Integer>();
                ArrayIntList.add(robotList.get(i).getSignal7()[y]);
                roboTemp.append("signal7", ArrayIntList);
            }


            // Scan the array ang get the timestamps
            for (int y = 0; y < robotList.get(i).getSignal1Time().length; y++) {
                ArrayList<Date> ArrayDateList = new ArrayList<Date>();
                ArrayDateList.add(robotList.get(i).getSignal1Time()[y]);
                roboTemp.append("signal1Time", ArrayDateList);
            }

            for (int y = 0; y < robotList.get(i).getSignal2Time().length; y++) {
                ArrayList<Date> ArrayDateList = new ArrayList<Date>();
                ArrayDateList.add(robotList.get(i).getSignal2Time()[y]);
                roboTemp.append("signal2Time", ArrayDateList);
            }


            for (int y = 0; y < robotList.get(i).getSignal3Time().length; y++) {
                ArrayList<Date> ArrayDateList = new ArrayList<Date>();
                ArrayDateList.add(robotList.get(i).getSignal3Time()[y]);
                roboTemp.append("signal3Time", ArrayDateList);
            }

            for (int y = 0; y < robotList.get(i).getSignal4Time().length; y++) {
                ArrayList<Date> ArrayDateList = new ArrayList<Date>();
                ArrayDateList.add(robotList.get(i).getSignal4Time()[y]);
                roboTemp.append("signal4Time", ArrayDateList);
            }


            for (int y = 0; y < robotList.get(i).getSignal5Time().length; y++) {
                ArrayList<Date> ArrayDateList = new ArrayList<Date>();
                ArrayDateList.add(robotList.get(i).getSignal5Time()[y]);
                roboTemp.append("signal5Time", ArrayDateList);
            }


            for (int y = 0; y < robotList.get(i).getSignal6Time().length; y++) {
                ArrayList<Date> ArrayDateList = new ArrayList<Date>();
                ArrayDateList.add(robotList.get(i).getSignal6Time()[y]);
                roboTemp.append("signal6Time", ArrayDateList);
            }


            for (int y = 0; y < robotList.get(i).getSignal7Time().length; y++) {
                ArrayList<Date> ArrayDateList = new ArrayList<Date>();
                ArrayDateList.add(robotList.get(i).getSignal7Time()[y]);
                roboTemp.append("signal7Time", ArrayDateList);
            }

            documents.add(roboTemp);

        }
        return documents;
    }
}
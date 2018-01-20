package controller;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Robot;
import org.bson.Document;
import sun.security.pkcs11.Secmod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.*;


public class DBManager {

    public static String newLine = System.getProperty("line.separator");

    /* Conenct to the DB */
    public static MongoDatabase dbConnect() {

        MongoClientURI uri = new MongoClientURI("mongodb://admin:testadmin123@87.20.196.37/?authSource=unnamedb");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase("unnamedb");

        return db;
    }

    /* Update the list of robots in the db */
    public static void updateRobots(MongoDatabase db, ArrayList<Robot> newList) throws IOException {

        ArrayList<Robot> oldList = DBManager.getDataFromDB(DBManager.dbConnect());

        System.out.println(oldList.get(0).getSignal2().length);
        System.out.println(newList.get(0).getSignal2().length);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Delete the collection to insert a new one
        MongoCollection myCollection = db.getCollection("robot");


        for (int i = 0; i < oldList.size(); i++) {

            if (oldList.get(i).getSignal1()[oldList.get(i).getSignal1().length - 1]
                    != newList.get(i).getSignal1()[0]) {

                System.out.println("Nel robot " + oldList.get(i).getId() + " il segnale 1 " + oldList.get(i).getSignal1()[oldList.get(i).getSignal1().length - 1]+
                        " è cambiato in " +newList.get(i).getSignal1()[0]);

                int size = oldList.get(i).getSignal1().length;

                int tempArray[] = new int[size+1];
                Date tempDate[] = new Date[size+1];

                for (int k = 0; k < oldList.get(i).getSignal1().length; k++) {
                    tempArray[k] = oldList.get(i).getSignal1()[k];
                }

                for (int k = 0; k < oldList.get(i).getSignal1Time().length; k++) {
                    tempDate[k] = oldList.get(i).getSignal1Time()[k];
                }

                tempArray[tempArray.length-1] = newList.get(i).getSignal1()[0];
                tempDate[tempDate.length-1] = newList.get(i).getSignal1Time()[0];

                System.out.println("I numeri da stampare sono " + tempArray[tempArray.length-2] + ", " + tempArray[tempArray.length-1]);

                oldList.get(i).setSignal1(tempArray);
                oldList.get(i).setSignal1Time(tempDate);

            }

            if (oldList.get(i).getSignal2()[oldList.get(i).getSignal2().length - 1]
                    != newList.get(i).getSignal2()[0]) {

                System.out.println("Nel robot " + oldList.get(i).getId() + " il segnale 2 " + oldList.get(i).getSignal2()[oldList.get(i).getSignal2().length - 1]+
                        " è cambiato in " +newList.get(i).getSignal2()[0]);

                int size = oldList.get(i).getSignal2().length;

                int tempArray[] = new int[size+1];
                Date tempDate[] = new Date[size+1];

                for (int k = 0; k < oldList.get(i).getSignal2().length; k++) {
                    tempArray[k] = oldList.get(i).getSignal2()[k];
                }

                for (int k = 0; k < oldList.get(i).getSignal2Time().length; k++) {
                    tempDate[k] = oldList.get(i).getSignal2Time()[k];
                }

                tempArray[tempArray.length-1] = newList.get(i).getSignal2()[0];
                tempDate[tempDate.length-1] = newList.get(i).getSignal2Time()[0];

                System.out.println("I numeri da stampare sono " + tempArray[tempArray.length-2] + ", " + tempArray[tempArray.length-1]);

                oldList.get(i).setSignal2(tempArray);
                oldList.get(i).setSignal2Time(tempDate);


            }

            if (oldList.get(i).getSignal3()[oldList.get(i).getSignal3().length - 1]
                    != newList.get(i).getSignal3()[0]) {

                System.out.println("Nel robot " + oldList.get(i).getId() + " il segnale 3 " + oldList.get(i).getSignal3()[oldList.get(i).getSignal3().length - 1]+
                        " è cambiato in " +newList.get(i).getSignal3()[0]);

                int size = oldList.get(i).getSignal3().length;

                int tempArray[] = new int[size+1];
                Date tempDate[] = new Date[size+1];

                for (int k = 0; k < oldList.get(i).getSignal3().length; k++) {
                    tempArray[k] = oldList.get(i).getSignal3()[k];
                }

                for (int k = 0; k < oldList.get(i).getSignal3Time().length; k++) {
                    tempDate[k] = oldList.get(i).getSignal3Time()[k];
                }

                tempArray[tempArray.length-1] = newList.get(i).getSignal3()[0];
                tempDate[tempDate.length-1] = newList.get(i).getSignal3Time()[0];

                System.out.println("I numeri da stampare sono " + tempArray[tempArray.length-2] + ", " + tempArray[tempArray.length-1]);

                oldList.get(i).setSignal3(tempArray);
                oldList.get(i).setSignal3Time(tempDate);

            }

            if (oldList.get(i).getSignal4()[oldList.get(i).getSignal4().length - 1]
                    != newList.get(i).getSignal4()[0]) {

                System.out.println("Nel robot " + oldList.get(i).getId() + " il segnale 4 " + oldList.get(i).getSignal4()[oldList.get(i).getSignal4().length - 1]+
                        " è cambiato in " +newList.get(i).getSignal4()[0]);

                int size = oldList.get(i).getSignal4().length;

                int tempArray[] = new int[size+1];
                Date tempDate[] = new Date[size+1];

                for (int k = 0; k < oldList.get(i).getSignal4().length; k++) {
                    tempArray[k] = oldList.get(i).getSignal4()[k];
                }

                for (int k = 0; k < oldList.get(i).getSignal4Time().length; k++) {
                    tempDate[k] = oldList.get(i).getSignal4Time()[k];
                }

                tempArray[tempArray.length-1] = newList.get(i).getSignal4()[0];
                tempDate[tempDate.length-1] = newList.get(i).getSignal4Time()[0];

                System.out.println("I numeri da stampare sono " + tempArray[tempArray.length-2] + ", " + tempArray[tempArray.length-1]);

                oldList.get(i).setSignal4(tempArray);
                oldList.get(i).setSignal4Time(tempDate);

            }

            if (oldList.get(i).getSignal5()[oldList.get(i).getSignal5().length - 1]
                    != newList.get(i).getSignal5()[0]) {

                System.out.println("Nel robot " + oldList.get(i).getId() + " il segnale 5 " + oldList.get(i).getSignal5()[oldList.get(i).getSignal5().length - 1]+
                        " è cambiato in " +newList.get(i).getSignal5()[0]);

                int size = oldList.get(i).getSignal5().length;

                int tempArray[] = new int[size+1];
                Date tempDate[] = new Date[size+1];

                for (int k = 0; k < oldList.get(i).getSignal5().length; k++) {
                    tempArray[k] = oldList.get(i).getSignal5()[k];
                }

                for (int k = 0; k < oldList.get(i).getSignal5Time().length; k++) {
                    tempDate[k] = oldList.get(i).getSignal5Time()[k];
                }

                tempArray[tempArray.length-1] = newList.get(i).getSignal5()[0];
                tempDate[tempDate.length-1] = newList.get(i).getSignal5Time()[0];

                System.out.println("I numeri da stampare sono " + tempArray[tempArray.length-2] + ", " + tempArray[tempArray.length-1]);

                oldList.get(i).setSignal5(tempArray);
                oldList.get(i).setSignal5Time(tempDate);

            }


            if (oldList.get(i).getSignal6()[oldList.get(i).getSignal6().length - 1]
                    != newList.get(i).getSignal6()[0]) {

                System.out.println("Nel robot " + oldList.get(i).getId() + " il segnale 6 " + oldList.get(i).getSignal6()[oldList.get(i).getSignal6().length - 1]+
                        " è cambiato in " +newList.get(i).getSignal6()[0]);

                int size = oldList.get(i).getSignal6().length;

                int tempArray[] = new int[size+1];
                Date tempDate[] = new Date[size+1];

                for (int k = 0; k < oldList.get(i).getSignal6().length; k++) {
                    tempArray[k] = oldList.get(i).getSignal6()[k];
                }

                for (int k = 0; k < oldList.get(i).getSignal6Time().length; k++) {
                    tempDate[k] = oldList.get(i).getSignal6Time()[k];
                }

                tempArray[tempArray.length-1] = newList.get(i).getSignal6()[0];
                tempDate[tempDate.length-1] = newList.get(i).getSignal6Time()[0];

                System.out.println("I numeri da stampare sono " + tempArray[tempArray.length-2] + ", " + tempArray[tempArray.length-1]);

                oldList.get(i).setSignal6(tempArray);
                oldList.get(i).setSignal6Time(tempDate);
            }


            if (oldList.get(i).getSignal7()[oldList.get(i).getSignal7().length - 1]
                    != newList.get(i).getSignal7()[0]) {

                System.out.println("Nel robot " + oldList.get(i).getId() + " il segnale 7 " + oldList.get(i).getSignal7()[oldList.get(i).getSignal7().length - 1]+
                        " è cambiato in " +newList.get(i).getSignal7()[0]);

                int size = oldList.get(i).getSignal7().length;

                int tempArray[] = new int[size+1];
                Date tempDate[] = new Date[size+1];

                for (int k = 0; k < oldList.get(i).getSignal7().length; k++) {
                    tempArray[k] = oldList.get(i).getSignal7()[k];
                }

                for (int k = 0; k < oldList.get(i).getSignal7Time().length; k++) {
                    tempDate[k] = oldList.get(i).getSignal7Time()[k];
                }

                tempArray[tempArray.length-1] = newList.get(i).getSignal7()[0];
                tempDate[tempDate.length-1] = newList.get(i).getSignal7Time()[0];

                System.out.println("I numeri da stampare sono " + tempArray[tempArray.length-2] + ", " + tempArray[tempArray.length-1]);

                oldList.get(i).setSignal7(tempArray);
                oldList.get(i).setSignal7Time(tempDate);

            }
        }

        myCollection.drop();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DBManager.saveDataToDB(db, oldList);

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

    public static boolean Authentication (String username, String password)

    {
        MongoDatabase database = dbConnect();
        MongoCollection<Document> collection = database.getCollection("utenti");

        List<Document> utenti = (List<Document>) collection.find((and(eq("username", username), eq("password", password)))).into(
                new ArrayList<Document>());




      return !utenti.isEmpty();
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
        // Signal 1
        for (int i = 0; i < robotList.size(); i++) {

            List<Integer> ArrayIntList1 = new ArrayList<Integer>();
            List<Integer> ArrayIntList2 = new ArrayList<Integer>();
            List<Integer> ArrayIntList3 = new ArrayList<Integer>();
            List<Integer> ArrayIntList4 = new ArrayList<Integer>();
            List<Integer> ArrayIntList5 = new ArrayList<Integer>();
            List<Integer> ArrayIntList6 = new ArrayList<Integer>();
            List<Integer> ArrayIntList7 = new ArrayList<Integer>();

            List<Date> ArrayDateList1 = new ArrayList<Date>();
            List<Date> ArrayDateList2 = new ArrayList<Date>();
            List<Date> ArrayDateList3 = new ArrayList<Date>();
            List<Date> ArrayDateList4 = new ArrayList<Date>();
            List<Date> ArrayDateList5 = new ArrayList<Date>();
            List<Date> ArrayDateList6 = new ArrayList<Date>();
            List<Date> ArrayDateList7 = new ArrayList<Date>();


            Document roboTemp = new Document("id", robotList.get(i).getId());
            roboTemp.append("cluster", robotList.get(i).getCluster());

            // Scan the array and get the signal lists
            for (int y = 0; y < robotList.get(i).getSignal1().length; y++) {

                ArrayIntList1.add(robotList.get(i).getSignal1()[y]);
            }

            roboTemp.append("signal1", ArrayIntList1);

            for (int y = 0; y < robotList.get(i).getSignal2().length; y++) {
                ArrayIntList2.add(robotList.get(i).getSignal2()[y]);
            }

            roboTemp.append("signal2", ArrayIntList2);

            for (int y = 0; y < robotList.get(i).getSignal3().length; y++) {
                ArrayIntList3.add(robotList.get(i).getSignal3()[y]);
            }

            roboTemp.append("signal3", ArrayIntList3);

            for (int y = 0; y < robotList.get(i).getSignal4().length; y++) {
                ArrayIntList4.add(robotList.get(i).getSignal4()[y]);
            }

            roboTemp.append("signal4", ArrayIntList4);

            for (int y = 0; y < robotList.get(i).getSignal5().length; y++) {
                ArrayIntList5.add(robotList.get(i).getSignal5()[y]);
            }

            roboTemp.append("signal5", ArrayIntList5);

            for (int y = 0; y < robotList.get(i).getSignal6().length; y++) {
                ArrayIntList6.add(robotList.get(i).getSignal6()[y]);
            }

            roboTemp.append("signal6", ArrayIntList6);

            for (int y = 0; y < robotList.get(i).getSignal7().length; y++) {
                ArrayIntList7.add(robotList.get(i).getSignal7()[y]);
            }

            roboTemp.append("signal7", ArrayIntList7);


            // Scan the array and get the timestamps
            for (int y = 0; y < robotList.get(i).getSignal1Time().length; y++) {
                ArrayDateList1.add(robotList.get(i).getSignal1Time()[y]);
            }

            roboTemp.append("signal1Time", ArrayDateList1);

            for (int y = 0; y < robotList.get(i).getSignal2Time().length; y++) {
                ArrayDateList2.add(robotList.get(i).getSignal2Time()[y]);
            }

            roboTemp.append("signal2Time", ArrayDateList2);

            for (int y = 0; y < robotList.get(i).getSignal3Time().length; y++) {
                ArrayDateList3.add(robotList.get(i).getSignal3Time()[y]);
            }

            roboTemp.append("signal3Time", ArrayDateList3);

            for (int y = 0; y < robotList.get(i).getSignal4Time().length; y++) {
                ArrayDateList4.add(robotList.get(i).getSignal4Time()[y]);
            }

            roboTemp.append("signal4Time", ArrayDateList4);

            for (int y = 0; y < robotList.get(i).getSignal5Time().length; y++) {
                ArrayDateList5.add(robotList.get(i).getSignal5Time()[y]);
            }

            roboTemp.append("signal5Time", ArrayDateList5);

            for (int y = 0; y < robotList.get(i).getSignal6Time().length; y++) {
                ArrayDateList6.add(robotList.get(i).getSignal6Time()[y]);
            }

            roboTemp.append("signal6Time", ArrayDateList6);

            for (int y = 0; y < robotList.get(i).getSignal7Time().length; y++) {
                ArrayDateList7.add(robotList.get(i).getSignal7Time()[y]);
            }

            roboTemp.append("signal7Time", ArrayDateList7);

            documents.add(roboTemp);

        }
        return documents;
    }
}
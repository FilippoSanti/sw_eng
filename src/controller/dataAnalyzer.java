package controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Robot;
import org.bson.Document;
import simulation.inputSimulation;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.push;

public class dataAnalyzer {

    // Find out which signals have changed and add them to a new list
    // This is done to make the update procedure faster
    public static void detectSignalChanges(MongoDatabase db, ArrayList<Robot> newList) throws IOException, ClassNotFoundException {

        // First we get the signals from the DB
        ArrayList<Robot> oldList = DBManager.getDataFromDB(DBManager.dbConnect());
        ArrayList<String> sortedList = new ArrayList<>();

        System.out.println("Oldlist size: "+oldList.size());
        System.out.println("Newlist size: "+newList.size());

        long upCount = 0;

        MongoCollection<Document> collection = db.getCollection("robot");

        // Check if the last element of the signalX array has changed
        // If so, we push another element to the array and its timestamp
        for (int i = 0; i < newList.size(); i++) {

            if (oldList.get(i).getSignal1()[oldList.get(i).getSignal1().length - 1]
                    != newList.get(i).getSignal1()[0]) {

                String tempString = newList.get(i).getId() + "," + "1" + "," +newList.get(i).getSignal1()[0] +
                        "," +newList.get(i).getSignal1Time()[0];

                sortedList.add(tempString);
            }

            if (oldList.get(i).getSignal2()[oldList.get(i).getSignal2().length - 1]
                    != newList.get(i).getSignal2()[0]) {

                String tempString = newList.get(i).getId() + "," + "2" + "," +newList.get(i).getSignal2()[0] +
                        "," +newList.get(i).getSignal2Time()[0];

                sortedList.add(tempString);
            }

            if (oldList.get(i).getSignal3()[oldList.get(i).getSignal3().length - 1]
                    != newList.get(i).getSignal3()[0]) {

                String tempString = newList.get(i).getId() + "," + "3" + "," +newList.get(i).getSignal3()[0] +
                        "," +newList.get(i).getSignal3Time()[0];

                sortedList.add(tempString);
            }

            if (oldList.get(i).getSignal4()[oldList.get(i).getSignal4().length - 1]
                    != newList.get(i).getSignal4()[0]) {

                String tempString = newList.get(i).getId() + "," + "4" + "," +newList.get(i).getSignal4()[0] +
                        "," +newList.get(i).getSignal4Time()[0];

                sortedList.add(tempString);
            }

            if (oldList.get(i).getSignal5()[oldList.get(i).getSignal5().length - 1]
                    != newList.get(i).getSignal5()[0]) {

                String tempString = newList.get(i).getId() + "," + "5" + "," +newList.get(i).getSignal5()[0] +
                        "," +newList.get(i).getSignal5Time()[0];

                sortedList.add(tempString);
            }


            if (oldList.get(i).getSignal6()[oldList.get(i).getSignal6().length - 1]
                    != newList.get(i).getSignal6()[0]) {

                String tempString = newList.get(i).getId() + "," + "6" + "," +newList.get(i).getSignal6()[0] +
                        "," +newList.get(i).getSignal6Time()[0];

                sortedList.add(tempString);
            }


            if (oldList.get(i).getSignal7()[oldList.get(i).getSignal7().length - 1]
                    != newList.get(i).getSignal7()[0]) {

                String tempString = newList.get(i).getId() + "," + "7" + "," +newList.get(i).getSignal7()[0] +
                        "," +newList.get(i).getSignal7Time()[0];

                sortedList.add(tempString);
            }
        }

        System.out.println(sortedList.size());
        System.out.println(sortedList.get(1));

        String[] tempStr = null;
        int robotID, signalX, value;
        String date;

        for (int h = 0; h < sortedList.size(); h++) {
            tempStr = sortedList.get(h).split(",");

            robotID = Integer.parseInt(tempStr[0]);
            signalX = Integer.parseInt(tempStr[1]);
            value   = Integer.parseInt(tempStr[2]);
            date    = tempStr[3];

            updateRobot(collection, robotID, "signal"+signalX, value, "signalTime"+signalX, date);
            System.out.println("Updated robot: " +robotID + " with signal"+signalX + " = " + value);
        }

    }

    public static void updateRobot (MongoCollection collection, int eqValue, String signal, int signalValue, String signalTime, String signalTimeValue) {

        collection.updateOne(eq("id", eqValue),
                combine(push(signal, signalValue),
                        push(signalTime, signalTimeValue))
        );

    }

    public static void testLists(ArrayList<Robot> newList, MongoDatabase db) {

        MongoCollection<Document> collection = db.getCollection("robot");

        // First we get the signals from the DB
        ArrayList<Robot> oldList = DBManager.getDataFromDB(DBManager.dbConnect());

        System.out.println("Oldlist size: "+oldList.size());
        System.out.println("id to update "+newList.get(0).getId());

        if (oldList.get(0).getSignal1()[oldList.get(0).getSignal1().length-1] == newList.get(0).getSignal2()[0]) {

            System.out.println("equal...updating");

            collection.updateOne(eq("id", newList.get(78).getId()),
                    combine(push("signal1", newList.get(0).getSignal1()[0]),
                            push("signal1Time", newList.get(0).getSignal1Time()[0])));

        } else System.out.println("Not equal");

    }

    // Save the parameters to a tmp file
    public static void saveDataToList(List<String> robotParams) throws IOException {

        FileOutputStream fos = new FileOutputStream("test.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(robotParams);
        oos.close();
    }
}
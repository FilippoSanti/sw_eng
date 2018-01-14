package controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Robot;
import org.bson.Document;
import simulation.inputSimulation;

import java.io.IOException;
import java.util.ArrayList;
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

        System.out.println("Oldlist size: "+oldList.size());
        System.out.println("Newlist size: "+newList.size());

        long upCount = 0;

        MongoCollection<Document> collection = db.getCollection("robot");

        // Check if the last element of the signalX array has changed
        // If so, we push another element to the array anmd its timestamp
        for (int i = 0; i < newList.size(); i++) {

            if (oldList.get(i).getSignal1()[oldList.get(i).getSignal1().length - 1]
                    != newList.get(i).getSignal1()[0]) {

                // Update the entry in the db
                collection.updateOne(eq("id", newList.get(i).getId()),
                        combine(push("signal1", newList.get(i).getSignal1()[0]),
                                push("signal1Time", newList.get(i).getSignal1Time()[0]))
                );
                upCount++;
                System.out.println("Signal 1 of robot "+oldList.get(i).getId()+ " was: " +
                        oldList.get(i).getSignal1()[oldList.get(i).getSignal1().length - 1] +
                        " and it has changed to " +newList.get(i).getSignal1()[0]);
            }

            if (oldList.get(i).getSignal2()[oldList.get(i).getSignal2().length - 1]
                    != newList.get(i).getSignal2()[0]) {
                collection.updateOne(eq("id", newList.get(i).getId()),
                        combine(push("signal2", newList.get(i).getSignal2()[0]),
                                push("signal2Time", newList.get(i).getSignal2Time()[0]))
                );
                upCount++;
                System.out.println("Signal 2 of robot "+oldList.get(i).getId()+ " was: " +
                        oldList.get(i).getSignal2()[oldList.get(i).getSignal2().length - 1] +
                        " and it has changed to " +newList.get(i).getSignal2()[0]);
            }

            if (oldList.get(i).getSignal3()[oldList.get(i).getSignal3().length - 1]
                    != newList.get(i).getSignal3()[0]) {
                collection.updateOne(eq("id", newList.get(i).getId()),
                        combine(push("signal3", newList.get(i).getSignal3()[0]),
                                push("signal3Time", newList.get(i).getSignal3Time()[0]))
                );
                upCount++;
                System.out.println("Signal 3 of robot "+oldList.get(i).getId()+ " was: " +
                        oldList.get(i).getSignal3()[oldList.get(i).getSignal3().length - 1] +
                        " and it has changed to " +newList.get(i).getSignal3()[0]);
            }

            if (oldList.get(i).getSignal4()[oldList.get(i).getSignal4().length - 1]
                    != newList.get(i).getSignal4()[0]) {
                collection.updateOne(eq("id", newList.get(i).getId()),
                        combine(push("signal4", newList.get(i).getSignal4()[0]),
                                push("signal4Time", newList.get(i).getSignal4Time()[0]))
                );
                upCount++;
                System.out.println("Signal 4 of robot "+oldList.get(i).getId()+ " was: " +
                        oldList.get(i).getSignal4()[oldList.get(i).getSignal4().length - 1] +
                        " and it has changed to " +newList.get(i).getSignal4()[0]);
            }

            if (oldList.get(i).getSignal5()[oldList.get(i).getSignal5().length - 1]
                    != newList.get(i).getSignal5()[0]) {
                collection.updateOne(eq("id", newList.get(i).getId()),
                        combine(push("signal5", newList.get(i).getSignal5()[0]),
                                push("signal5Time", newList.get(i).getSignal5Time()[0]))
                );
                upCount++;
                System.out.println("Signal 5 of robot "+oldList.get(i).getId()+ " was: " +
                        oldList.get(i).getSignal5()[oldList.get(i).getSignal5().length - 1] +
                        " and it has changed to " +newList.get(i).getSignal5()[0]);
            }

            if (oldList.get(i).getSignal6()[oldList.get(i).getSignal6().length - 1]
                    != newList.get(i).getSignal6()[0]) {
                collection.updateOne(eq("id", newList.get(i).getId()),
                        combine(push("signal6", newList.get(i).getSignal6()[0]),
                                push("signal6Time", newList.get(i).getSignal6Time()[0]))
                );
                upCount++;
                System.out.println("Signal 6 of robot "+oldList.get(i).getId()+ " was: " +
                        oldList.get(i).getSignal6()[oldList.get(i).getSignal6().length - 1] +
                        " and it has changed to " +newList.get(i).getSignal6()[0]);
            }

            if (oldList.get(i).getSignal7()[oldList.get(i).getSignal7().length - 1]
                    != newList.get(i).getSignal7()[0]) {
                collection.updateOne(eq("id", newList.get(i).getId()),
                        combine(push("signal7", newList.get(i).getSignal7()[0]),
                                push("signal7Time", newList.get(i).getSignal7Time()[0]))
                );
                upCount++;
                System.out.println("Signal 6 of robot "+oldList.get(i).getId()+ " was: " +
                        oldList.get(i).getSignal6()[oldList.get(i).getSignal6().length - 1] +
                        " and it has changed to " +newList.get(i).getSignal6()[0]);
            }
        }
    }
}
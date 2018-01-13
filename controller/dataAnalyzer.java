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
import static com.mongodb.client.model.Updates.push;

public class dataAnalyzer {

    // Find out which signals have changed and add them to a new list
    // This is done to make the update procedure faster
    public static void detectSignalChanges(MongoDatabase db, ArrayList<Robot> newList) throws IOException, ClassNotFoundException {

        // First we get the signals from the DB
        ArrayList<Robot> oldList = DBManager.getDataFromDB(DBManager.dbConnect());
        ArrayList<Robot> tempList = null;
        long roboCount = 0;

        MongoCollection<Document> collection = db.getCollection("robot");

        List<Integer> robotParams = inputSimulation.getDataFromList();

        for (int q = 0; q < robotParams.size(); q++) {

            roboCount += robotParams.get(q);

        }

        // Check if any of the signals has changed
        for (int i = 0; i < newList.size(); i++) {

            // If signal x has changed, we update the field in the db
            if (oldList.get(i).getSignal1() != newList.get(i).getSignal1()) {
                collection.updateOne(eq("id", newList.get(i).getId()), push("signal1", newList.get(i).getSignal1()));
            }

            if (oldList.get(i).getSignal2() != newList.get(i).getSignal2()) {
                collection.updateOne(eq("id", newList.get(i).getId()), push("signal2", newList.get(i).getSignal2()));
            }

            if (oldList.get(i).getSignal3() != newList.get(i).getSignal3()) {
                collection.updateOne(eq("id", newList.get(i).getId()), push("signal3", newList.get(i).getSignal3()));
            }

            if (oldList.get(i).getSignal4() != newList.get(i).getSignal4()) {
                collection.updateOne(eq("id", newList.get(i).getId()), push("signal4", newList.get(i).getSignal4()));
            }

            if (oldList.get(i).getSignal4() != newList.get(i).getSignal4()) {
                collection.updateOne(eq("id", newList.get(i).getId()), push("signal5", newList.get(i).getSignal5()));
            }

            if (oldList.get(i).getSignal4() != newList.get(i).getSignal4()) {
                collection.updateOne(eq("id", newList.get(i).getId()), push("signal6", newList.get(i).getSignal6()));
            }

            if (oldList.get(i).getSignal4() != newList.get(i).getSignal4()) {
                collection.updateOne(eq("id", newList.get(i).getId()), push("signal7", newList.get(i).getSignal7()));
            }
        }
    }
}
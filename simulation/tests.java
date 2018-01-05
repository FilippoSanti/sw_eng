package simulation;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import controller.DBManager;
import model.Robot;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class tests {

    public static void main(String[] args) {
        //runUpdateTests(DBManager.dbConnect(), 100);

        // Get data into a list
        ArrayList<Robot> robotList = DBManager.getDataFromDB(DBManager.dbConnect());

        System.out.println(robotList.get(1).getSignal1Time());

    }

    public static void runGetTests() {
        long startTime = System.currentTimeMillis();
        // Get data into a list
        ArrayList<Robot> robotList = DBManager.getDataFromDB(DBManager.dbConnect());

        System.out.println(robotList.size());

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Got " + robotList.size() + " robots in " + elapsedTime + "ms");
    }
}

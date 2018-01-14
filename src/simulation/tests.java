package simulation;

import com.mongodb.client.MongoCollection;
import controller.DBManager;
import model.Robot;

import java.util.ArrayList;

public class tests {

    public static void main(String[] args) {

        //runUpdateTests(DBManager.dbConnect(), 100);

        // Get data into a list
        ArrayList<Robot> robotList = DBManager.getDataFromDB(DBManager.dbConnect());

        int array[] = robotList.get(1).getSignal1();

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
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

    public static void runUpdate(MongoCollection collection) {
    }
}
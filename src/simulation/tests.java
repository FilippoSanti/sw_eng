package simulation;

import com.mongodb.client.MongoCollection;
import controller.DBManager;
import model.Robot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class tests {

    public static void main(String[] args) {


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
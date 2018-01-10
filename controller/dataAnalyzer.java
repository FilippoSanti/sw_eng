package controller;

import model.Robot;

import java.util.ArrayList;

public class dataAnalyzer {

    // Find out which signals have changed
    public static void detectSignalChanges(ArrayList<Robot> newList) {

        // First we get the signals from the DB
        ArrayList<Robot> oldList = DBManager.getDataFromDB(DBManager.dbConnect());

        // Check if any of the signals has changed

        for (int i = 0; i < newList.size(); i++) {

            if (newList.get(i) != oldList.get(i)) {
                System.out.println("Signal has changed: " + oldList.get(i) + "to" + newList.get(i));
            }

        }
    }

}
package controller;

import model.Robot;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Robot> oldList = DBManager.getDataFromDB(DBManager.dbConnect());
        Robot robot1 = oldList.get(0);
        long minute = dataAnalyzer.CalculateDownTime(robot1, 60, 0, false);
        System.out.println(minute);
    }
}

package controller;

import model.Robot;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ArrayList<Robot> oldList = DBManager.getDataFromDB(DBManager.dbConnect());
        Robot robot1 = oldList.get(87820);

        double minute = dataAnalyzer.inefficiencyRateOfARobot(robot1).getInefficiencyRate();
        System.out.println(minute);
          double prova = dataAnalyzer.inefficiencyRateByCluster(dataAnalyzer.inefficiencyRateAllRobot(oldList)).get(0).getInefficiencyRate();
         System.out.println(prova);
    }
}

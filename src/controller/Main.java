package controller;

import model.InefficiencyRate;
import model.InefficiencyRateByCluster;
import model.Robot;
import view.viewIRClusterRobot;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ArrayList<Robot> oldList = DBManager.getDataFromDB(DBManager.dbConnect());
        Robot robot1 = oldList.get(0);


        ArrayList<InefficiencyRate> allThings = controller.dataAnalyzer.inefficiencyRateAllRobot(oldList);
        ArrayList<InefficiencyRateByCluster> zsdas = controller.dataAnalyzer.inefficiencyRateByCluster(allThings);

        ArrayList<Double> asdfsdf = controller.dataAnalyzer.calculateIRByArea(zsdas);

        for (int i = 0; i < asdfsdf.size(); i++) {
            System.out.println(asdfsdf.get(i));
        }

    }
}

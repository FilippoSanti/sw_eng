package simulation;

import controller.DBManager;
import model.Robot;

import java.util.ArrayList;

public class tests {

    public static void main(String[] args) {

        // Get data into a list
        ArrayList<Robot> robotList = DBManager.getDataFromDB(DBManager.dbConnect());

        for (int i = 0; i < robotList.size(); i++) {
            System.out.println(robotList.get(i).getCluster());
        }

    }

}

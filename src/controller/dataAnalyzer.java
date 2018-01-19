package controller;

import com.mongodb.Mongo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Robot;
import org.bson.Document;
import simulation.inputSimulation;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.push;

public class dataAnalyzer {

    public static String newLine = System.getProperty("line.separator");

    public static void updateRobotsDB(MongoDatabase db, ArrayList<Robot> newList) throws IOException {

        ArrayList<Robot> oldList = DBManager.getDataFromDB(DBManager.dbConnect());

        System.out.println(oldList.get(0).getSignal2().length);
        System.out.println(newList.get(0).getSignal2().length);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Delete the collection to insert a new one
        MongoCollection myCollection = db.getCollection("robot");


        for (int i = 0; i < oldList.size(); i++) {

            if (oldList.get(i).getSignal1()[oldList.get(i).getSignal1().length - 1]
                    != newList.get(i).getSignal1()[0]) {

                System.out.println("Nel robot " + oldList.get(i).getId() + " il segnale 1 " + oldList.get(i).getSignal1()[oldList.get(i).getSignal1().length - 1]+
                " è cambiato in " +newList.get(i).getSignal1()[0]);

                int size = oldList.get(i).getSignal1().length;

                int tempArray[] = new int[size+1];
                Date tempDate[] = new Date[size+1];

                for (int k = 0; k < oldList.get(i).getSignal1().length; k++) {
                    tempArray[k] = oldList.get(i).getSignal1()[k];
                }

                for (int k = 0; k < oldList.get(i).getSignal1Time().length; k++) {
                    tempDate[k] = oldList.get(i).getSignal1Time()[k];
                }

                tempArray[tempArray.length-1] = newList.get(i).getSignal1()[0];
                tempDate[tempDate.length-1] = newList.get(i).getSignal1Time()[0];

                System.out.println("I numeri da stampare sono " + tempArray[tempArray.length-2] + ", " + tempArray[tempArray.length-1]);

                oldList.get(i).setSignal1(tempArray);
                oldList.get(i).setSignal1Time(tempDate);

            }

            if (oldList.get(i).getSignal2()[oldList.get(i).getSignal2().length - 1]
                    != newList.get(i).getSignal2()[0]) {

                System.out.println("Nel robot " + oldList.get(i).getId() + " il segnale 2 " + oldList.get(i).getSignal2()[oldList.get(i).getSignal2().length - 1]+
                        " è cambiato in " +newList.get(i).getSignal2()[0]);

                int size = oldList.get(i).getSignal2().length;

                int tempArray[] = new int[size+1];
                Date tempDate[] = new Date[size+1];

                for (int k = 0; k < oldList.get(i).getSignal2().length; k++) {
                    tempArray[k] = oldList.get(i).getSignal2()[k];
                }

                for (int k = 0; k < oldList.get(i).getSignal2Time().length; k++) {
                    tempDate[k] = oldList.get(i).getSignal2Time()[k];
                }

                tempArray[tempArray.length-1] = newList.get(i).getSignal2()[0];
                tempDate[tempDate.length-1] = newList.get(i).getSignal2Time()[0];

                System.out.println("I numeri da stampare sono " + tempArray[tempArray.length-2] + ", " + tempArray[tempArray.length-1]);

                oldList.get(i).setSignal2(tempArray);
                oldList.get(i).setSignal2Time(tempDate);


            }

            if (oldList.get(i).getSignal3()[oldList.get(i).getSignal3().length - 1]
                    != newList.get(i).getSignal3()[0]) {

                System.out.println("Nel robot " + oldList.get(i).getId() + " il segnale 3 " + oldList.get(i).getSignal3()[oldList.get(i).getSignal3().length - 1]+
                        " è cambiato in " +newList.get(i).getSignal3()[0]);

                int size = oldList.get(i).getSignal3().length;

                int tempArray[] = new int[size+1];
                Date tempDate[] = new Date[size+1];

                for (int k = 0; k < oldList.get(i).getSignal3().length; k++) {
                    tempArray[k] = oldList.get(i).getSignal3()[k];
                }

                for (int k = 0; k < oldList.get(i).getSignal3Time().length; k++) {
                    tempDate[k] = oldList.get(i).getSignal3Time()[k];
                }

                tempArray[tempArray.length-1] = newList.get(i).getSignal3()[0];
                tempDate[tempDate.length-1] = newList.get(i).getSignal3Time()[0];

                System.out.println("I numeri da stampare sono " + tempArray[tempArray.length-2] + ", " + tempArray[tempArray.length-1]);

                oldList.get(i).setSignal3(tempArray);
                oldList.get(i).setSignal3Time(tempDate);

            }

            if (oldList.get(i).getSignal4()[oldList.get(i).getSignal4().length - 1]
                    != newList.get(i).getSignal4()[0]) {

                System.out.println("Nel robot " + oldList.get(i).getId() + " il segnale 4 " + oldList.get(i).getSignal4()[oldList.get(i).getSignal4().length - 1]+
                        " è cambiato in " +newList.get(i).getSignal4()[0]);

                int size = oldList.get(i).getSignal4().length;

                int tempArray[] = new int[size+1];
                Date tempDate[] = new Date[size+1];

                for (int k = 0; k < oldList.get(i).getSignal4().length; k++) {
                    tempArray[k] = oldList.get(i).getSignal4()[k];
                }

                for (int k = 0; k < oldList.get(i).getSignal4Time().length; k++) {
                    tempDate[k] = oldList.get(i).getSignal4Time()[k];
                }

                tempArray[tempArray.length-1] = newList.get(i).getSignal4()[0];
                tempDate[tempDate.length-1] = newList.get(i).getSignal4Time()[0];

                System.out.println("I numeri da stampare sono " + tempArray[tempArray.length-2] + ", " + tempArray[tempArray.length-1]);

                oldList.get(i).setSignal4(tempArray);
                oldList.get(i).setSignal4Time(tempDate);

            }

            if (oldList.get(i).getSignal5()[oldList.get(i).getSignal5().length - 1]
                    != newList.get(i).getSignal5()[0]) {

                System.out.println("Nel robot " + oldList.get(i).getId() + " il segnale 5 " + oldList.get(i).getSignal5()[oldList.get(i).getSignal5().length - 1]+
                        " è cambiato in " +newList.get(i).getSignal5()[0]);

                int size = oldList.get(i).getSignal5().length;

                int tempArray[] = new int[size+1];
                Date tempDate[] = new Date[size+1];

                for (int k = 0; k < oldList.get(i).getSignal5().length; k++) {
                    tempArray[k] = oldList.get(i).getSignal5()[k];
                }

                for (int k = 0; k < oldList.get(i).getSignal5Time().length; k++) {
                    tempDate[k] = oldList.get(i).getSignal5Time()[k];
                }

                tempArray[tempArray.length-1] = newList.get(i).getSignal5()[0];
                tempDate[tempDate.length-1] = newList.get(i).getSignal5Time()[0];

                System.out.println("I numeri da stampare sono " + tempArray[tempArray.length-2] + ", " + tempArray[tempArray.length-1]);

                oldList.get(i).setSignal5(tempArray);
                oldList.get(i).setSignal5Time(tempDate);

            }


            if (oldList.get(i).getSignal6()[oldList.get(i).getSignal6().length - 1]
                    != newList.get(i).getSignal6()[0]) {

                System.out.println("Nel robot " + oldList.get(i).getId() + " il segnale 6 " + oldList.get(i).getSignal6()[oldList.get(i).getSignal6().length - 1]+
                        " è cambiato in " +newList.get(i).getSignal6()[0]);

                int size = oldList.get(i).getSignal6().length;

                int tempArray[] = new int[size+1];
                Date tempDate[] = new Date[size+1];

                for (int k = 0; k < oldList.get(i).getSignal6().length; k++) {
                    tempArray[k] = oldList.get(i).getSignal6()[k];
                }

                for (int k = 0; k < oldList.get(i).getSignal6Time().length; k++) {
                    tempDate[k] = oldList.get(i).getSignal6Time()[k];
                }

                tempArray[tempArray.length-1] = newList.get(i).getSignal6()[0];
                tempDate[tempDate.length-1] = newList.get(i).getSignal6Time()[0];

                System.out.println("I numeri da stampare sono " + tempArray[tempArray.length-2] + ", " + tempArray[tempArray.length-1]);

                oldList.get(i).setSignal6(tempArray);
                oldList.get(i).setSignal6Time(tempDate);
            }


            if (oldList.get(i).getSignal7()[oldList.get(i).getSignal7().length - 1]
                    != newList.get(i).getSignal7()[0]) {

                System.out.println("Nel robot " + oldList.get(i).getId() + " il segnale 7 " + oldList.get(i).getSignal7()[oldList.get(i).getSignal7().length - 1]+
                        " è cambiato in " +newList.get(i).getSignal7()[0]);

                int size = oldList.get(i).getSignal7().length;

                int tempArray[] = new int[size+1];
                Date tempDate[] = new Date[size+1];

                for (int k = 0; k < oldList.get(i).getSignal7().length; k++) {
                    tempArray[k] = oldList.get(i).getSignal7()[k];
                }

                for (int k = 0; k < oldList.get(i).getSignal7Time().length; k++) {
                    tempDate[k] = oldList.get(i).getSignal7Time()[k];
                }

                tempArray[tempArray.length-1] = newList.get(i).getSignal7()[0];
                tempDate[tempDate.length-1] = newList.get(i).getSignal7Time()[0];

                System.out.println("I numeri da stampare sono " + tempArray[tempArray.length-2] + ", " + tempArray[tempArray.length-1]);

                oldList.get(i).setSignal7(tempArray);
                oldList.get(i).setSignal7Time(tempDate);

            }
        }

        myCollection.drop();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DBManager.saveDataToDB(db, oldList);

    }
}
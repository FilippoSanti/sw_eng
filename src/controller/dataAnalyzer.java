package controller;

import model.InefficiencyRate;
import model.InefficiencyRateByCluster;
import model.Robot;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class dataAnalyzer {

    private static boolean isMinutesAgo(Date date, long minutes) {

        Instant instant = Instant.ofEpochMilli(date.getTime());
        Instant time = Instant.now().minus(Duration.ofMinutes(minutes));


        return instant.isAfter(time);
    }

    public static long CalculateDownTime(Robot robot, long timeLeft, long relativeDownTime, boolean LOTrovato) {


        // Ora corrente
        Date d1 = new Date(System.currentTimeMillis());
        Date signalbiggestfaster = d1;

        boolean trovato1 = false;
        boolean trovato2 = false;
        boolean trovato3 = false;
        boolean trovato4 = false;
        boolean trovato5 = false;
        boolean trovato6 = false;
        boolean trovato7 = false;

        int pos1 = 0;
        int pos2 = 0;
        int pos3 = 0;
        int pos4 = 0;
        int pos5 = 0;
        int pos6 = 0;
        int pos7 = 0;
        int segnale = 0;


        // Signal 1
        for (int y = 0; y < 5; y++) {

            for (int j = 0; j < robot.getSignal1Time().length && !trovato1; j++) {

                if (isMinutesAgo(robot.getSignal1Time()[j], timeLeft)) {
                    signalbiggestfaster = robot.getSignal1Time()[j];
                    segnale = 1;
                    trovato1 = true;
                    pos1 = j;
                }

            }


            for (int j = 0; j < robot.getSignal2Time().length && !trovato2; j++) {
                if (isMinutesAgo(robot.getSignal2Time()[j], timeLeft)) {
                    if (robot.getSignal2Time()[j].before(signalbiggestfaster)) {
                        signalbiggestfaster = robot.getSignal2Time()[j];
                        segnale = 2;
                        trovato2 = true;
                        pos2 = j;
                    }
                }
            }

            for (int j = 0; j < robot.getSignal3Time().length && !trovato3; j++) {
                if (isMinutesAgo(robot.getSignal3Time()[j], timeLeft)) {
                    if (robot.getSignal3Time()[j].before(signalbiggestfaster)) {
                        signalbiggestfaster = robot.getSignal3Time()[j];
                        segnale = 3;
                        trovato3 = true;
                        pos3 = j;
                    }
                }
            }

            for (int j = 0; j < robot.getSignal4Time().length && !trovato4; j++) {
                if (isMinutesAgo(robot.getSignal4Time()[j], timeLeft)) {
                    if (robot.getSignal4Time()[j].before(signalbiggestfaster)) {
                        signalbiggestfaster = robot.getSignal4Time()[j];
                        segnale = 4;
                        trovato4 = true;
                        pos4 = j;
                    }
                }
            }

            for (int j = 0; j < robot.getSignal5Time().length && !trovato1; j++) {
                if (isMinutesAgo(robot.getSignal5Time()[j], timeLeft)) {
                    if (robot.getSignal5Time()[j].before(signalbiggestfaster)) {
                        signalbiggestfaster = robot.getSignal5Time()[j];
                        segnale = 5;
                        trovato5 = true;
                        pos5 = j;
                    }
                }
            }

            for (int j = 0; j < robot.getSignal6Time().length && !trovato6; j++) {
                if (isMinutesAgo(robot.getSignal6Time()[j], timeLeft)) {
                    if (robot.getSignal6Time()[j].before(signalbiggestfaster)) {
                        signalbiggestfaster = robot.getSignal6Time()[j];
                        segnale = 6;
                        trovato6 = true;
                        pos6 = j;
                    }
                }
            }

            for (int j = 0; j < robot.getSignal7Time().length && !trovato1; j++) {
                if (isMinutesAgo(robot.getSignal7Time()[j], timeLeft)) {
                    if (robot.getSignal7Time()[j].before(signalbiggestfaster)) {
                        signalbiggestfaster = robot.getSignal7Time()[j];
                        segnale = 7;
                        trovato7 = true;
                        pos7 = j;
                    }
                }
            }


            switch (segnale) {

                case 1: {

                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal1()[pos1] != 1) {
                        if (pos1 == robot.getSignal1Time().length - 1) {

                            long diff = Math.abs(d1.getTime() - robot.getSignal1Time()[pos1].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        }

                        // Se il segnale torna uno, bisogna vedere quando
                        else {
                            LOTrovato = true;
                            Date d3 = robot.getSignal1Time()[pos1 + 1];
                            long diff = Math.abs(d3.getTime() - robot.getSignal1Time()[pos1].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            long diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);
                        }
                    } else if (pos1 != 0) {
                        Date d3 = robot.getSignal1Time()[pos1];
                        long diff = Math.abs(d1.getTime() - d3.getTime());
                        timeLeft = diff / (60 * 1000);
                        relativeDownTime = relativeDownTime + (60 - timeLeft);

                        return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);


                    }

                }


                signalbiggestfaster = d1;
                break;

                case 2: {

                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal2()[pos2] != 1) {
                        if (pos2 == robot.getSignal2Time().length - 1) {

                            long diff = Math.abs(d1.getTime() - robot.getSignal2Time()[pos2].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        }

                        // Se il segnale torna uno, bisogna vedere quando
                        else {
                            LOTrovato = true;
                            Date d3 = robot.getSignal2Time()[pos2 + 1];
                            long diff = Math.abs(d3.getTime() - robot.getSignal2Time()[pos2].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            long diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);
                        }
                    } else if (pos2 != 0) {
                        Date d3 = robot.getSignal2Time()[pos2];
                        long diff = Math.abs(d1.getTime() - d3.getTime());
                        timeLeft = diff / (60 * 1000);
                        relativeDownTime = relativeDownTime + (60 - timeLeft);

                        return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);


                    }
                }

                signalbiggestfaster = d1;


                break;

                case 3: {

                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal3()[pos3] != 1) {
                        if (pos3 == robot.getSignal3Time().length - 1) {

                            long diff = Math.abs(d1.getTime() - robot.getSignal3Time()[pos3].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        }

                        // Se il segnale torna uno, bisogna vedere quando
                        else {
                            LOTrovato = true;
                            Date d3 = robot.getSignal3Time()[pos3 + 1];
                            long diff = Math.abs(d3.getTime() - robot.getSignal3Time()[pos3].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            long diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);
                        }

                    } else if (pos3 != 0) {
                        Date d3 = robot.getSignal3Time()[pos3];
                        long diff = Math.abs(d1.getTime() - d3.getTime());
                        timeLeft = diff / (60 * 1000);
                        relativeDownTime = relativeDownTime + (60 - timeLeft);

                        return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);


                    }
                }

                signalbiggestfaster = d1;
                break;

                case 4: {

                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal4()[pos4] != 1) {
                        if (pos4 == robot.getSignal4Time().length - 1) {

                            long diff = Math.abs(d1.getTime() - robot.getSignal4Time()[pos4].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        }

                        // Se il segnale torna uno, bisogna vedere quando
                        else {
                            LOTrovato = true;
                            Date d3 = robot.getSignal4Time()[pos4 + 1];
                            long diff = Math.abs(d3.getTime() - robot.getSignal4Time()[pos4].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            long diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);
                        }
                    } else if (pos4 != 0) {
                        Date d3 = robot.getSignal4Time()[pos4];
                        long diff = Math.abs(d1.getTime() - d3.getTime());
                        timeLeft = diff / (60 * 1000);
                        relativeDownTime = relativeDownTime + (60 - timeLeft);

                        return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);


                    }
                }


                signalbiggestfaster = d1;
                break;

                case 5: {

                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal5()[pos5] != 1) {
                        if (pos5 == robot.getSignal5Time().length - 1) {

                            long diff = Math.abs(d1.getTime() - robot.getSignal5Time()[pos5].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        }

                        // Se il segnale torna uno, bisogna vedere quando
                        else {
                            LOTrovato = true;
                            Date d3 = robot.getSignal5Time()[pos5 + 1];
                            long diff = Math.abs(d3.getTime() - robot.getSignal5Time()[pos5].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            long diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);
                        }
                    } else if (pos5 != 0) {
                        Date d3 = robot.getSignal5Time()[pos5];
                        long diff = Math.abs(d1.getTime() - d3.getTime());
                        timeLeft = diff / (60 * 1000);
                        relativeDownTime = relativeDownTime + (60 - timeLeft);

                        return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);


                    }
                }


                signalbiggestfaster = d1;

                break;

                case 6: {


                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal6()[pos6] != 1) {
                        if (pos6 == robot.getSignal6Time().length - 1) {

                            long diff = Math.abs(d1.getTime() - robot.getSignal6Time()[pos6].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        }

                        // Se il segnale torna uno, bisogna vedere quando
                        else {
                            LOTrovato = true;
                            Date d3 = robot.getSignal6Time()[pos6 + 1];
                            long diff = Math.abs(d3.getTime() - robot.getSignal6Time()[pos6].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            long diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);
                        }
                    } else if (pos6 != 0) {
                        Date d3 = robot.getSignal6Time()[pos6];
                        long diff = Math.abs(d1.getTime() - d3.getTime());
                        timeLeft = diff / (60 * 1000);
                        relativeDownTime = relativeDownTime + (60 - timeLeft);

                        return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);


                    }
                }

                signalbiggestfaster = d1;

                break;


                case 7:

                {

                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal7()[pos7] != 1) {
                        if (pos7 == robot.getSignal7Time().length - 1) {

                            long diff = Math.abs(d1.getTime() - robot.getSignal7Time()[pos7].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        }

                        // Se il segnale torna uno, bisogna vedere quando
                        else {
                            LOTrovato = true;
                            Date d3 = robot.getSignal7Time()[pos7 + 1];
                            long diff = Math.abs(d3.getTime() - robot.getSignal7Time()[pos7].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            long diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);
                        }

                    } else if (pos7 != 0) {
                        Date d3 = robot.getSignal7Time()[pos7];
                        long diff = Math.abs(d1.getTime() - d3.getTime());
                        timeLeft = diff / (60 * 1000);
                        relativeDownTime = relativeDownTime + (60 - timeLeft);

                        return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);


                    }
                }
                signalbiggestfaster = d1;

                break;
            }
        }

        //CONTROLLO FINALE.



        return relativeDownTime;
    }


    public static InefficiencyRate inefficiencyRateOfARobot(Robot robot) {
        long downtime= CalculateDownTime(robot, 60, 0, false);
        double iR =  (downtime* 100.00)/60.00;
        InefficiencyRate temp = new InefficiencyRate(robot.getId(), robot.getCluster(), iR, robot.getArea());
        return temp;
    }

    public static ArrayList<InefficiencyRate> inefficiencyRateAllRobot (ArrayList<Robot> oldList)
    {
        ArrayList<InefficiencyRate> temp = new ArrayList<InefficiencyRate>();
        for (int i = 0; i < oldList.size(); i++){
            temp.add(inefficiencyRateOfARobot(oldList.get(i)));

        }

        return temp;
    }

    public static ArrayList<InefficiencyRateByCluster> inefficiencyRateByCluster(ArrayList<InefficiencyRate> tuttecose) throws IOException, ClassNotFoundException {

        List<Integer> lista = simulation.inputSimulation.getDataFromList();

        ArrayList<InefficiencyRateByCluster> temp = new ArrayList<InefficiencyRateByCluster>();
        int robotId = 0;

        for (int i = 0; i < lista.size(); i++) {
            double ineffRate = 0;
            InefficiencyRateByCluster IRBC = new InefficiencyRateByCluster(0,0);
            for (int y = 0; y < lista.get(i); y++){
                ineffRate = ineffRate + tuttecose.get(robotId).getInefficiencyRate();
                robotId++;

            }
            IRBC.setCluster(i);
            IRBC.setInefficiencyRate(ineffRate/lista.get(i));
            temp.add(IRBC);

        }

        return temp;
    }


}


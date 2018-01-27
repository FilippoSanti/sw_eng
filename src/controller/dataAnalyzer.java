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

    public static double CalculateDownTime(Robot robot, double timeLeft, double relativeDownTime, int ultimo, int posizione) {

        // Ora corrente
        Date d1 = new Date(System.currentTimeMillis());
        Date signalbiggestfaster = d1;

        boolean trovato, trovato1, trovato2, trovato3, trovato4, trovato5, trovato6, trovato7;
        trovato = trovato1 = trovato2 = trovato3 = trovato4 = trovato5 = trovato6 = trovato7 = false;

        int pos1, pos2, pos3, pos4, pos5, pos6, pos7;
        pos1 = pos2 = pos3 = pos4 = pos5 = pos6 = pos7 = 0;

        int segnale = 0;

        for (int y = 0; y < 50 && trovato == false; y++) {
            for (int j = 0; j < robot.getSignal1Time().length && !trovato1; j++) {
                if (isMinutesAgo(robot.getSignal1Time()[j], (long) timeLeft)) {
                    if (!(robot.getSignal1()[j] == 1 && j == 0) && !(ultimo == 1 && posizione == j)) {
                        signalbiggestfaster = robot.getSignal1Time()[j];
                        segnale = 1;
                        trovato = true;
                        trovato1 = true;
                        pos1 = j;
                    }
                }
            }

            for (int j = 0; j < robot.getSignal2Time().length && !trovato2; j++) {
                if (isMinutesAgo(robot.getSignal2Time()[j], (long) timeLeft)) {
                    if (robot.getSignal2Time()[j].before(signalbiggestfaster)) {

                        if (!(robot.getSignal2()[j] == 1 && j == 0) && !(ultimo == 2 && posizione == j)) {

                            signalbiggestfaster = robot.getSignal2Time()[j];
                            segnale = 2;
                            trovato = true;
                            trovato2 = true;
                            pos2 = j;
                        }
                    }
                }
            }

            for (int j = 0; j < robot.getSignal3Time().length && !trovato3; j++) {
                if (isMinutesAgo(robot.getSignal3Time()[j], (long) timeLeft)) {
                    if (!(robot.getSignal3()[j] == 1 && j == 0) && !(ultimo == 3 && posizione == j)) {
                        if (robot.getSignal3Time()[j].before(signalbiggestfaster)) {
                            signalbiggestfaster = robot.getSignal3Time()[j];
                            segnale = 3;
                            trovato = true;
                            trovato3 = true;
                            pos3 = j;
                        }
                    }
                }
            }

            for (int j = 0; j < robot.getSignal4Time().length && !trovato4; j++) {
                if (isMinutesAgo(robot.getSignal4Time()[j], (long) timeLeft)) {
                    if (robot.getSignal4Time()[j].before(signalbiggestfaster)) {
                        if (!(robot.getSignal4()[j] == 1 && j == 0) && !(ultimo == 4 && posizione == j)) {
                            signalbiggestfaster = robot.getSignal4Time()[j];
                            segnale = 4;
                            trovato = true;
                            trovato4 = true;
                            pos4 = j;
                        }
                    }
                }
            }

            for (int j = 0; j < robot.getSignal5Time().length && !trovato5; j++) {
                if (isMinutesAgo(robot.getSignal5Time()[j], (long) timeLeft)) {
                    if (robot.getSignal5Time()[j].before(signalbiggestfaster)) {
                        if (!(robot.getSignal5()[j] == 1 && j == 0) && !(ultimo == 5 && posizione == j)) {
                            signalbiggestfaster = robot.getSignal5Time()[j];
                            segnale = 5;
                            trovato = true;
                            trovato5 = true;
                            pos5 = j;
                        }
                    }
                }
            }

            for (int j = 0; j < robot.getSignal6Time().length && !trovato6; j++) {
                if (isMinutesAgo(robot.getSignal6Time()[j], (long) timeLeft)) {
                    if (robot.getSignal6Time()[j].before(signalbiggestfaster)) {
                        if (!(robot.getSignal6()[j] == 1 && j == 0) && !(ultimo == 6 && posizione == j)) {
                            signalbiggestfaster = robot.getSignal6Time()[j];
                            segnale = 6;
                            trovato = true;
                            trovato6 = true;
                            pos6 = j;
                        }
                    }
                }
            }

            for (int j = 0; j < robot.getSignal7Time().length && !trovato7; j++) {
                if (isMinutesAgo(robot.getSignal7Time()[j], (long) timeLeft)) {
                    if (robot.getSignal7Time()[j].before(signalbiggestfaster)) {
                        if (!(robot.getSignal7()[j] == 1 && j == 0) && !(ultimo == 7 && posizione == j)) {
                            signalbiggestfaster = robot.getSignal7Time()[j];
                            segnale = 7;
                            trovato = true;
                            trovato7 = true;
                            pos7 = j;
                        }
                    }
                }
            }

            switch (segnale) {
                case 1: {

                    if (robot.getSignal1()[pos1] != 1) {
                        if (pos1 == robot.getSignal1Time().length - 1) {
                            double diff = Math.abs(d1.getTime() - robot.getSignal1Time()[pos1].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        } else {
                            Date d3 = robot.getSignal1Time()[pos1 + 1];
                            double diff = Math.abs(d3.getTime() - robot.getSignal1Time()[pos1].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            ultimo = 1;
                            posizione = pos1 + 1;
                            double diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        }
                    }
                    if (pos1 != 0) {

                        if (isMinutesAgo(robot.getSignal1Time()[pos1 - 1], (long) timeLeft)) {

                            Date d3 = robot.getSignal1Time()[pos1 - 1];
                            double diff = Math.abs(robot.getSignal1Time()[pos1].getTime() - d3.getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            ultimo = 1;
                            posizione = pos1;
                            double diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);

                        } else {
                            Date d3 = robot.getSignal1Time()[pos1];
                            double diff = Math.abs(d1.getTime() - d3.getTime());
                            diff = timeLeft - (diff / (60 * 1000));
                            relativeDownTime = relativeDownTime + diff;

                            ultimo = 1;
                            posizione = pos1;
                            timeLeft = diff;
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        }

                    } else {
                        Date d3 = robot.getSignal1Time()[pos1];
                        double diff = Math.abs(d1.getTime() - d3.getTime());
                        timeLeft = diff / (60 * 1000);
                        relativeDownTime = relativeDownTime + (60 - (long) timeLeft);

                        ultimo = 1;
                        posizione = pos1;
                        return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                    }


                }

                case 2: {

                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal2()[pos2] != 1) {
                        if (pos2 == robot.getSignal2Time().length - 1) {
                            double diff = Math.abs(d1.getTime() - robot.getSignal2Time()[pos2].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        } else {
                            Date d3 = robot.getSignal2Time()[pos2 + 1];
                            double diff = Math.abs(d3.getTime() - robot.getSignal2Time()[pos2].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            double diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);
                            ultimo = 2;
                            posizione = pos2 + 1;
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        }
                    }
                    if (pos2 != 0) {

                        if (isMinutesAgo(robot.getSignal2Time()[pos2 - 1], (long) timeLeft)) {

                            Date d3 = robot.getSignal2Time()[pos2 - 1];
                            double diff = Math.abs(robot.getSignal2Time()[pos2].getTime() - d3.getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            ultimo = 2;
                            posizione = pos2;
                            double diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        } else {
                            Date d3 = robot.getSignal2Time()[pos2];
                            double diff = Math.abs(d1.getTime() - d3.getTime());
                            diff = timeLeft - (diff / (60 * 1000));
                            relativeDownTime = relativeDownTime + diff;

                            ultimo = 2;
                            posizione = pos2;
                            timeLeft = diff;
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        }
                    } else {
                        Date d3 = robot.getSignal2Time()[pos2];
                        double diff = Math.abs(d1.getTime() - d3.getTime());
                        timeLeft = diff / (60 * 1000);
                        relativeDownTime = relativeDownTime + (60 - (long) timeLeft);

                        ultimo = 2;
                        posizione = pos2;
                        return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                    }


                }

                case 3: {

                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal3()[pos3] != 1) {
                        if (pos3 == robot.getSignal3Time().length - 1) {
                            double diff = Math.abs(d1.getTime() - robot.getSignal3Time()[pos3].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        } else {
                            Date d3 = robot.getSignal3Time()[pos3 + 1];
                            double diff = Math.abs(d3.getTime() - robot.getSignal3Time()[pos3].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            double diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);

                            ultimo = 3;
                            posizione = pos3 + 1;
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        }

                    }
                    if (pos3 != 0) {

                        if (isMinutesAgo(robot.getSignal3Time()[pos3 - 1], (long) timeLeft)) {

                            Date d3 = robot.getSignal3Time()[pos3 - 1];
                            double diff = Math.abs(robot.getSignal3Time()[pos3].getTime() - d3.getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            ultimo = 3;
                            posizione = pos3;
                            double diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        } else {
                            Date d3 = robot.getSignal3Time()[pos3];
                            double diff = Math.abs(d1.getTime() - d3.getTime());
                            diff = timeLeft - (diff / (60 * 1000));
                            relativeDownTime = relativeDownTime + diff;

                            ultimo = 3;
                            posizione = pos3;
                            timeLeft = diff;
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        }

                    } else {
                        Date d3 = robot.getSignal3Time()[pos3];
                        double diff = Math.abs(d1.getTime() - d3.getTime());
                        timeLeft = diff / (60 * 1000);
                        relativeDownTime = relativeDownTime + (60 - (long) timeLeft);

                        ultimo = 3;
                        posizione = pos3;
                        return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);

                    }


                }

                case 4: {
                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal4()[pos4] != 1) {
                        if (pos4 == robot.getSignal4Time().length - 1) {
                            double diff = Math.abs(d1.getTime() - robot.getSignal4Time()[pos4].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        } else {
                            Date d3 = robot.getSignal4Time()[pos4 + 1];
                            double diff = Math.abs(d3.getTime() - robot.getSignal4Time()[pos4].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            double diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);
                            ultimo = 4;
                            posizione = pos4 + 1;
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        }
                    }
                    if (pos4 != 0) {

                        if (isMinutesAgo(robot.getSignal4Time()[pos4 - 1], (long) timeLeft)) {

                            Date d3 = robot.getSignal4Time()[pos4 - 1];
                            double diff = Math.abs(robot.getSignal4Time()[pos4].getTime() - d3.getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            ultimo = 4;
                            posizione = pos4;
                            double diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        } else {
                            Date d3 = robot.getSignal4Time()[pos4];
                            double diff = Math.abs(d1.getTime() - d3.getTime());
                            diff = timeLeft - (diff / (60 * 1000));
                            relativeDownTime = relativeDownTime + diff;

                            ultimo = 4;
                            posizione = pos4;
                            timeLeft = diff;
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        }
                    } else {
                        Date d3 = robot.getSignal4Time()[pos4];
                        double diff = Math.abs(d1.getTime() - d3.getTime());
                        timeLeft = diff / (60 * 1000);
                        relativeDownTime = relativeDownTime + (60 - (long) timeLeft);

                        ultimo = 4;
                        posizione = pos4;
                        return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);

                    }


                }

                case 5: {
                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal5()[pos5] != 1) {
                        if (pos5 == robot.getSignal5Time().length - 1) {
                            double diff = Math.abs(d1.getTime() - robot.getSignal5Time()[pos5].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        } else {
                            Date d3 = robot.getSignal5Time()[pos5 + 1];
                            double diff = Math.abs(d3.getTime() - robot.getSignal5Time()[pos5].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            double diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);

                            ultimo = 5;
                            posizione = pos5 + 1;
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        }
                    }
                    if (pos5 != 0) {

                        if (isMinutesAgo(robot.getSignal5Time()[pos5 - 1], (long) timeLeft)) {

                            Date d3 = robot.getSignal5Time()[pos5 - 1];
                            double diff = Math.abs(robot.getSignal5Time()[pos5].getTime() - d3.getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            ultimo = 5;
                            posizione = pos5;
                            double diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);

                        } else {
                            Date d3 = robot.getSignal5Time()[pos5];
                            double diff = Math.abs(d1.getTime() - d3.getTime());
                            diff = timeLeft - (diff / (60 * 1000));
                            relativeDownTime = relativeDownTime + diff;

                            ultimo = 5;
                            posizione = pos5;
                            timeLeft = diff;
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        }

                    } else {
                        Date d3 = robot.getSignal5Time()[pos5];
                        double diff = Math.abs(d1.getTime() - d3.getTime());
                        timeLeft = diff / (60 * 1000);
                        relativeDownTime = relativeDownTime + (60 - (long) timeLeft);

                        ultimo = 5;
                        posizione = pos5;
                        return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);

                    }

                }

                case 6: {
                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal6()[pos6] != 1) {
                        if (pos6 == robot.getSignal6Time().length - 1) {
                            double diff = Math.abs(d1.getTime() - robot.getSignal6Time()[pos6].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        } else {
                            Date d3 = robot.getSignal6Time()[pos6 + 1];
                            double diff = Math.abs(d3.getTime() - robot.getSignal6Time()[pos6].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            double diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);

                            ultimo = 6;
                            posizione = pos6 + 1;
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        }
                    }
                    if (pos6 != 0) {

                        if (isMinutesAgo(robot.getSignal6Time()[pos6 - 1], (long) timeLeft)) {

                            Date d3 = robot.getSignal6Time()[pos6 - 1];
                            double diff = Math.abs(robot.getSignal6Time()[pos6].getTime() - d3.getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            ultimo = 6;
                            posizione = pos6;
                            double diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        } else {
                            Date d3 = robot.getSignal6Time()[pos6];
                            double diff = Math.abs(d1.getTime() - d3.getTime());
                            diff = timeLeft - (diff / (60 * 1000));
                            relativeDownTime = relativeDownTime + diff;

                            ultimo = 6;
                            posizione = pos6;
                            timeLeft = diff;
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        }

                    } else {


                        Date d3 = robot.getSignal6Time()[pos6];
                        double diff = Math.abs(d1.getTime() - d3.getTime());
                        timeLeft = diff / (60 * 1000);
                        relativeDownTime = relativeDownTime + (60 - (long) timeLeft);

                        ultimo = 6;
                        posizione = pos6;
                        return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                    }


                }

                case 7: {
                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal7()[pos7] != 1) {
                        if (pos7 == robot.getSignal7Time().length - 1) {

                            double diff = Math.abs(d1.getTime() - robot.getSignal7Time()[pos7].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        }

                        // Se il segnale torna uno, bisogna vedere quando
                        else {
                            Date d3 = robot.getSignal7Time()[pos7 + 1];
                            double diff = Math.abs(d3.getTime() - robot.getSignal7Time()[pos7].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            double diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);

                            ultimo = 7;
                            posizione = pos7 + 1;
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        }

                    }
                    if (pos7 != 0) {

                        if (isMinutesAgo(robot.getSignal7Time()[pos7 - 1], (long) timeLeft)) {

                            Date d3 = robot.getSignal7Time()[pos7 - 1];
                            double diff = Math.abs(robot.getSignal7Time()[pos7].getTime() - d3.getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            ultimo = 7;
                            posizione = pos7;
                            double diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        } else {
                            Date d3 = robot.getSignal7Time()[pos7];
                            double diff = Math.abs(d1.getTime() - d3.getTime());
                            diff = timeLeft - (diff / (60 * 1000));
                            relativeDownTime = relativeDownTime + diff;

                            ultimo = 7;
                            posizione = pos7;
                            timeLeft = diff;
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                        }

                    } else {


                        Date d3 = robot.getSignal7Time()[pos7];
                        double diff = Math.abs(d1.getTime() - d3.getTime());
                        timeLeft = diff / (60 * 1000);
                        relativeDownTime = relativeDownTime + (60 - (long) timeLeft);

                        ultimo = 7;
                        posizione = pos7;
                        return CalculateDownTime(robot, timeLeft, relativeDownTime, ultimo, posizione);
                    }


                }
            }
        }
        return relativeDownTime;
    }

    public static InefficiencyRate inefficiencyRateOfARobot(Robot robot) {
        double downtime = CalculateDownTime(robot, 60, 0, 0, 0);
        double iR = (downtime * 100.00) / 60.00;
        InefficiencyRate temp = new InefficiencyRate(robot.getId(), robot.getCluster(), iR);
        return temp;
    }

    public static ArrayList<InefficiencyRate> inefficiencyRateAllRobot(ArrayList<Robot> oldList) {
        ArrayList<InefficiencyRate> temp = new ArrayList<InefficiencyRate>();
        for (int i = 0; i < oldList.size(); i++) {
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
            InefficiencyRateByCluster IRBC = new InefficiencyRateByCluster(0, 0);
            for (int y = 0; y < lista.get(i); y++) {
                ineffRate = ineffRate + tuttecose.get(robotId).getInefficiencyRate();
                robotId++;

            }
            IRBC.setCluster(i);
            IRBC.setInefficiencyRate(ineffRate / lista.get(i));
            temp.add(IRBC);

        }
        return temp;
    }

    public static ArrayList<Robot> splitListIntoClusters(ArrayList<Robot> oldList, int clusterList) {

        ArrayList<Robot> tempList = new ArrayList<Robot>();

        for (int i = 0; i < oldList.size(); i++) {
            if (oldList.get(i).getCluster() == clusterList) {
                tempList.add(oldList.get(i));
            }
        }
        return tempList;
    }

    public static ArrayList<Double> calculateIRByArea(ArrayList<InefficiencyRateByCluster> clusterIneff) throws IOException, ClassNotFoundException {

        ArrayList list = new ArrayList<Double>();
        int jCount = 0;
        int sizeOne = 10;

        for (int i = 0; i < 10; i++) {
            double IR = 0;

            for (int j = jCount; j < sizeOne; j++) {
                IR += clusterIneff.get(j).getInefficiencyRate();
                jCount++;
            }
            sizeOne += 10;
            list.add(IR / 10);
        }
        return list;
    }
}



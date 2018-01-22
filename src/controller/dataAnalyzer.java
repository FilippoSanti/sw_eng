package controller;

import com.mongodb.Mongo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Robot;
import org.bson.Document;
import simulation.inputSimulation;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.push;

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
        int segnale = 0;

        // Signal 1
        for (int y = 0; y < robot.getSignal1Time().length && LOTrovato != true; y++) {
            if (isMinutesAgo(robot.getSignal1Time()[y], timeLeft)) {
                signalbiggestfaster = robot.getSignal1Time()[y];
                segnale = 1;
            }

            if (isMinutesAgo(robot.getSignal2Time()[y], timeLeft)) {
                if (robot.getSignal2Time()[y].before(signalbiggestfaster)) {
                    signalbiggestfaster = robot.getSignal2Time()[y];
                    segnale = 2;
                }
            }

            if (isMinutesAgo(robot.getSignal3Time()[y], timeLeft)) {
                if (robot.getSignal3Time()[y].before(signalbiggestfaster)) {
                    signalbiggestfaster = robot.getSignal3Time()[y];
                    segnale = 3;
                }
            }

            if (isMinutesAgo(robot.getSignal4Time()[y], timeLeft)) {
                if (robot.getSignal4Time()[y].before(signalbiggestfaster)) {
                    signalbiggestfaster = robot.getSignal4Time()[y];
                    segnale = 4;
                }
            }

            if (isMinutesAgo(robot.getSignal5Time()[y], timeLeft)) {
                if (robot.getSignal5Time()[y].before(signalbiggestfaster)) {
                    signalbiggestfaster = robot.getSignal5Time()[y];
                    segnale = 5;
                }
            }

            if (isMinutesAgo(robot.getSignal6Time()[y], timeLeft)) {
                if (robot.getSignal6Time()[y].before(signalbiggestfaster)) {
                    signalbiggestfaster = robot.getSignal6Time()[y];
                    segnale = 6;
                }
            }

            if (isMinutesAgo(robot.getSignal7Time()[y], timeLeft)) {
                if (robot.getSignal7Time()[y].before(signalbiggestfaster)) {
                    signalbiggestfaster = robot.getSignal7Time()[y];
                    segnale = 7;
                }
            }


            switch (segnale) {
                case 1:

                    {

                    LOTrovato = true;
                    System.out.println(robot.getSignal1Time()[y]);
                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal1()[y] != 1) {
                        if (y == robot.getSignal1Time().length - 1) {

                            long diff = Math.abs(d1.getTime() - robot.getSignal1Time()[y].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        }

                        // Se il segnale torna uno, bisogna vedere quando
                        else {
                            Date d3 = robot.getSignal1Time()[y + 1];
                            long diff = Math.abs(d3.getTime() - robot.getSignal1Time()[y].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            long diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = timeLeft - diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);
                        }

                    }
                }

                if (LOTrovato == false) {
                    if (robot.getSignal1()[robot.getSignal1Time().length - 1] == 0) {
                        relativeDownTime = 60;
                        return relativeDownTime;
                    }
                }

                break;

                case 2:

                {

                    LOTrovato = true;
                    System.out.println(robot.getSignal2Time()[y]);
                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal1()[y] != 1) {
                        if (y == robot.getSignal2Time().length - 1) {

                            long diff = Math.abs(d1.getTime() - robot.getSignal2Time()[y].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        }

                        // Se il segnale torna uno, bisogna vedere quando
                        else {
                            Date d3 = robot.getSignal2Time()[y + 1];
                            long diff = Math.abs(d3.getTime() - robot.getSignal2Time()[y].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            long diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = timeLeft - diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);
                        }

                    }
                }

                if (LOTrovato == false) {
                    if (robot.getSignal1()[robot.getSignal2Time().length - 1] == 0) {
                        relativeDownTime = 60;
                        return relativeDownTime;
                    }
                }


                    break;


                case 3:

                {

                    LOTrovato = true;
                    System.out.println(robot.getSignal3Time()[y]);
                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal1()[y] != 1) {
                        if (y == robot.getSignal3Time().length - 1) {

                            long diff = Math.abs(d1.getTime() - robot.getSignal3Time()[y].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        }

                        // Se il segnale torna uno, bisogna vedere quando
                        else {
                            Date d3 = robot.getSignal3Time()[y + 1];
                            long diff = Math.abs(d3.getTime() - robot.getSignal3Time()[y].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            long diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = timeLeft - diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);
                        }

                    }
                }

                if (LOTrovato == false) {
                    if (robot.getSignal1()[robot.getSignal3Time().length - 1] == 0) {
                        relativeDownTime = 60;
                        return relativeDownTime;
                    }
                }

                    break;


                case 4:


                {

                    LOTrovato = true;
                    System.out.println(robot.getSignal4Time()[y]);
                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal1()[y] != 1) {
                        if (y == robot.getSignal4Time().length - 1) {

                            long diff = Math.abs(d1.getTime() - robot.getSignal4Time()[y].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        }

                        // Se il segnale torna uno, bisogna vedere quando
                        else {
                            Date d3 = robot.getSignal4Time()[y + 1];
                            long diff = Math.abs(d3.getTime() - robot.getSignal4Time()[y].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            long diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = timeLeft - diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);
                        }

                    }
                }

                if (LOTrovato == false) {
                    if (robot.getSignal1()[robot.getSignal4Time().length - 1] == 0) {
                        relativeDownTime = 60;
                        return relativeDownTime;
                    }
                }


                    break;


                case 5:


                {

                    LOTrovato = true;
                    System.out.println(robot.getSignal5Time()[y]);
                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal5()[y] != 1) {
                        if (y == robot.getSignal5Time().length - 1) {

                            long diff = Math.abs(d1.getTime() - robot.getSignal5Time()[y].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        }

                        // Se il segnale torna uno, bisogna vedere quando
                        else {
                            Date d3 = robot.getSignal5Time()[y + 1];
                            long diff = Math.abs(d3.getTime() - robot.getSignal5Time()[y].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            long diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = timeLeft - diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);
                        }

                    }
                }

                if (LOTrovato == false) {
                    if (robot.getSignal1()[robot.getSignal5Time().length - 1] == 0) {
                        relativeDownTime = 60;
                        return relativeDownTime;
                    }
                }


                    break;


                case 6:


                {

                    LOTrovato = true;
                    System.out.println(robot.getSignal6Time()[y]);
                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal1()[y] != 1) {
                        if (y == robot.getSignal6Time().length - 1) {

                            long diff = Math.abs(d1.getTime() - robot.getSignal6Time()[y].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        }

                        // Se il segnale torna uno, bisogna vedere quando
                        else {
                            Date d3 = robot.getSignal6Time()[y + 1];
                            long diff = Math.abs(d3.getTime() - robot.getSignal6Time()[y].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            long diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = timeLeft - diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);
                        }

                    }
                }

                if (LOTrovato == false) {
                    if (robot.getSignal1()[robot.getSignal6Time().length - 1] == 0) {
                        relativeDownTime = 60;
                        return relativeDownTime;
                    }
                }


                    break;


                case 7:


                {

                    LOTrovato = true;
                    System.out.println(robot.getSignal7Time()[y]);
                    // Controlliamo che il valore sia diverso da uno e resti 0
                    if (robot.getSignal1()[y] != 1) {
                        if (y == robot.getSignal7Time().length - 1) {

                            long diff = Math.abs(d1.getTime() - robot.getSignal7Time()[y].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            return relativeDownTime;
                        }

                        // Se il segnale torna uno, bisogna vedere quando
                        else {
                            Date d3 = robot.getSignal7Time()[y + 1];
                            long diff = Math.abs(d3.getTime() - robot.getSignal7Time()[y].getTime());
                            relativeDownTime = relativeDownTime + diff / (60 * 1000);

                            long diff1 = Math.abs(d1.getTime() - d3.getTime());
                            timeLeft = timeLeft - diff1 / (60 * 1000);
                            return CalculateDownTime(robot, timeLeft, relativeDownTime, LOTrovato);
                        }

                    }
                }

                if (LOTrovato == false) {
                    if (robot.getSignal1()[robot.getSignal7Time().length - 1] == 0) {
                        relativeDownTime = 60;
                        return relativeDownTime;
                    }
                }


                    break;

            }


        }

 //CONTROLLO FINALE.
        if (LOTrovato == false) {
            if (robot.getSignal1()[robot.getSignal1Time().length - 1] == 1) {
                relativeDownTime = 0;
                return relativeDownTime;
            } else {
                relativeDownTime = 60;
                return relativeDownTime;
            }
        }

        return relativeDownTime;
    }

}










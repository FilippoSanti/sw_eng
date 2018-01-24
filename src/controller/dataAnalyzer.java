package controller;

import model.Robot;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class dataAnalyzer {

    private static boolean isMinutesAgo(Date date, long minutes) {

        Instant instant = Instant.ofEpochMilli(date.getTime());
        Instant time = Instant.now().minus(Duration.ofMinutes(minutes));
        System.out.print(instant);
        System.out.println(time);
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

        System.out.println(robot.getSignal3Time()[0]);
        System.out.println(d1);
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

            for (int j = 0; j < robot.getSignal1Time().length && !trovato1; j++) {
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

                case 1:
                {
                    System.out.println(robot.getSignal1Time()[pos1]);
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
                    }
                }


                signalbiggestfaster = d1;
                break;

                case 2:
                {
                    System.out.println(robot.getSignal2Time()[pos2]);
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
                    }
                }

                signalbiggestfaster = d1;


                break;

                case 3:
                {
                    System.out.println(robot.getSignal3Time()[pos3]);
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

                    }
                }

                signalbiggestfaster = d1;
                break;

                case 4:
                {
                    System.out.println(robot.getSignal4Time()[pos4]);
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
                    }
                }


                signalbiggestfaster = d1;
                break;

                case 5:
                {
                    System.out.println(robot.getSignal5Time()[pos5]);
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
                    }
                }


                signalbiggestfaster = d1;

                break;

                case 6:
                {

                    System.out.println(robot.getSignal6Time()[pos6]);
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
                    }
                }

                signalbiggestfaster = d1;

                break;


                case 7:

                {
                    System.out.println(robot.getSignal7Time()[pos7]);
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

                    }
                }
                signalbiggestfaster = d1;

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
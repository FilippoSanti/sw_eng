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


        // Signal 1
        for (int y = 0; y < robot.getSignal1Time().length && LOTrovato != true; y++) {
            if (isMinutesAgo(robot.getSignal1Time()[y], timeLeft)) {

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

        }


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










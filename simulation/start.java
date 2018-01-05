package simulation;

import java.util.Timer;
import java.util.TimerTask;


public class start {


    public static void main(String[] args) {

        // Schedule the timer to execute the simulator every 'x' ms
        Timer timer = new Timer();
        timer.schedule(new inputSimulation(), 0, 100000);
    }
}
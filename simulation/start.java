package simulation;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mikesh on 16/12/2017.
 */
public class start {


    public static void main(String[] args) {

        // And From your main() method or any other method
        Timer timer = new Timer();
        timer.schedule(new inputSimulation(), 0, 10000);
    }
}

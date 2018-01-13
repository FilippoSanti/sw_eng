package model;

import java.util.Date;
import java.util.List;

public class Signals {

        private  List<int[]> signalValues;
        private  List<Date[]> timestampValues;

        public Signals(List<int[]> signalValues,  List<Date[]> timestampValues)
        {
            this.signalValues = signalValues;
            this.timestampValues = timestampValues;
        }

    public  List<int[]> getSignalValues() {
        return this.signalValues;
    }

    public  List<Date[]> getDateValues() {
        return this.timestampValues;
    }

}
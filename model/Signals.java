package model;

import java.util.Date;

public class Signals {

        private int[]  signalValues;
        private Date[] timestampValues;

        public Signals(int[] signalValues, Date[] timestampValues)
        {
            this.signalValues = signalValues;
            this.timestampValues = timestampValues;
        }

    public int[] getSignalValues() {
        return this.signalValues;
    }

    public Date[] getDateValues() {
        return this.timestampValues;
    }

}

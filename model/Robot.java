package model;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

public class Robot implements Serializable {

    private int id;
    private int cluster;
    private int signal1;
    private int signal2;
    private int signal3;
    private int signal4;
    private int signal5;
    private int signal6;
    private int signal7;
    private Timestamp tempo;

    public Robot(int id, int c, int s1, int s2, int s3, int s4, int s5, int s6, int s7, Timestamp t)
    {
        this.id      = id;
        this.cluster = c;
        this.signal1 = s1;
        this.signal2 = s2;
        this.signal3 = s3;
        this.signal4 = s4;
        this.signal5 = s5;
        this.signal6 = s6;
        this.signal7 = s7;
        this.tempo = t;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSignal1(int signal1) {
        this.signal1 = signal1;
    }

    public void setSignal2(int signal2) {
        this.signal2 = signal2;
    }

    public void setSignal3(int signal3) {
        this.signal3 = signal3;
    }

    public void setSignal4(int signal4) {
        this.signal4 = signal4;
    }

    public void setSignal5(int signal5) {
        this.signal5 = signal5;
    }

    public void setSignal6(int signal6) {
        this.signal6 = signal6;
    }

    public void setSignal7(int signal7) {
        this.signal7 = signal7;
    }

    public void setTempo(Timestamp tempo) { this.tempo = tempo; }

    public int getCluster() {
        return cluster;
    }

    public int getId() {
        return id;
    }

    public int getSignal1() {
        return signal1;
    }
    public int getSignal2() {
        return signal2;
    }

    public int getSignal3()
    {
        return signal3;
    }

    public int getSignal4() {
        return signal4;
    }

    public int getSignal5() {
        return signal5;
    }

    public int getSignal6() {
        return signal6;
    }

    public int getSignal7() {
        return signal7;
    }

    public Timestamp getTempo()
    {
        return tempo;
    }
}

package model;

import java.io.Serializable;
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
    private Timestamp signal1Time;
    private Timestamp signal2Time;
    private Timestamp signal3Time;
    private Timestamp signal4Time;
    private Timestamp signal5Time;
    private Timestamp signal6Time;
    private Timestamp signal7Time;

    public Robot(int id, int c, int s1, int s2, int s3, int s4, int s5, int s6, int s7, Timestamp s1t, Timestamp s2t,
                 Timestamp s3t, Timestamp s4t, Timestamp s5t, Timestamp s6t, Timestamp s7t) {
        this.id = id;
        this.cluster = c;
        this.signal1 = s1;
        this.signal2 = s2;
        this.signal3 = s3;
        this.signal4 = s4;
        this.signal5 = s5;
        this.signal6 = s6;
        this.signal7 = s7;
        this.signal1Time = s1t;
        this.signal2Time = s2t;
        this.signal3Time = s3t;
        this.signal4Time = s4t;
        this.signal5Time = s5t;
        this.signal6Time = s6t;
        this.signal7Time = s7t;
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSignal1() {
        return signal1;
    }

    public void setSignal1(int signal1) {
        this.signal1 = signal1;
    }

    public int getSignal2() {
        return signal2;
    }

    public void setSignal2(int signal2) {
        this.signal2 = signal2;
    }

    public int getSignal3() {
        return signal3;
    }

    public void setSignal3(int signal3) {
        this.signal3 = signal3;
    }

    public int getSignal4() {
        return signal4;
    }

    public void setSignal4(int signal4) {
        this.signal4 = signal4;
    }

    public int getSignal5() {
        return signal5;
    }

    public void setSignal5(int signal5) {
        this.signal5 = signal5;
    }

    public int getSignal6() {
        return signal6;
    }

    public void setSignal6(int signal6) {
        this.signal6 = signal6;
    }

    public int getSignal7() {
        return signal7;
    }

    public void setSignal7(int signal7) {
        this.signal7 = signal7;
    }

    public Timestamp getSignal1Time() {
        return signal1Time;
    }

    public void setSignal1Time(Timestamp signal1Time) {
        this.signal1Time = signal1Time;
    }

    public Timestamp getSignal2Time() {
        return signal2Time;
    }

    public void setSignal2Time(Timestamp signal2Time) {
        this.signal2Time = signal2Time;
    }

    public Timestamp getSignal3Time() {
        return signal3Time;
    }

    public void setSignal3Time(Timestamp signal3Time) {
        this.signal3Time = signal3Time;
    }

    public Timestamp getSignal4Time() {
        return signal4Time;
    }

    public void setSignal4Time(Timestamp signal4Time) {
        this.signal4Time = signal4Time;
    }

    public Timestamp getSignal5Time() {
        return signal5Time;
    }

    public void setSignal5Time(Timestamp signal5Time) {
        this.signal5Time = signal5Time;
    }

    public Timestamp getSignal6Time() {
        return signal6Time;
    }

    public void setSignal6Time(Timestamp signal6Time) {
        this.signal6Time = signal6Time;
    }

    public Timestamp getSignal7Time() {
        return signal7Time;
    }

    public void setSignal7Time(Timestamp signal7Time) {
        this.signal7Time = signal7Time;
    }
}
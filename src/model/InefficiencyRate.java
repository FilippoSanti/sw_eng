package model;

public class InefficiencyRate {
    private int id;
    private int cluster;
    private double inefficiencyRate;

    public InefficiencyRate(int id, int cluster, double iR) {
        this.id = id;
        this.cluster = cluster;
        this.inefficiencyRate = iR;
    }

    public int getCluster() {
        return cluster;
    }

    public int getId() {
        return id;
    }

    public double getInefficiencyRate() {
        return inefficiencyRate;
    }


    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInefficiencyRate(long inefficiencyRate) {
        this.inefficiencyRate = inefficiencyRate;
    }
}

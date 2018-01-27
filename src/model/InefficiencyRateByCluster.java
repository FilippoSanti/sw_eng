package model;

public class InefficiencyRateByCluster {
    private int cluster;
    private double inefficiencyRate;

    public InefficiencyRateByCluster(int cluster, long iR) {
        this.cluster = cluster;
        this.inefficiencyRate = iR;
    }

    public double getInefficiencyRate() {
        return inefficiencyRate;
    }

    public void setInefficiencyRate(double inefficiencyRate) {
        this.inefficiencyRate = inefficiencyRate;
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }
}
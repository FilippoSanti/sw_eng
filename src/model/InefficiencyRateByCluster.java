package model;

public class InefficiencyRateByCluster {
    private int cluster;
    private double inefficiencyRate;

    public InefficiencyRateByCluster(int cluster, long iR){
        this.cluster = cluster;
        this.inefficiencyRate = iR;
    }

    public void setInefficiencyRate(double inefficiencyRate) {
        this.inefficiencyRate = inefficiencyRate;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public double getInefficiencyRate() {
        return inefficiencyRate;
    }

    public int getCluster() {
        return cluster;
    }
}

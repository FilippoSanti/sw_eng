package model;

public class InefficiencyRateByCluster {
    private int cluster;
    private long inefficiencyRate;

    public InefficiencyRateByCluster(int cluster, long iR){
        this.cluster = cluster;
        this.inefficiencyRate = iR;
    }

    public void setInefficiencyRate(long inefficiencyRate) {
        this.inefficiencyRate = inefficiencyRate;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public long getInefficiencyRate() {
        return inefficiencyRate;
    }

    public int getCluster() {
        return cluster;
    }
}

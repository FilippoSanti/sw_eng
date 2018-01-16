package view.robot;

import javafx.beans.property.SimpleStringProperty;

public class Robot {

    private SimpleStringProperty sensors;
    private SimpleStringProperty actState;
    private SimpleStringProperty partIR;

    Robot(String sensors, String actState, String partIR){

        this.sensors = new SimpleStringProperty(sensors);
        this.actState = new SimpleStringProperty(actState);
        this.partIR = new SimpleStringProperty(partIR);
    }


    public String getSensors() {
        return sensors.get();
    }

    public void setSensors (String sensors){
        this.sensors.set(sensors);
    }

    public String getActState(){
        return actState.get();
    }

    public void setActState (String  actState){
        this.actState.set(actState);
    }

    public String getPartIR() {
        return partIR.get();
    }

    public void setPartIR (String partIR){
        this.partIR.set(partIR);
    }


}

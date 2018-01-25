package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.view_model.Robot;
import view.viewIR_Cluster_AreaController;

import java.net.URL;
import java.util.ResourceBundle;

import static view.startGUI.mainStage;

public class viewRobotIRController implements Initializable {
    public Button btn;

   @FXML
    private Label label;

    @FXML
    private Label lbl1;

    @FXML
    private TableView myTable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        int counter = viewIR_Cluster_AreaController.roboTemp.get(viewIR_RobotController.robotFakeID).getId();
        --counter;

        label.setText("Display State of Sensors - Robot id: " +viewIR_RobotController.robotRealID);

        //TODO
        TableColumn sensors = new TableColumn("Sensors");
        sensors.setMinWidth(400.0);
        TableColumn actState = new TableColumn("Actual State");
        actState.setMinWidth(150.0);
        TableColumn partIR = new TableColumn("Partial IR");
        partIR.setMinWidth(150.0);

        String s1, s2, s3, s4, s5, s6, s7;

        myTable.getColumns().addAll(sensors, actState, partIR);
        int len = viewIR_Cluster_AreaController.roboTemp.get(viewIR_RobotController.robotFakeID).getSignal1().length;

        // Check if the sensors of the robot are up or down
        if (viewIR_Cluster_AreaController.roboTemp.get(viewIR_RobotController.robotFakeID).getSignal1()[len-1] == 0) {
            s1 = "DOWN";
        } else s1 = "UP";

        if (viewIR_Cluster_AreaController.roboTemp.get(viewIR_RobotController.robotFakeID).getSignal1()[len-1] == 0) {
            s2 = "DOWN";
        } else s2 = "UP";

        if (viewIR_Cluster_AreaController.roboTemp.get(viewIR_RobotController.robotFakeID).getSignal1()[len-1] == 0) {
            s3 = "DOWN";
        } else s3 = "UP";

        if (viewIR_Cluster_AreaController.roboTemp.get(viewIR_RobotController.robotFakeID).getSignal1()[len-1] == 0) {
            s4 = "DOWN";
        } else s4 = "UP";

        if (viewIR_Cluster_AreaController.roboTemp.get(viewIR_RobotController.robotFakeID).getSignal1()[len-1] == 0) {
            s5 = "DOWN";
        } else s5 = "UP";

        if (viewIR_Cluster_AreaController.roboTemp.get(viewIR_RobotController.robotFakeID).getSignal1()[len-1] == 0) {
            s6 = "DOWN";
        } else s6 = "UP";

        if (viewIR_Cluster_AreaController.roboTemp.get(viewIR_RobotController.robotFakeID).getSignal1()[len-1] == 0) {
            s7 = "DOWN";
        } else s7 = "UP";

        final ObservableList<Robot> data = FXCollections.observableArrayList(

                new Robot("Laser - Calibrazione", s1, "20%"),

                new Robot("Piastra di Processo  -  Livello di Contaminazione", s2, "51%"),

                new Robot("Camera di Processo  -  Temperatura", s3, "42%"),

                new Robot("Camera di Processo  -  Livello di Contaminazione", s4, "73%"),

                new Robot("Camera di Processo  -  Pressione", s5, "2%"),

                new Robot("Injector Plasma  -  Pressione", s6, "62%"),

                new Robot("Injector Plasma  -  Temperatura", s7, "14%")

        );

        sensors.setCellValueFactory(new PropertyValueFactory<Robot, String>("sensors"));
        actState.setCellValueFactory(new PropertyValueFactory<Robot, String>("actState"));
        partIR.setCellValueFactory(new PropertyValueFactory<Robot, String>("partIR"));


        myTable.setItems(data);
    }


    public void goBack(ActionEvent actionEvent) {

        btn.getScene().getWindow().hide();
        new viewIR_RobotController().start(mainStage);

    }
}
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static view.startGUI.mainStage;
import static view.viewIRClusterRobot.newRobo;
import static view.viewIRClusterRobot.roboTemp;

public class viewRobotIRController implements Initializable {
    public Button btn;

    @FXML
    private Label generalIR;

   @FXML
    private Label label;

    @FXML
    private Label lbl1;

    @FXML
    private Label idRob;

    @FXML
    private Label appCl;

    @FXML
    private TableView myTable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        label.setText("Display State of Sensors - Robot: " +viewIR_RobotController.robotFakeID);
        idRob.setText("Robot ID: "+viewIR_RobotController.robotRealID);
        appCl.setText("Appartenance Cluster ID: " +roboTemp.get(viewIR_RobotController.robotRealID-1).getCluster());
        generalIR.setText("General robot IR => ");

        //TODO
        TableColumn sensors = new TableColumn("Sensors");
        sensors.setMinWidth(400.0);
        TableColumn actState = new TableColumn("Actual State");
        actState.setMinWidth(150.0);

        String s1, s2, s3, s4, s5, s6, s7;

        myTable.getColumns().addAll(sensors, actState);

        // Check if the sensors of the robot are up or down
        int roboID = viewIR_RobotController.robotRealID;
        --roboID;

        System.out.println("Robot id: "+roboID);

        int len = roboTemp.get(roboID).getSignal1().length-1;
        if (roboTemp.get(roboID).getSignal1()[len] == 0) {
            s1 = "DOWN";
        } else s1 = "UP";

        len = roboTemp.get(roboID).getSignal2().length-1;
        if (roboTemp.get(roboID).getSignal2()[len] == 0) {
            s2 = "DOWN";
        } else s2 = "UP";

        len = roboTemp.get(roboID).getSignal3().length-1;
        if (roboTemp.get(roboID).getSignal3()[len] == 0) {
            s3 = "DOWN";
        } else s3 = "UP";

        len = roboTemp.get(roboID).getSignal4().length-1;
        if (roboTemp.get(roboID).getSignal4()[len] == 0) {
            s4 = "DOWN";
        } else s4 = "UP";

        len = roboTemp.get(roboID).getSignal5().length-1;
        if (roboTemp.get(roboID).getSignal5()[len] == 0) {
            s5 = "DOWN";
        } else s5 = "UP";

        len = roboTemp.get(roboID).getSignal6().length-1;
        if (roboTemp.get(roboID).getSignal6()[len] == 0) {
            s6 = "DOWN";
        } else s6 = "UP";

        len = roboTemp.get(roboID).getSignal7().length-1;
        if (roboTemp.get(roboID).getSignal7()[len] == 0) {
            s7 = "DOWN";
        } else s7 = "UP";

        final ObservableList<Robot> data = FXCollections.observableArrayList(

                new Robot("Laser - Calibrazione", s1),

                new Robot("Piastra di Processo  -  Livello di Contaminazione", s2),

                new Robot("Camera di Processo  -  Temperatura", s3),

                new Robot("Camera di Processo  -  Livello di Contaminazione", s4),

                new Robot("Camera di Processo  -  Pressione", s5),

                new Robot("Injector Plasma  -  Pressione", s6),

                new Robot("Injector Plasma  -  Temperatura", s7)

        );

        sensors.setCellValueFactory(new PropertyValueFactory<Robot, String>("sensors"));
        actState.setCellValueFactory(new PropertyValueFactory<Robot, String>("actState"));

        myTable.setItems(data);
    }


    public void goBack(ActionEvent actionEvent) throws IOException, ClassNotFoundException {

        btn.getScene().getWindow().hide();
        new viewIR_RobotController().start(mainStage);

    }
}
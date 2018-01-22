package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.view_model.Robot;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static view.startGUI.mainStage;

public class viewRobotIRController implements Initializable {
    public Button btn;

   /* @FXML
    private Label label;

    @FXML
    private Label lbl1;

   @FXML
    private Button btn;*/

    @FXML
    private TableView myTable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        TableColumn sensors = new TableColumn("Sensors");

        TableColumn actState = new TableColumn("Actual State");
        TableColumn partIR = new TableColumn("Partial IR");
        myTable.getColumns().addAll(sensors, actState, partIR);


        final ObservableList<Robot> data = FXCollections.observableArrayList(

                new Robot("Laser - Calibrazione", "DOWN", "20%"),

                new Robot("Piastra di Processo  -  Livello di Contaminazione", "UP", "51%"),

                new Robot("Camera di Processo  -  Temperatura", "DOWN", "42%"),

                new Robot("Camera di Processo  -  Livello di Contaminazione", "UP", "73%"),

                new Robot("Camera di Processo  -  Pressione", "DOWN", "2%"),

                new Robot("Injector Plasma  -  Pressione", "UP", "62%"),

                new Robot("Injector Plasma  -  Temperatura", "DOWN", "14%")

        );

        sensors.setCellValueFactory(new PropertyValueFactory<Robot, String>("sensors"));
        actState.setCellValueFactory(new PropertyValueFactory<Robot, String>("actState"));
        partIR.setCellValueFactory(new PropertyValueFactory<Robot, String>("partIR"));


        myTable.setItems(data);
    }


    public void goBack(ActionEvent actionEvent) {

        btn.getScene().getWindow().hide();
        new viewIRController().start(mainStage);

    }
}
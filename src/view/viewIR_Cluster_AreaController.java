package view;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import controller.DBManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Robot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static view.startGUI.mainStage;


public class viewIR_Cluster_AreaController extends Application {

    public static int roboSize;

    // Get the list of robots
    public static ArrayList<Robot> getRobotList() {

        System.out.println("Getting the list of robots...");
        long startTime = System.currentTimeMillis();

        ArrayList<Robot> roboTemp = DBManager.getDataFromDB(DBManager.dbConnect());

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Got the list of robots in: " +elapsedTime +" ms");

        return roboTemp;
    }

    public static ArrayList<Robot> roboTemp;

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        primaryStage.setTitle("Industrial Robot Dashboard");

        roboTemp = getRobotList();

        FlowPane pane = new FlowPane();
        pane.setHgap(40);
        pane.setVgap(40);
        pane.setRowValignment(VPos.CENTER);
        pane.setColumnHalignment(HPos.LEFT);
        pane.setFocusTraversable(false);

        ScrollPane scroll = new ScrollPane(pane);
        scroll.setFitToWidth(true);
        scroll.setPrefSize(1140, 610);
        scroll.setFocusTraversable(false);

        BorderPane root = new BorderPane(scroll);
        root.setFocusTraversable(false);

        // Central title
        Label title = new Label("Display IR by Cluster / Area");
        title.setFocusTraversable(false);
        title.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
        title.getStyleClass().add("style2");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);

        Region spacer3 = new Region();
        HBox.setHgrow(spacer3, Priority.ALWAYS);

        // Text near choicebox
        Label Lbl = new Label("Select IR to display:");
        Lbl.setFocusTraversable(false);
        Lbl.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
        Lbl.getStyleClass().add("style1");


        // Select here % of IR to display
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setFocusTraversable(false);
        choiceBox.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
        choiceBox.getStyleClass().add("choiceBox");
        choiceBox.getItems().add("5%");
        choiceBox.getItems().add("10%");
        choiceBox.getItems().add("15%");
        choiceBox.getItems().add("20%");
        choiceBox.getItems().add("25%");
        choiceBox.getItems().add("30%");
        choiceBox.getItems().add("35%");
        choiceBox.getItems().add("40%");
        choiceBox.getItems().add("45%");
        choiceBox.getItems().add("50%");
        choiceBox.getItems().add("55%");
        choiceBox.getItems().add("60%");
        choiceBox.getItems().add("65%");
        choiceBox.getItems().add("70%");
        choiceBox.getItems().add("75%");
        choiceBox.getItems().add("80%");
        choiceBox.getItems().add("85%");
        choiceBox.getItems().add("90%");
        choiceBox.getItems().add("95%");
        choiceBox.setValue("40%");


        // Back button
        Button btn = new Button("Back");
        btn.setFocusTraversable(false);
        btn.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
        btn.getStyleClass().add("back_btn");


        // Title and menu
        root.setTop(new VBox(new HBox(spacer, title, spacer2), (new HBox(btn, spacer3, Lbl, choiceBox))));

        // Get the list of clusters
        List<Integer> tempList = simulation.inputSimulation.getDataFromList();

        int counter = 0;

        // Display ID & IR
        for (int i = 0; i < tempList.size(); i++) {

            counter++;

            // Gren panel
            Label id = new Label("");
            id.setPrefSize(150, 150);
            id.setAlignment(Pos.CENTER);
            id.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
            id.getStyleClass().add("style7");
            id.setCursor(Cursor.HAND);
            int finalI = i;
            id.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {

                    roboSize = tempList.get(finalI);
                    System.out.println(roboSize);
                    mainStage.close();
                    new viewIR_RobotController().start(mainStage);

                }
            });

            Label ir = new Label("Cluster ID: " + counter);
            ir.setPrefSize(150, 25);
            ir.setAlignment(Pos.CENTER);
            ir.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
            ir.getStyleClass().add("style6");
            ir.setCursor(Cursor.HAND);
            ir.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {

                    roboSize = tempList.get(finalI);
                    System.out.println(roboSize);
                    mainStage.close();
                    new viewIR_RobotController().start(mainStage);
                }
            });

            Label irr = new Label("IR:  25%");
            irr.setPrefSize(150, 25);
            irr.setAlignment(Pos.CENTER);
            irr.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
            irr.getStyleClass().add("style6");
            irr.setCursor(Cursor.HAND);
            irr.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {

                    roboSize = tempList.get(finalI);
                    System.out.println(roboSize);
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("fxml/viewRobotIR.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mainStage.setScene(new Scene(root, 1000, 650));
                    mainStage.show();
                }
            });

            // Add the panel to window
            pane.getChildren().add(new VBox(id, ir, irr));

        }


        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();


        btn.setOnAction((ActionEvent event) -> {

            // Go back to the start page
            primaryStage.close();
            Parent root1 = null;
            try {
                root1 = FXMLLoader.load(getClass().getResource("fxml/select_ir.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            mainStage.setScene(new Scene(root1, 1000, 650));
            mainStage.show();

        });

    }
}
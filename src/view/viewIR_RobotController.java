package view;

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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.InefficiencyRate;
import model.Robot;

import java.io.IOException;
import java.util.ArrayList;

import static view.startGUI.mainStage;

public class viewIR_RobotController extends Application {

    public static int robotFakeID;
    public static int robotRealID;
    public static double robotIR;

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {

        primaryStage.setTitle("Industrial Robot Dashboard");

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

        // central title
        Label title = new Label("Display IR by Robot");
        title.setFocusTraversable(false);
        title.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
        title.getStyleClass().add("style2");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);

        Region spacer3 = new Region();
        HBox.setHgrow(spacer3, Priority.ALWAYS);

        Region spacer4 = new Region();
        HBox.setHgrow(spacer4, Priority.SOMETIMES);

        // text near choicebox
        Label Lbl = new Label("Select IR to display:");
        Lbl.setFocusTraversable(false);
        Lbl.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
        Lbl.getStyleClass().add("style1");

        // Back button
        Button btn = new Button("Back");
        btn.setFocusTraversable(false);
        btn.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
        btn.getStyleClass().add("back_btn");

        // Title and menu
        root.setTop(new VBox(new HBox(spacer, title, spacer2), (new HBox(btn, spacer4, spacer3))));

        // Display ID & IR
        int tempIndex = viewIRClusterRobot.roboSize;
        ArrayList<Robot> listaTemp = viewIRClusterRobot.newRobo;

        ArrayList<InefficiencyRate> allThings = controller.dataAnalyzer.inefficiencyRateAllRobot(listaTemp);

        int roboCounter = 0;

        for (int i = 0; i < listaTemp.size() ; i++) {

            int robotIn = (int) allThings.get(i).getInefficiencyRate();
            roboCounter++;

            // If the IR is > 20 we show the red icon
            if (robotIn < 20) {

                // Green panel
                // Green robot img
                Label id = new Label("");
                id.setPrefSize(150, 150);
                id.setAlignment(Pos.CENTER);
                id.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
                id.getStyleClass().add("style3");
                id.setCursor(Cursor.HAND);

                int finalI = i;
                int finalRoboCounter = roboCounter;
                int finalI1 = i;
                id.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        robotIR = allThings.get(finalI).getInefficiencyRate();
                        robotRealID = listaTemp.get(finalI).getId();
                        robotFakeID = finalRoboCounter;
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

                Label ir = new Label("Robot ID: "+roboCounter);
                ir.setPrefSize(150, 25);
                ir.setAlignment(Pos.CENTER);
                ir.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
                ir.getStyleClass().add("style6");

                Label irr = new Label("IR: "+robotIn +"%");
                irr.setPrefSize(150, 25);
                irr.setAlignment(Pos.CENTER);
                irr.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
                irr.getStyleClass().add("style6");

                // stampa sulla finestra
                pane.getChildren().add(new VBox(id, ir, irr));

            } else {

                // Red panel
                // Red robot img
                Label id1 = new Label("");
                id1.setPrefSize(150, 150);
                id1.setAlignment(Pos.CENTER);
                id1.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
                id1.getStyleClass().add("style5");
                id1.setCursor(Cursor.HAND);
                int finalI = i;
                int finalRoboCounter = roboCounter;

                id1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        robotIR = allThings.get(finalI).getInefficiencyRate();
                        robotRealID = listaTemp.get(finalI).getId();
                        robotFakeID = finalRoboCounter;
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

                //id label
                Label ir1 = new Label("Robot ID: "+roboCounter);
                ir1.setPrefSize(150, 25);
                ir1.setAlignment(Pos.CENTER);
                ir1.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
                ir1.getStyleClass().add("style6");

                //ir label
                Label ir2 = new Label("IR: " +robotIn +"%");
                ir2.setPrefSize(150, 25);
                ir2.setAlignment(Pos.CENTER);
                ir2.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
                ir2.getStyleClass().add("style6");
                pane.getChildren().add(new VBox(id1, ir1, ir2));

            }

        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        btn.setOnAction((ActionEvent event) -> {

            // Go back to the start page
            primaryStage.close();
            try {
                new viewIRClusterRobot().start(primaryStage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
    }
}
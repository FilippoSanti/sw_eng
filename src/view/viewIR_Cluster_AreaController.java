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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static view.startGUI.mainStage;

public class viewIR_Cluster_AreaController extends Application {

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
        Label title = new Label("Display IR by Areas / Clusters");
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
        Label Lbl = new Label("Select IR to display:" +viewIRClusterRobot.roboSize);
        Lbl.setFocusTraversable(false);
        Lbl.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
        Lbl.getStyleClass().add("style1");


        //select here % of IR to dispaly
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


        //refresh button
        Button refresh= new Button("Refresh");
        refresh.setFocusTraversable(false);
        refresh.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
        refresh.getStyleClass().add("refresh_btn");

        // Title and menu
        root.setTop(new VBox(new HBox(spacer, title, spacer2), (new HBox(btn, spacer4, refresh, spacer3, Lbl, choiceBox))));

        // Get the list of clusters
        List<Integer> tempList = simulation.inputSimulation.getDataFromList();

        // Display ID & IR
        for (int i = 0; i < tempList.size(); i++) {
            // Green panel
            // green ligth img
            Label id = new Label("");
            id.setPrefSize(150, 150);
            id.setAlignment(Pos.CENTER);
            id.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
            id.getStyleClass().add("style7");
            id.setCursor(Cursor.HAND);
            id.setOnMouseClicked(new EventHandler<MouseEvent>() {    // quando servirà di aggiungere il click che manda all'altra view
                @Override
                public void handle(MouseEvent t) {

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

            Label ir = new Label("ID:  123455");
            ir.setPrefSize(150, 25);
            ir.setAlignment(Pos.CENTER);
            ir.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
            ir.getStyleClass().add("style6");
            ir.setCursor(Cursor.HAND);
            ir.setOnMouseClicked(new EventHandler<MouseEvent>() {    // quando servirà di aggiungere il click che manda all'altra view
                @Override
                public void handle(MouseEvent t) {

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

            Label irr = new Label("IR:  25%");
            irr.setPrefSize(150, 25);
            irr.setAlignment(Pos.CENTER);
            irr.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
            irr.getStyleClass().add("style6");
            irr.setCursor(Cursor.HAND);
            irr.setOnMouseClicked(new EventHandler<MouseEvent>() {    // quando servirà di aggiungere il click che manda all'altra view
                @Override
                public void handle(MouseEvent t) {

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



            // stampa sulla finestra
            pane.getChildren().add(new VBox(id, ir, irr));

        }  //end for


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

    public static void redpanel() {

        /*
        // red panel
        //red light img
        Label id1 = new Label("");
        id1.setPrefSize(150, 150);
        id1.setAlignment(Pos.CENTER);
        id1.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
        id1.getStyleClass().add("style8");
        id1.setCursor(Cursor.HAND);
        id1.setOnMouseClicked(new EventHandler<MouseEvent>() {    // quando servirà di aggiungere il click che manda all'altra view
            @Override
            public void handle(MouseEvent t) {

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
        Label ir1 = new Label("ID:  123456");
        ir1.setPrefSize(150, 25);
        ir1.setAlignment(Pos.CENTER);
        ir1.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
        ir1.getStyleClass().add("style6");
        ir1.setCursor(Cursor.HAND);
        ir1.setOnMouseClicked(new EventHandler<MouseEvent>() {    // quando servirà di aggiungere il click che manda all'altra view
            @Override
            public void handle(MouseEvent t) {

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

        //ir label
        Label ir2 = new Label("IR:  55%");
        ir2.setPrefSize(150, 25);
        ir2.setAlignment(Pos.CENTER);
        ir2.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
        ir2.getStyleClass().add("style6");
        ir2.setCursor(Cursor.HAND);
        ir2.setOnMouseClicked(new EventHandler<MouseEvent>() {    // quando servirà di aggiungere il click che manda all'altra view
            @Override
            public void handle(MouseEvent t) {

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
        pane.getChildren().add(new VBox(id1, ir1, ir2));
        */
    }
}
package Dispaly_IR;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main extends Application{

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Industrial Robot Dashboard");


        FlowPane pane = new FlowPane();
        pane.setHgap(30);
        pane.setVgap(30);
        pane.setRowValignment(VPos.CENTER);
        pane.setColumnHalignment(HPos.LEFT);
        pane.setFocusTraversable(false);

        ScrollPane scroll = new ScrollPane(pane);
        scroll.setFitToWidth(true);
        scroll.setPrefSize(1110, 610);
        scroll.setFocusTraversable(false);

        BorderPane root = new BorderPane(scroll);
        root.setFocusTraversable(false);


        // central title
        Label title = new Label("Display IR by ######");
        title.setFocusTraversable(false);
        title.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        title.getStyleClass().add("style2");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);

        Region spacer3 = new Region();
        HBox.setHgrow(spacer3, Priority.ALWAYS);


        // text near choicebox
        Label Lbl= new Label("Select IR to point out:");
        Lbl.setFocusTraversable(false);
        Lbl.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Lbl.getStyleClass().add("style1");


        //select here % of IR to dispaly

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setFocusTraversable(false);
        choiceBox.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
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


        // back button

        Button btn = new Button("Back");
        btn.setFocusTraversable(false);
        btn.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        btn.getStyleClass().add("back_btn");


        // title and menu
        root.setTop (new VBox (new HBox(spacer,title,spacer2 ), (new HBox(btn,spacer3,Lbl,choiceBox))));


     //Display ID & IR

        // premi il tasto back per popolare la view
        btn.setOnAction((ActionEvent event) -> {

                //dichiarazioni delle labels

                        //green panel
                Label id = new Label("K57CM");
                id.setPrefSize(130, 65);
                id.setAlignment(Pos.CENTER);
                id.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                id.getStyleClass().add("style3");
                id.setCursor(Cursor.HAND);
                id.setOnMouseClicked(new EventHandler<MouseEvent>(){    // quando servirà di aggiungere il click che manda all'altra view
                    @Override
                    public void handle(MouseEvent t) {
                        id.setStyle("-fx-font-size: 22px; -fx-font-family: 'Open Sans Light'");
                    }
                 });

                Label ir = new Label("55%");
                ir.setPrefSize(130, 65);
                ir.setAlignment(Pos.CENTER);
                ir.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                ir.getStyleClass().add("style3");
                ir.setCursor(Cursor.HAND);
                ir.setOnMouseClicked(new EventHandler<MouseEvent>(){       // quando servirà di aggiungere il click che manda all'altra view
                    @Override
                    public void handle(MouseEvent t) {
                        ir.setStyle("-fx-font-size: 22px; -fx-font-family: 'Open Sans Light'");
                    }
                });

                        // red panel
                Label id1 = new Label("K57CM");
                id1.setPrefSize(130, 65);
                id1.setAlignment(Pos.CENTER);
                id1.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                id1.getStyleClass().add("style4");
                id1.setCursor(Cursor.HAND);
                id1.setOnMouseClicked(new EventHandler<MouseEvent>(){    // quando servirà di aggiungere il click che manda all'altra view
                    @Override
                    public void handle(MouseEvent t) {
                        id1.setStyle("-fx-font-size: 22px; -fx-font-family: 'Open Sans Light'");
                    }
                });

                Label ir1 = new Label("55%");
                ir1.setPrefSize(130, 65);
                ir1.setAlignment(Pos.CENTER);
                ir1.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                ir1.getStyleClass().add("style4");
                ir1.setCursor(Cursor.HAND);
                ir1.setOnMouseClicked(new EventHandler<MouseEvent>(){    // quando servirà di aggiungere il click che manda all'altra view
                    @Override
                     public void handle(MouseEvent t) {
                        ir1.setStyle("-fx-font-size: 22px; -fx-font-family: 'Open Sans Light'");
                     }
                 });

                // stampa sulla finestra
                pane.getChildren().add(new VBox(id, ir));
                pane.getChildren().add(new VBox(id1, ir1));

        });

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

package Dispaly_IR;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) {
        Image image = new Image(getClass().getResource("../img/green.jpg").toExternalForm());
        Image img = new Image(getClass().getResource("../img/red.jpg").toExternalForm());
        primaryStage.setTitle("Industrial Robot Dashboard");


        FlowPane imagePane = new FlowPane();
        imagePane.setHgap(30);
        imagePane.setVgap(30);
        imagePane.setRowValignment(VPos.CENTER);
        imagePane.setColumnHalignment(HPos.LEFT);
        imagePane.setFocusTraversable(false);

        ScrollPane scroll = new ScrollPane(imagePane);
        scroll.setFitToWidth(true);
        scroll.setPrefSize(1050, 610);
        scroll.setFocusTraversable(false);

        BorderPane root = new BorderPane(scroll);
        root.setFocusTraversable(false);

        // back button (now add image into pane)

        Button btn = new Button("Back");
        btn.setFocusTraversable(false);
        btn.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        btn.getStyleClass().add("back_btn");
        btn.setOnAction((ActionEvent event) -> {
            ImageView newImage = new ImageView(image);
            Label id = new Label();
            newImage.setFitHeight(120);
            newImage.setFitWidth(120);


            ImageView newImg = new ImageView(img);
            newImg.setFitHeight(120);
            newImg.setFitWidth(120);


            newImage.getStyleClass().add("on_img");
            imagePane.getChildren().add(newImage);
            imagePane.getChildren().add(newImg);
        });

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


        root.setTop (new VBox (new HBox(spacer,title,spacer2 ), (new HBox(btn,spacer3,Lbl,choiceBox))));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

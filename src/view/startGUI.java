package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class startGUI extends Application {

    public static Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        mainStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("fxml/auth.fxml"));
        mainStage.setTitle("User Authentication Page");
        mainStage.setScene(new Scene(root, 1000, 657));
        mainStage.show();
    }
}
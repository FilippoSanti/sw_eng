package Choose_IR_Log_Out;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Choose_IR_Log_Out.fxml"));
        primaryStage.setTitle("Industrial Robot Dashboard");
        primaryStage.setScene(new Scene(root, 1000, 650));
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(1000);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

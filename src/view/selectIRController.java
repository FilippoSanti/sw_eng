package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static view.startGUI.mainStage;

public class selectIRController {

    public Button irButton;
    private Stage myStage;

    // View ir by robot
    public void IRbyRobot_btn() throws IOException {
        // Change page
        irButton.getScene().getWindow().hide();
        new viewIRController().start(mainStage);

    }

    // View ir by robot
    public void IRbyCluster_btn() {
        irButton.getScene().getWindow().hide();
        new viewIRController().start(mainStage);
    }

    // Go back to the authetication
    public void LogOut_btn() throws IOException {

        irButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("fxml/auth.fxml"));
        mainStage.setTitle("User Authentication Page");
        mainStage.setScene(new Scene(root, 1000, 650));
        mainStage.setResizable(false);
        mainStage.show();
    }
}
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

    // View ir by cluster - robot
    public void IRbyRobot_btn() throws IOException, ClassNotFoundException, InterruptedException {
        // Change page
        irButton.getScene().getWindow().hide();
        new viewIRClusterRobot().start(mainStage);
    }

    //View ir by area - cluster
    public void IRbyArea_btn() throws IOException, ClassNotFoundException {
        irButton.getScene().getWindow().hide();
        new viewIR_Cluster_AreaController().start(mainStage);
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
package view;

import controller.DBManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static view.startGUI.mainStage;

public class authController {
    @FXML
    private TextField text;
    @FXML
    private PasswordField psw;
    private Stage myStage;

    public void GoToDashboard_btn() throws IOException {

        if (DBManager.Authentication(text.getText(), psw.getText())) {
            // Change page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/select_ir.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            mainStage.close();
            stage.show();

        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred");
            alert.setContentText("Try with a different user code or/and password");

            alert.showAndWait();
        }
    }
}
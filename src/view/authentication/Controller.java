package view.authentication;


import com.mongodb.DB;
import controller.DBManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Controller {
    @FXML
    private TextField text;
    @FXML
    private PasswordField psw;




    public void GoToDashboard_btn() throws IOException {

        if (DBManager.Authentication(text.getText(), psw.getText()))

        {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("select_ir.fxml"));
             Parent root1 = (Parent) fxmlLoader.load();
             Stage stage = new Stage();
             stage.setScene(new Scene(root1));
             stage.show();



         }

         else System.out.println("errore");


    }

    public void IRbyRobot_btn(){
        System.out.println("Dispaly Inefficiency Rate by ROBOT!!!!!");

    }

    public void IRbyCluster_btn(){
        System.out.println("Display Inefficiency Rate by CLUSTER!!!!!");

    }

    public void LogOut_btn(){
        System.out.println("You are Logged Out!!!!!");

    }



    }


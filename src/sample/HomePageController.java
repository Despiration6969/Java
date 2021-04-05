package sample;

import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import javafx.scene.control.Label;
import javafx.stage.WindowEvent;

public class HomePageController {
    @FXML
    private TextField homeTF1;

    @FXML
    private Button homeBtnRegister;

    @FXML
    private Button homeBtnGo;

    @FXML
    void GoButtonPressed() throws IOException{

        Borrower b1 = new Borrower();
        b1.ValidateUser(homeTF1.getText());
      if (b1.isUserIsValidate() == true){
            JOptionPane.showMessageDialog(null, "Successfully Login");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene((root), 900, 600));
            stage.setTitle("Profile Page");
            stage.setResizable(false);
            stage.show();
            stage = (Stage) homeBtnGo.getScene().getWindow();
            stage.close();
            ProfileController pc1 = loader.getController();
            pc1.setUserLoggedIn(homeTF1.getText());
            pc1.setBorrowerProfile();


        }if (b1.isUserIsValidate() == false){
            JOptionPane.showMessageDialog(null, "There's no such user in the system, please register");
        }
    }

    @FXML
    void RegisterButtonPressed() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene((root), 900, 600));
        stage.setTitle("Register");
        stage.setResizable(false);
        stage.show();
        stage = (Stage) homeBtnRegister.getScene().getWindow();
        stage.close();

    }

    @FXML
    void BtnManageBookPressed() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Book.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene((root), 900, 600));
        stage.setTitle("Manage Book");
        stage.setResizable(false);
        stage.show();
        stage = (Stage) homeBtnRegister.getScene().getWindow();
        stage.close();
    }

    @FXML
    void BtnViewHistoryPressed() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("History.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene((root), 900, 600));
        stage.setTitle("History");
        stage.setResizable(false);
        stage.show();
        stage = (Stage) homeBtnRegister.getScene().getWindow();
        stage.close();
    }



}


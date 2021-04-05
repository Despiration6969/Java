package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.*;

public class RegisterController {

    @FXML
    private TextField regTF1;

    @FXML
    private TextField regTF2;

    @FXML
    private Button regBtnBack;

    @FXML
    void BtnBackPressed() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene((root), 900, 540));
        stage.setTitle("HomePage");
        stage.setResizable(false);
        stage.show();
        stage = (Stage) regBtnBack.getScene().getWindow();
        stage.close();
    }

    @FXML
    void BtnClearPressed(){
        regTF1.clear();
        regTF2.clear();
    }

    @FXML
    void BtnRegisterPressed() {
        if (regTF1.getText().isEmpty() || regTF2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please make sure all of the field are entered data.");
        }else{
            Borrower b1 = new Borrower();
            b1.RegisterBorrower(regTF1.getText(), regTF2.getText());
            JOptionPane.showMessageDialog(null, "New borrower has been successfully registered.");
            regTF1.clear();
            regTF2.clear();
        }
    }

}

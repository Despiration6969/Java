package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.awt.print.Book;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ProfileController {

    private String BorrowerID;
    private String BorrowerName;
    private String UserLoggedIn;

    public ProfileController(){
        BorrowerID="";
        BorrowerName="";
    }

    public void setUserLoggedIn(String userLoggedIn) {
        UserLoggedIn = userLoggedIn;
    }

    @FXML
    private TextField profileTF2;

    @FXML
    private TextField profileTF1;

    @FXML
    private TextField profileTF3;


    @FXML
    private Button btnProfileBack;

    @FXML
    void btnBackPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene((root), 900, 540));
        stage.setTitle("Navigator");
        stage.setResizable(false);
        stage.show();
        stage = (Stage) btnProfileBack.getScene().getWindow();
        stage.close();

    }

    public void setBorrowerProfile()throws IOException{

        String pathfile = "Login.txt";
        String BorrowerName =""; String Bdob = ""; int BorrowerOverdue; int TotalOverdue=0;

        Scanner x = new Scanner(new File(pathfile));
        x.useDelimiter(":");
        while (x.hasNext()){
            String line = x.nextLine();
            String[] BorrowerDetail = line.split(":");
            BorrowerName = BorrowerDetail[0];
            Bdob = BorrowerDetail[1];

            if (UserLoggedIn.equals(BorrowerName)){

               profileTF1.setText(BorrowerName);
               profileTF2.setText(Bdob);
               break;
            }
        }
        x.close();

        String pathfile2 = "Overdue.txt";
        x = new Scanner(new File(pathfile2));
        x.useDelimiter(":");
        while (x.hasNext()){
            String Line = x.nextLine();
            String [] OverdueSum = Line.split(":");
            BorrowerName = OverdueSum[0];
            BorrowerOverdue= Integer.parseInt(OverdueSum[1]);

            if(UserLoggedIn.equals(OverdueSum[0])){
                TotalOverdue += BorrowerOverdue;
            }
        }
        x.close();
        profileTF3.setText("RM"+TotalOverdue);

    }
}












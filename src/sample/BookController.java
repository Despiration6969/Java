package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BookController {

    @FXML
    private ComboBox<?> BorrowComboBox1;

    @FXML
    private TextField bookTF2;

    @FXML
    private Button BorrowBtnBack;

    @FXML
    private ComboBox<?> BorrowComboBox2;

    @FXML
    private ComboBox<?> BorrowComboBox3;

    @FXML
    void ComboBox1Showing() {

        BorrowComboBox2.setValue(null);

        try {
            final ObservableList ComboBoxItem = FXCollections.observableArrayList();


            File file = new File("BorrowBook.txt");
            Scanner ScanBookInfo = new Scanner(file);
            ScanBookInfo.useDelimiter(":");
            Set<String> UniqueItem = new HashSet<>();

            while (ScanBookInfo.hasNext()) {

                String BorrowerCheck = ScanBookInfo.nextLine();
                String[] BorrowerDetail = BorrowerCheck.split(":");
                UniqueItem.add(BorrowerDetail[0]);
            }
            for (String i : UniqueItem) {
                ComboBoxItem.add(i);
            }
            BorrowComboBox1.setItems(ComboBoxItem);
            ScanBookInfo.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @FXML
    void ComboBox2Showing() {

        try{

        final ObservableList ComboBoxItem = FXCollections.observableArrayList();


        File file = new File("BorrowBook.txt");
        Scanner ScanBookInfo = new Scanner(file);
        ScanBookInfo.useDelimiter(":");

        while (ScanBookInfo.hasNext()) {

            String BookCheck = ScanBookInfo.nextLine();
            String[] BookDetail = BookCheck.split(":");
            if(BookDetail[0].equals(BorrowComboBox1.getValue())) {
                ComboBoxItem.add(BookDetail[1]);
            }
        }
        BorrowComboBox2.setItems(ComboBoxItem);
        ScanBookInfo.close();
    }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void ComboBox3Showing(){

            try {
                final ObservableList ComboBoxItem = FXCollections.observableArrayList();


                File file = new File("Login.txt");
                Scanner x = new Scanner(file);
                x.useDelimiter(":");
                Set<String> UniqueItem = new HashSet<>();

                while (x.hasNext()) {

                    String UserCheck = x.nextLine();
                    String[] UserDetail = UserCheck.split(":");
                    UniqueItem.add(UserDetail[0]);
                }
                for (String i : UniqueItem) {
                    ComboBoxItem.add(i);
                }
                BorrowComboBox3.setItems(ComboBoxItem);
                x.close();
            } catch (Exception e) {
                e.printStackTrace();

            }


    }

    @FXML
    void btnBackPressed() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene((root), 900, 600));
        stage.setTitle("HomePage");
        stage.setResizable(false);
        stage.show();
        stage = (Stage) BorrowBtnBack.getScene().getWindow();
        stage.close();

    }

    @FXML
    void btnBorrowPressed() {

        if (BorrowComboBox3.getValue()==null || bookTF2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please make sure to select a borrower first and enter an item name");

        }
        else{
            Borrower b1 = new Borrower();
            b1.LendBook((String) BorrowComboBox3.getValue(), bookTF2.getText());
            JOptionPane.showMessageDialog(null, BorrowComboBox3.getValue() + " has successfully borrowed " + bookTF2.getText());
            BorrowComboBox3.setValue(null);
            bookTF2.clear();
        }

    }
    @FXML
    void btnReturnPressed() throws IOException {
        if(BorrowComboBox1.getValue()==null && BorrowComboBox2.getValue()==null){

            JOptionPane.showMessageDialog(null, "Please make sure you have selected both the User and Item");

        }else{
            Borrower b1 = new Borrower();
            b1.CheckIfExpired((String)BorrowComboBox1.getValue(), (String)BorrowComboBox2.getValue());
            if(b1.getOverdue() >1){
                //Add Overdue into new file
                b1.CalculateOverdue((String)BorrowComboBox1.getValue(), (String)BorrowComboBox2.getValue());
                b1.ReturnBook((String) BorrowComboBox1.getValue(), (String) BorrowComboBox2.getValue());
                BorrowComboBox1.setValue(null);
                BorrowComboBox2.setValue(null);
                JOptionPane.showMessageDialog(null, "The selected book has been return but a fine has also been recorded");
            }else{
                b1.ReturnBook((String) BorrowComboBox1.getValue(), (String) BorrowComboBox2.getValue());
                BorrowComboBox1.setValue(null);
                BorrowComboBox2.setValue(null);
                JOptionPane.showMessageDialog(null, "The selected book has been successfully returned");

            }
        }


    }

    @FXML
    void btnExtendPressed() throws IOException {
        if(BorrowComboBox1.getValue()==null || BorrowComboBox2.getValue()==null){
            JOptionPane.showMessageDialog(null, "Please make sure you have selected both the User and Item");
        }else{

            Borrower b1 = new Borrower();
            b1.CheckIfExpired((String) BorrowComboBox1.getValue(), (String) BorrowComboBox2.getValue());
            if (b1.isBookStatusIsExpired()== true) {
                JOptionPane.showMessageDialog(null, "Your Book has been expired and cannot be extend, please return the book and pay for fine");
            }if (b1.isBookStatusIsExpired()== false)
            { b1.ExtendBorrowTime((String) BorrowComboBox1.getValue(), (String) BorrowComboBox2.getValue());
                JOptionPane.showMessageDialog(null, "Your Book Has been extended for another 2 weeks");
            }
        }
    }

}

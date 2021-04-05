package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class HistoryController {

    @FXML
    private ComboBox<?> HistoryComboBox;

    @FXML
    private ListView HistoryListView;

    @FXML
    private Button btnHistory2;

    @FXML
    private ComboBox<?> HistoryComboBox2;


    @FXML
    void HistoryComboBoxShowing() {

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
            HistoryComboBox.setItems(ComboBoxItem);
            x.close();
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    @FXML
    void btnHistoryBackPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene((root), 900, 600));
        stage.setTitle("HomePage");
        stage.setResizable(false);
        stage.show();
        stage = (Stage) btnHistory2.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnClearPressed(){

        HistoryComboBox.setValue(null);
        HistoryComboBox2.setValue(null);
        HistoryListView.setItems(null);

    }

    @FXML
    void HistoryComboBoxShowing2(){
        final ObservableList ComboBoxItem = FXCollections.observableArrayList();
        ComboBoxItem.add("T1");
        ComboBoxItem.add("T2");
        ComboBoxItem.add("T3");
        ComboBoxItem.add("T4");

        HistoryComboBox2.setItems(ComboBoxItem);

    }

    @FXML
    void btnHistoryPressed() throws IOException{

        Borrower b1 = new Borrower();
        if (!(HistoryComboBox.getValue()==null) && HistoryComboBox2.getValue()==null){
            b1.ShowHistory((String)HistoryComboBox.getValue());
            HistoryListView.setItems(b1.getListViewItem());
        }else if (!(HistoryComboBox.getValue()==null) &&!(HistoryComboBox2.getValue()==null)){
            b1.ShowHistory((String)HistoryComboBox.getValue(),(String)HistoryComboBox2.getValue());
            HistoryListView.setItems(b1.getListViewItem());
        }
    }
}


package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage PrimaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        PrimaryStage.setTitle("Home Page");
        PrimaryStage.setScene(new Scene(root, 900, 600));
        PrimaryStage.setResizable(false);
        PrimaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);

    }


}

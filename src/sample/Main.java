package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    // ADMIN : name : adminu     pass: admin
    //res name:mym    pass: 1234
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load (getClass ().getResource ("view/login.fxml"));

        primaryStage.setTitle ("Hotel System");
        primaryStage.setScene (new Scene (root, 1273, 652));
        primaryStage.show ();
    }

    public static void main(String[] args) throws ClassNotFoundException {

        launch (args);

    }
}

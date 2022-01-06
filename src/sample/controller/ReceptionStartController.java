package sample.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReceptionStartController {
    @FXML
    private Button search_btn;
    @FXML
    private Button bookings_specific;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_sign_out;

    @FXML
    private Button btn_change_Pass;

    @FXML
    private Button btn_rooms_details;
    @FXML
    private Label userName_label;
    public static String flag = "";
    public  static  String SENDER = "";
    @FXML
    private Button reservation_btn;
    @FXML
    void chagePass_Clicked(MouseEvent event) throws IOException {
        flag = "reception";
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/changepassword.fxml"));

        Scene twoPageScene = new Scene(twoPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }

    @FXML
    void logOutBtnClicked(ActionEvent event) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        Scene twoPageScene = new Scene(twoPageParent,1273,652);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }

    @FXML
    void roomDet_clicked(MouseEvent event) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/ViewRooms.fxml"));

        Scene twoPageScene = new Scene(twoPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }

    @FXML
    void initialize() {
        assert btn_sign_out != null : "fx:id=\"btn_sign_out\" was not injected: check your FXML file 'receptionstart.fxml'.";
        assert btn_change_Pass != null : "fx:id=\"btn_change_Pass\" was not injected: check your FXML file 'receptionstart.fxml'.";
        assert btn_rooms_details != null : "fx:id=\"btn_rooms_details\" was not injected: check your FXML file 'receptionstart.fxml'.";
        assert reservation_btn != null : "fx:id=\"reservation_btn\" was not injected: check your FXML file 'receptionstart.fxml'.";
        assert bookings_specific != null : "fx:id=\"bookings_specific\" was not injected: check your FXML file 'receptionstart.fxml'.";

        userName_label.setText(LogInController.testS);

    }

    public void reservation_btn_clicked(ActionEvent actionEvent) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/createreservation.fxml"));

        Scene twoPageScene = new Scene(twoPageParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }

    public void see_specific_clicked(MouseEvent mouseEvent) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/allbookings.fxml"));

        Scene twoPageScene = new Scene(twoPageParent);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }

    public void search_clicked(ActionEvent actionEvent) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/searchbooking.fxml"));

        Scene twoPageScene = new Scene(twoPageParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }

    public void back_clicked(ActionEvent actionEvent) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/receptionstart.fxml"));

        Scene twoPageScene = new Scene(twoPageParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }

    public void start_Chat(ActionEvent actionEvent) throws IOException {
        SENDER = userName_label.getText();
        Stage primaryStage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("../view/udpchat.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest((WindowEvent e) -> {
            Platform.exit();
            System.exit(0);
        });

        primaryStage.show();
    }

    public void search_clicked_date(ActionEvent actionEvent) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/datesearching.fxml"));

        Scene twoPageScene = new Scene(twoPageParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }
}

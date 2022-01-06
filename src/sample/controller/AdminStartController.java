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

public class AdminStartController {
    public  static  String flag_admin = "";
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_change_Pass;

    @FXML
    private Button btn_sign_out;

    @FXML
    private Button btn_add_room;

    @FXML
    private Label userName_lbl;
    public  static  String SENDER = "";
    @FXML
    void logOutBtnClicked(ActionEvent event) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        Scene twoPageScene = new Scene(twoPageParent,1273,652);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();

    }
    @FXML
    private Button edit_room_btn;
    @FXML
    void searchBtnClicked(ActionEvent event) {

    }

    @FXML
    void edit_clicked(ActionEvent event) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/changedetails.fxml"));
        Scene twoPageScene = new Scene(twoPageParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }

    @FXML
    void addRoomClicked(ActionEvent event) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/AddRoom.fxml"));
        Scene twoPageScene = new Scene(twoPageParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }

    public void addUser_Clicked(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/adduser.fxml"));
        Scene twoPageScene = new Scene(twoPageParent);

        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }
    @FXML
    void initialize() {
        assert btn_change_Pass != null : "fx:id=\"btn_change_Pass\" was not injected: check your FXML file 'AdminStart.fxml'.";
        assert btn_sign_out != null : "fx:id=\"btn_sign_out\" was not injected: check your FXML file 'AdminStart.fxml'.";
        assert btn_add_room != null : "fx:id=\"btn_add_room\" was not injected: check your FXML file 'AdminStart.fxml'.";
        assert edit_room_btn != null : "fx:id=\"edit_room_btn\" was not injected: check your FXML file 'AdminStart.fxml'.";

        userName_lbl.setText(LogInController.testS);


    }


    public void deleteRoom_clicked(MouseEvent mouseEvent) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/deleteroom.fxml"));
        Scene twoPageScene = new Scene(twoPageParent);

        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }


    public void change_password(ActionEvent actionEvent) throws IOException {
        flag_admin = "admin";
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/changepassword.fxml"));

        Scene twoPageScene = new Scene(twoPageParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }

    public void start_chat(ActionEvent actionEvent) throws IOException {
        SENDER = userName_lbl.getText();
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
}

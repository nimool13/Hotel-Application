package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.model.DBM;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangePasswordController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button p1bookBtn;

    @FXML
    private Button btn_sign_out;

    @FXML
    private Label starNrTravelers;

    @FXML
    private Label starFirst;

    @FXML
    private Label starLast;

    @FXML
    private Label starPnr;

    @FXML
    private Label starAdress;
    @FXML
    private Label userName_lbl;
    @FXML
    private Label starPhone;

    @FXML
    private Label starEmail;

    @FXML
    private TextField curr_pass;

    @FXML
    private TextField new_pass;

    @FXML
    private TextField rep_pass;

    @FXML
    private Button change_btn;
    DBM dbm ;

    @FXML
    void bookBtnClicked(ActionEvent event) {

    }

    @FXML
    void logOutBtnClicked(ActionEvent event) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        Scene twoPageScene = new Scene(twoPageParent, 1273,652);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }

    @FXML
    void nextBtnClicked(ActionEvent event) {

    }

    @FXML
    void submit_clicked(MouseEvent event) {
        dbm = new DBM();
        if (dbm.checkCurrentPass(userName_lbl.getText()).equals(curr_pass.getText())&&validate()){
        dbm.changePass(new_pass.getText(),userName_lbl.getText());

        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Oop! Something went wrong please check your password");
            alert.show();
        }


    }
    private boolean validate() {
        boolean temp = true;

        String pass = new_pass.getText();
        String cpass = rep_pass.getText();
        if (!pass.equals(cpass)) {
            temp = false;

        }

        return temp;


    }
    @FXML
    void initialize() {
        assert p1bookBtn != null : "fx:id=\"p1bookBtn\" was not injected: check your FXML file 'changepassword.fxml'.";
        assert btn_sign_out != null : "fx:id=\"btn_sign_out\" was not injected: check your FXML file 'changepassword.fxml'.";
        assert starNrTravelers != null : "fx:id=\"starNrTravelers\" was not injected: check your FXML file 'changepassword.fxml'.";
        assert starFirst != null : "fx:id=\"starFirst\" was not injected: check your FXML file 'changepassword.fxml'.";
        assert starLast != null : "fx:id=\"starLast\" was not injected: check your FXML file 'changepassword.fxml'.";
        assert starPnr != null : "fx:id=\"starPnr\" was not injected: check your FXML file 'changepassword.fxml'.";
        assert starAdress != null : "fx:id=\"starAdress\" was not injected: check your FXML file 'changepassword.fxml'.";
        assert starPhone != null : "fx:id=\"starPhone\" was not injected: check your FXML file 'changepassword.fxml'.";
        assert starEmail != null : "fx:id=\"starEmail\" was not injected: check your FXML file 'changepassword.fxml'.";
        assert curr_pass != null : "fx:id=\"curr_pass\" was not injected: check your FXML file 'changepassword.fxml'.";
        assert new_pass != null : "fx:id=\"new_pass\" was not injected: check your FXML file 'changepassword.fxml'.";
        assert rep_pass != null : "fx:id=\"rep_pass\" was not injected: check your FXML file 'changepassword.fxml'.";
        assert change_btn != null : "fx:id=\"change_btn\" was not injected: check your FXML file 'changepassword.fxml'.";
        assert userName_lbl != null : "fx:id=\"userName_lbl\" was not injected: check your FXML file 'changepassword.fxml'.";
        userName_lbl.setText(LogInController.testS);

    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        String page = "";
        if (AdminStartController.flag_admin != null){
            page = "AdminStart.fxml";
        }
        else {
            page = "../view/receptionstart.fxml";
        }
        Parent twoPageParent = FXMLLoader.load(getClass().getResource(page));
        Scene twoPageScene = new Scene(twoPageParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }
}

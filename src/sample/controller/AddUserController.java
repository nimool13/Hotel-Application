package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.model.MyConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddUserController {

    int role = 0;
    String Srole = "";
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button p1bookBtn;
    @FXML
    private Button btn_change_Pass;
    @FXML
    private Button btn_sign_out;
    @FXML
    private TextField userF_txt;
    @FXML
    private TextField userL_txt;
    @FXML
    private TextField userName_txt;
    @FXML
    private TextField user_email;

    @FXML
    private TextField userPass_txt;
    @FXML
    private ComboBox<String> roles_drop;
    @FXML
    private Button addBtn;

    @FXML
    public void addClicked(MouseEvent mouseEvent) {
        String sql = "{call add_user(?,?,?,?,?,?)}";
        try {

            Connection con = MyConnection.getConnection ();
            Statement st = con.createStatement ();
            CallableStatement cs = con.prepareCall (sql);
            cs.setString (1, userF_txt.getText ());
            cs.setString (2, userL_txt.getText ());
            cs.setString (3, userName_txt.getText ());
            cs.setString (4, userPass_txt.getText ());
            cs.setString (5, user_email.getText ());
            if (Srole.equalsIgnoreCase ("Admin")) {
                cs.setInt (6, 1);
            } else {
                cs.setInt (6, 2);
            }


            cs.executeQuery ();


        } catch (Exception e1) {
            e1.printStackTrace ();
        }
    }


    @FXML
    void bookBtnClicked(ActionEvent event) {

    }

    @FXML
    void logOutBtnClicked(ActionEvent event) throws IOException {
        Parent twoPageParent = FXMLLoader.load (getClass ().getResource ("../view/login.fxml"));
        Scene twoPageScene = new Scene (twoPageParent,1273,652);

        Stage window = (Stage) ( (Node) event.getSource () ).getScene ().getWindow ();

        window.setScene (twoPageScene);
        window.show ();
    }

    @FXML
    void nextBtnClicked(ActionEvent event) {

    }

    @FXML
    void role_choosed(ActionEvent event) {
        //  role = update();
    }

    @FXML
    void searchBtnClicked(ActionEvent event) {

    }

    public void update() {

        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent> () {
                    public void handle(ActionEvent e) {


                        Srole = roles_drop.getValue ();


                    }
                };


    }

    @FXML
    void initialize() {
        assert p1bookBtn != null : "fx:id=\"p1bookBtn\" was not injected: check your FXML file 'adduser.fxml'.";
        assert btn_change_Pass != null : "fx:id=\"btn_change_Pass\" was not injected: check your FXML file 'adduser.fxml'.";
        assert btn_sign_out != null : "fx:id=\"btn_sign_out\" was not injected: check your FXML file 'adduser.fxml'.";
        assert userF_txt != null : "fx:id=\"userF_txt\" was not injected: check your FXML file 'adduser.fxml'.";
        assert userL_txt != null : "fx:id=\"userL_txt\" was not injected: check your FXML file 'adduser.fxml'.";
        assert userName_txt != null : "fx:id=\"userName_txt\" was not injected: check your FXML file 'adduser.fxml'.";
        assert userPass_txt != null : "fx:id=\"userPass_txt\" was not injected: check your FXML file 'adduser.fxml'.";
        assert roles_drop != null : "fx:id=\"roles_drop\" was not injected: check your FXML file 'adduser.fxml'.";
        assert addBtn != null : "fx:id=\"addBtn\" was not injected: check your FXML file 'adduser.fxml'.";
        roles_drop.getItems ().add ("Admin");
        roles_drop.getItems ().add ("Reception");

        addBtn.setDisable (true);

        user_email.focusedProperty ().addListener ((arg0, oldValue, newValue) -> {
            if (!newValue) { // when focus lost
                if (!user_email.getText ().matches ("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
//                    user_email.setText("");
                    Alert alert = new Alert (Alert.AlertType.WARNING);

                    if (!user_email.getText ().trim ().isEmpty ()) {
                        addBtn.setDisable (true);
                        alert.setContentText ("Wrong email format!");
                        alert.show ();
                    } else {
                        alert.setContentText ("Email is required!");
                        alert.show ();
                    }
                } else {
                    addBtn.setDisable (false);
                }
            }
        });
    }

    public void role_mouse_clicked(MouseEvent mouseEvent) {
        update ();
    }


    public void addBtnClicked(ActionEvent actionEvent) {

    }

    public void roles_chossed(ActionEvent actionEvent) {
        Srole = roles_drop.getValue ();
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        Parent twoPageParent = FXMLLoader.load (getClass ().getResource ("../view/adminstart.fxml"));

        Scene twoPageScene = new Scene (twoPageParent);
        Stage window = (Stage) ( (Node) actionEvent.getSource () ).getScene ().getWindow ();

        window.setScene (twoPageScene);
        window.show ();
    }

}

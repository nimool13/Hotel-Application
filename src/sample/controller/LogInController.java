package sample.controller;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.model.MyConnection;

import javax.swing.*;

public class LogInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label wrongPass;

    @FXML
    private TextField tUname;

    @FXML
    private PasswordField tPword;

    @FXML
    private Button logInBtn;

    @FXML
    private Button exitBtn;

    public static String testS = "";

    @FXML
    void exitBtnClicked(ActionEvent event) {

    }


 
    public void loginBtnClicked(javafx.scene.input.MouseEvent mouseEvent) {
        String user_name = tUname.getText();
        String user_pass = tPword.getText();

        Connection con;
        Statement st;
        
        int test=0;
        String sql_user = "select * from user where user_name ='" + user_name + "' and user_Password='" + user_pass + "'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
             con = MyConnection.getConnection();
             st = con.createStatement();
            ResultSet rs = st.executeQuery(sql_user);
            int count = 0;
            while (rs.next()) {
                 test = rs.getInt("role_ID");
                testS = rs.getString("user_name");
                count++;
            }


             if (count > 0 && test == 2) {

                System.out.println("Login Successful");

                Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/receptionstart.fxml"));

                Scene twoPageScene = new Scene(twoPageParent);
                Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

                window.setScene(twoPageScene);
                window.show();

                //page.setVisible(true);
            }
            else if (count > 0 && test == 1) {

                System.out.println("Login Successful");

                Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/adminstart.fxml"));

                Scene twoPageScene = new Scene(twoPageParent);
                Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

                window.setScene(twoPageScene);
                window.show();

                //page.setVisible(true);
            }
            else {
                tUname.setText("");
                tPword.setText("");
                wrongPass.setText("Invalid Login name or password");

            }

        } catch (Exception e1) {
        }
    }
    @FXML
    void initialize() {
        assert wrongPass != null : "fx:id=\"wrongPass\" was not injected: check your FXML file 'login.fxml'.";
        assert tUname != null : "fx:id=\"tUname\" was not injected: check your FXML file 'login.fxml'.";
        assert tPword != null : "fx:id=\"tPword\" was not injected: check your FXML file 'login.fxml'.";
        assert logInBtn != null : "fx:id=\"logInBtn\" was not injected: check your FXML file 'login.fxml'.";
        assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'login.fxml'.";

    }

    public void reset_pass_clicked (ActionEvent actionEvent) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/resetpassword.fxml"));

        Scene twoPageScene = new Scene(twoPageParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }
}

package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.DBM;
import sample.model.MyConnection;
import sample.model.PasswordGenerator;
import sample.model.SendAttachmentInEmail;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
public class ResetPasswordController {
    DBM dbm ;
    @FXML
    private Button backLog;
    @FXML
    private ResourceBundle resources;
    PasswordGenerator passwordGenerator;
    @FXML
    private TextField user_name_txt;
    @FXML
    private Label message;
    @FXML
    private URL location;
    public static String nameU = "";
    public static String emailU = "";
    @FXML
    private Label wrongPass;

    @FXML
    private TextField uEmail;

    @FXML
    private Button reset;

    @FXML
    private Button exitBtn;
    String newPass = "";
    @FXML
    void exitBtnClicked(ActionEvent event) {
            System.exit(0);

        }


    @FXML
    void reset_pass_clicked(ActionEvent event) {
        String user_name = user_name_txt.getText();
        String user_email = uEmail.getText();

        if (doCheck() > 0){
            newPass = PasswordGenerator.generateStrongPassword();
            String as = "Here is your new password " + newPass;
            new SendAttachmentInEmail().sendEmail(user_email,as);

            message.setText("Email has sent to "+ user_email + " with your new password");
            dbm = new DBM();
            dbm.changePass(newPass,user_name);
            backLog.setVisible(true);

        }
        else {
            message.setText("Invalid Login username or email");

        }

    }



    public int doCheck(){
        String user_name = user_name_txt.getText();
        String user_email = uEmail.getText();
        int count = 0;
        Connection con;
        Statement st;
        String sql_user = "select * from user where user_name ='" + user_name + "' and email='" + user_email + "'";
        try {

            con = MyConnection.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql_user);

            while (rs.next()) {
                emailU = rs.getString("email");
                nameU = rs.getString("user_name");
                count++;
            }




        } catch (Exception e1) {
        }
        return count;
    }

    @FXML
    void initialize() {
        assert wrongPass != null : "fx:id=\"wrongPass\" was not injected: check your FXML file 'resetpassword.fxml'.";
        assert uEmail != null : "fx:id=\"uEmail\" was not injected: check your FXML file 'resetpassword.fxml'.";
        assert reset != null : "fx:id=\"reset\" was not injected: check your FXML file 'resetpassword.fxml'.";
        assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'resetpassword.fxml'.";

    }

    public void backLog_clicked(ActionEvent actionEvent) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        Scene twoPageScene = new Scene(twoPageParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }
}

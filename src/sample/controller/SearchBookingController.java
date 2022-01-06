package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.model.MyConnection;

public class SearchBookingController {
    public  static  String reservationNR = "";
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField lastName_input;

    @FXML
    private Button search_btn;

    @FXML
    private ListView<String> res_number;

    @FXML
    private ListView<String> user_list;

    @FXML
    private ListView<String>date_list;

    @FXML
    private ListView<String> paymentMethodeList;

    @FXML
    private ListView<String> status_list;

    @FXML
    private ListView<String> roomNumber_list;

    @FXML
    private Label note_txt;

    @FXML
    private Button go_back;

    @FXML
    void back_clicked(MouseEvent event) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/receptionstart.fxml"));

        Scene twoPageScene = new Scene(twoPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }

    @FXML
    void res_nmr_clicked(MouseEvent event) throws IOException {
        reservationNR =     res_number.getSelectionModel().selectedItemProperty().getValue();
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/modifybboking.fxml"));

        Scene twoPageScene = new Scene(twoPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }

    @FXML
    void searchBtn_clicked(ActionEvent event) {
    if (!lastName_input.getText().equals("")){
        String sql = "{call search_booking(?)}";
        try {

            Connection con = MyConnection.getConnection();
            Statement st = con.createStatement();
            CallableStatement cs = con.prepareCall(sql);

            cs.setString(1, lastName_input.getText());


            ResultSet rs1 = cs.executeQuery();


            if (rs1.last()) {

                rs1.beforeFirst();
            }


            res_number.getItems().clear();
            date_list.getItems().clear();
            user_list.getItems().clear();
            paymentMethodeList.getItems().clear();

            status_list.getItems().clear();
            roomNumber_list.getItems().clear();
            while (rs1.next()) {
                res_number.getItems().add(String.valueOf(rs1.getInt("res_NR")));

                user_list.getItems().add(String.valueOf(rs1.getString("user_name")));
                date_list.getItems().add(rs1.getDate("date_reserve") + " , " + rs1.getDate("reserved_until"));
                status_list.getItems().add(rs1.getString("res_status"));
                paymentMethodeList.getItems().add(rs1.getString("payment_methode"));
                roomNumber_list.getItems().add(String.valueOf(rs1.getInt("room_NR")));


            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    }

    @FXML
    void initialize() {
        assert lastName_input != null : "fx:id=\"lastName_input\" was not injected: check your FXML file 'searchbooking.fxml'.";
        assert search_btn != null : "fx:id=\"search_btn\" was not injected: check your FXML file 'searchbooking.fxml'.";
        assert res_number != null : "fx:id=\"res_number\" was not injected: check your FXML file 'searchbooking.fxml'.";
        assert user_list != null : "fx:id=\"user_list\" was not injected: check your FXML file 'searchbooking.fxml'.";
        assert date_list != null : "fx:id=\"date_list\" was not injected: check your FXML file 'searchbooking.fxml'.";
        assert paymentMethodeList != null : "fx:id=\"paymentMethodeList\" was not injected: check your FXML file 'searchbooking.fxml'.";
        assert status_list != null : "fx:id=\"status_list\" was not injected: check your FXML file 'searchbooking.fxml'.";
        assert roomNumber_list != null : "fx:id=\"roomNumber_list\" was not injected: check your FXML file 'searchbooking.fxml'.";
        assert note_txt != null : "fx:id=\"note_txt\" was not injected: check your FXML file 'searchbooking.fxml'.";
        assert go_back != null : "fx:id=\"go_back\" was not injected: check your FXML file 'searchbooking.fxml'.";

    }
}

package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.model.DBM;
import sample.model.DBM2;
import sample.model.MyConnection;
import sample.model.poco.Reservation;
import sample.model.poco.Room;

public class AllBookingsController {

    public  static  String test = "";
    int selectedRoom = 0;
    DBM2 dbm = new DBM2();
    ArrayList<Room> arrayList = new ArrayList<>();
    ArrayList<Reservation> reservationArrayList = new ArrayList<Reservation>();
    int size = 0;
    CheckBox c;
    @FXML
    private ResourceBundle resources;
    @FXML
    private Label note_txt;
    @FXML
    private ListView<String> res_number;
    @FXML
    private ListView<String> paid_list;
    @FXML
    private ListView<String> paymentMethodeList;
    @FXML
    private URL location;
    @FXML
    private Button go_back;
    @FXML
    private ComboBox<String> room_box;
    @FXML
    private ListView<String> user_list;

    @FXML
    private ListView<String> date_list;
    private CheckBox paid_box;
    @FXML
    private ListView<String> status_list;

    @FXML
    void roomChoosed(ActionEvent event) {

        selectedRoom = Integer.parseInt(room_box.getValue());
        String sql = "{call show_booking(?)}";
        try {

            Connection con = MyConnection.getConnection();
            Statement st = con.createStatement();
            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, selectedRoom);


            ResultSet rs1 = cs.executeQuery();


            if (rs1.last()) {
                size = rs1.getRow();
                rs1.beforeFirst();
            }

            int s = 0;
            res_number.getItems().clear();
            date_list.getItems().clear();
            user_list.getItems().clear();
            paymentMethodeList.getItems().clear();
            res_number.getItems().clear();
            status_list.getItems().clear();
            while (rs1.next()) {
                res_number.getItems().add(String.valueOf(rs1.getInt("res_NR")));

                user_list.getItems().add(String.valueOf(rs1.getString("user_name")));
                date_list.getItems().add(rs1.getDate("date_reserve") + " , " + rs1.getDate("reserved_until"));
                status_list.getItems().add(rs1.getString("res_status"));
                paymentMethodeList.getItems().add(rs1.getString("payment_methode"));


            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    @FXML
    void initialize() {
        assert room_box != null : "fx:id=\"room_box\" was not injected: check your FXML file 'allbookings.fxml'.";
        assert user_list != null : "fx:id=\"user_list\" was not injected: check your FXML file 'allbookings.fxml'.";
        assert date_list != null : "fx:id=\"date_list\" was not injected: check your FXML file 'allbookings.fxml'.";
        assert status_list != null : "fx:id=\"status_list\" was not injected: check your FXML file 'allbookings.fxml'.";
        assert note_txt != null : "fx:id=\"note_txt\" was not injected: check your FXML file 'allbookings.fxml'.";

        dbm = new DBM2();
        arrayList = dbm.returnRoom();
        for (int i = 0; i < arrayList.size(); i++) {
            room_box.getItems().add(arrayList.get(i).getRoom_NR());
        }
    }

    public void back_clicked(MouseEvent mouseEvent) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/receptionstart.fxml"));
        Scene twoPageScene = new Scene(twoPageParent);

        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();

    }

    public void res_nmr_clicked(MouseEvent mouseEvent) throws IOException {

    test =     res_number.getSelectionModel().selectedItemProperty().getValue();
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/modifybboking.fxml"));

        Scene twoPageScene = new Scene(twoPageParent);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }
}

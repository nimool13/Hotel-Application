package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.model.DBM2;
import sample.model.MyConnection;
import sample.model.poco.Room;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeleteRoomController {

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
    private Label starPhone;

    @FXML
    private Label starEmail;

    @FXML
    private ComboBox<String> rooms;

    @FXML
    private Button p1nextBtn;
    int selectedRoom = 0;
    DBM2 dbm = new DBM2();
    ArrayList<Room> arrayList = new ArrayList<>();



    @FXML
    void logOutBtnClicked(ActionEvent event) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        Scene twoPageScene = new Scene(twoPageParent,1273,652);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }

    @FXML
    void submBtnClicked(ActionEvent event) {

        String sql = "{call delete_room(?)}";
        try{

            Connection con = MyConnection.getConnection();
            Statement st=con.createStatement();
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1,selectedRoom);


            cs.executeQuery();




        }

        catch(Exception e1){

        }
    }

    @FXML
    void initialize() {
        assert p1bookBtn != null : "fx:id=\"p1bookBtn\" was not injected: check your FXML file 'deleteroom.fxml'.";
        assert btn_sign_out != null : "fx:id=\"btn_sign_out\" was not injected: check your FXML file 'deleteroom.fxml'.";
        assert starNrTravelers != null : "fx:id=\"starNrTravelers\" was not injected: check your FXML file 'deleteroom.fxml'.";
        assert starFirst != null : "fx:id=\"starFirst\" was not injected: check your FXML file 'deleteroom.fxml'.";
        assert starLast != null : "fx:id=\"starLast\" was not injected: check your FXML file 'deleteroom.fxml'.";
        assert starPnr != null : "fx:id=\"starPnr\" was not injected: check your FXML file 'deleteroom.fxml'.";
        assert starAdress != null : "fx:id=\"starAdress\" was not injected: check your FXML file 'deleteroom.fxml'.";
        assert starPhone != null : "fx:id=\"starPhone\" was not injected: check your FXML file 'deleteroom.fxml'.";
        assert starEmail != null : "fx:id=\"starEmail\" was not injected: check your FXML file 'deleteroom.fxml'.";
        assert rooms != null : "fx:id=\"rooms\" was not injected: check your FXML file 'deleteroom.fxml'.";
        assert p1nextBtn != null : "fx:id=\"p1nextBtn\" was not injected: check your FXML file 'deleteroom.fxml'.";
        arrayList = dbm.returnRoom();
        for (int i = 0; i < arrayList.size(); i++) {
            rooms.getItems().add(arrayList.get(i).room_NR);
        }
    }

    public void room_Choosed(ActionEvent actionEvent) {
        selectedRoom = Integer.parseInt(rooms.getValue());
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/AdminStart.fxml"));
        Scene twoPageScene = new Scene(twoPageParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }
}

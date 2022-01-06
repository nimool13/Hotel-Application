package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.model.MyConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddRoomController {

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
    private Label starNrTravelers;

    @FXML
    private Label starFirst;

    @FXML
    private Label starLast;

    @FXML
    private Label starPnr;
    @FXML
    private ComboBox<String> status_Box;
    @FXML
    private Label starAdress;

    @FXML
    private Label starPhone;

    @FXML
    private Label starEmail;

    @FXML
    private TextField room_nr_txt;

    @FXML
    private TextField room_location_txt;

    @FXML
    private TextField room_size_txt;

    @FXML
    private TextField room_type_txt;

    @FXML
    private TextArea room_det_txt;

    @FXML
    private ComboBox<String> Beds;

    @FXML
    private Button p1nextBtn;

    int beds_nr;
    String stat = "";
    @FXML
    void addBtnClicked(ActionEvent event) {
        String sql = "{call add_room(?,?,?,?,?,?,?)}";
        try{

            Connection con = MyConnection.getConnection();
            Statement st=con.createStatement();
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1,Integer.parseInt(room_nr_txt.getText()));
            cs.setInt(2,Integer.parseInt(room_size_txt.getText()));
            cs.setInt(3,beds_nr);
            cs.setString(4,room_location_txt.getText());
            cs.setString(5,room_type_txt.getText());
            if (stat.equalsIgnoreCase("Available")){
                cs.setInt(6, 1);
            }
            else {
                cs.setInt(6, 2);
            }

            cs.setString(7,room_det_txt.getText());

            cs.executeQuery();




        }

        catch(Exception e1){}
    }
    @FXML
    void beds_choosed(ActionEvent event) {
        beds_nr = Integer.parseInt(Beds.getValue());
    }
    public double update() {

        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {


                        beds_nr = Integer.parseInt(Beds.getValue());

                    }
                };

        // Set on action
        Beds.setOnAction(event);


return beds_nr;
    }
    @FXML
    void bookBtnClicked(ActionEvent event) {

    }

    @FXML
    void logOutBtnClicked(ActionEvent event) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        Scene twoPageScene = new Scene(twoPageParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();

    }

    @FXML
    void nextBtnClicked(ActionEvent event) {

    }

    @FXML
    void searchBtnClicked(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert p1bookBtn != null : "fx:id=\"p1bookBtn\" was not injected: check your FXML file 'AddRoom.fxml'.";
        assert btn_change_Pass != null : "fx:id=\"btn_change_Pass\" was not injected: check your FXML file 'AddRoom.fxml'.";
        assert btn_sign_out != null : "fx:id=\"btn_sign_out\" was not injected: check your FXML file 'AddRoom.fxml'.";
        assert starNrTravelers != null : "fx:id=\"starNrTravelers\" was not injected: check your FXML file 'AddRoom.fxml'.";
        assert starFirst != null : "fx:id=\"starFirst\" was not injected: check your FXML file 'AddRoom.fxml'.";
        assert starLast != null : "fx:id=\"starLast\" was not injected: check your FXML file 'AddRoom.fxml'.";
        assert starPnr != null : "fx:id=\"starPnr\" was not injected: check your FXML file 'AddRoom.fxml'.";
        assert starAdress != null : "fx:id=\"starAdress\" was not injected: check your FXML file 'AddRoom.fxml'.";
        assert starPhone != null : "fx:id=\"starPhone\" was not injected: check your FXML file 'AddRoom.fxml'.";
        assert starEmail != null : "fx:id=\"starEmail\" was not injected: check your FXML file 'AddRoom.fxml'.";
        assert room_nr_txt != null : "fx:id=\"room_nr_txt\" was not injected: check your FXML file 'AddRoom.fxml'.";
        assert room_location_txt != null : "fx:id=\"room_location_txt\" was not injected: check your FXML file 'AddRoom.fxml'.";
        assert room_size_txt != null : "fx:id=\"room_size_txt\" was not injected: check your FXML file 'AddRoom.fxml'.";
        assert room_type_txt != null : "fx:id=\"room_type_txt\" was not injected: check your FXML file 'AddRoom.fxml'.";
        assert room_det_txt != null : "fx:id=\"room_det_txt\" was not injected: check your FXML file 'AddRoom.fxml'.";
        assert Beds != null : "fx:id=\"Beds\" was not injected: check your FXML file 'AddRoom.fxml'.";
        assert p1nextBtn != null : "fx:id=\"p1nextBtn\" was not injected: check your FXML file 'AddRoom.fxml'.";
        for (int i = 1; i <=3 ; i++) {
            Beds.getItems().add(String.valueOf(i));
        }
        status_Box.getItems().add("Booked");
        status_Box.getItems().add("Available");
    }

    public void status_choosed(ActionEvent actionEvent) {

    stat = status_Box.getValue();
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/AdminStart.fxml"));
        Scene twoPageScene = new Scene(twoPageParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }
}

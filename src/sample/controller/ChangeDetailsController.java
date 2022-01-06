package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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

public class ChangeDetailsController {

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
    private ComboBox<String> room_comBox;

    @FXML
    private Label starLast;

    @FXML
    private Label starPnr;
    @FXML
    private ComboBox<String> status;
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
    private TextField beds_number;
    @FXML
    private TextField room_size_txt;
    String statusString = "";
    @FXML
    private TextField room_type_txt;

    @FXML
    private TextArea room_det_txt;



    @FXML
    private Button change_btn;
    int selectedRoom;
    DBM2 dbm;
    ArrayList<Room> arrayList = new ArrayList<>();
    int test = 0;
    int bds=0;
    @FXML
    void beds_choosed(ActionEvent event) {

    }

    @FXML
    void bookBtnClicked(ActionEvent event) {

    }

    @FXML
    void changeAction(ActionEvent event) {
        String roomSize = room_size_txt.getText ();
        String bedsNumber = beds_number.getText ();
        Alert alert = new Alert(Alert.AlertType.ERROR);

        try {
            Integer.parseInt (roomSize);
        } catch (NumberFormatException e) {
            alert.setContentText("Wrong room size format!");
            alert.show();
            return;
        }

        try {
            Integer.parseInt (bedsNumber);
        } catch (NumberFormatException e) {
            alert.setContentText("Wrong beds number size format!");
            alert.show();
            return;
        }


        dbm = new DBM2();
        dbm.changeDetails(room_location_txt.getText(),Integer.parseInt(room_size_txt.getText()),room_type_txt.getText(),room_det_txt.getText(),Integer.parseInt(beds_number.getText()),test);

        String sql = "{call changeRoomDetails(?,?,?,?,?,?,?)}";
        try{

            Connection con = MyConnection.getConnection();
            Statement st=con.createStatement();
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1,selectedRoom);
            cs.setInt(2,Integer.parseInt(room_size_txt.getText()));
            cs.setInt(3,Integer.parseInt(beds_number.getText()));
            cs.setString(4,room_location_txt.getText());
            cs.setString(5,room_type_txt.getText());



            cs.setString(7,room_det_txt.getText());

            if (statusString.equalsIgnoreCase("Available")){
                cs.setInt(6,1);
            }
            else {
                cs.setInt(6,2);
            }


            cs.executeQuery();




        }

        catch(Exception e1){
            e1.printStackTrace();
        }

    }

    @FXML
    void change_clicked(MouseEvent event) {
      /*  dbm = new DBM();
        dbm.changeDetails(room_location_txt.getText(),Double.parseDouble(room_size_txt.getText()),room_type_txt.getText(),room_det_txt.getText(),Integer.parseInt(room_nr_txt.getText()),Integer.parseInt(beds_number.getText()));
*/
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
    void room_chossed(ActionEvent event) {
      int s =  room_comBox.getSelectionModel().getSelectedIndex();
        selectedRoom = Integer.parseInt(String.valueOf(room_comBox.getValue()));
        test = s;
        room_nr_txt.setText(arrayList.get(s).room_NR);
        room_type_txt.setText(arrayList.get(s).type);
        room_det_txt.setText(arrayList.get(s).getDetails());
        room_location_txt.setText(arrayList.get(s).getLocation());
        room_size_txt.setText(arrayList.get(s).size);
        beds_number.setText(arrayList.get(s).getBed_NR());
        status.setValue(arrayList.get(s).getStatus());

    }
    public void update() {

      /*  EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {


                        selectedRoom = Integer.parseInt(String.valueOf(room_comBox.getValue()));
                        test = Integer.parseInt((String) room_comBox.getValue());
                        room_nr_txt.setText(arrayList.get(test-1).room_NR);
                        room_type_txt.setText(arrayList.get(test-1).type);
                        room_det_txt.setText(arrayList.get(test-1).getDetails());
                        room_location_txt.setText(arrayList.get(test-1).getLocation());
                        room_size_txt.setText(arrayList.get(test-1).size);
                        beds_number.setText(arrayList.get(test-1).getBed_NR());

                        // room_details.setText(arrayList.get(test - 1).details);
                        //  room_type.setText(arrayList.get(test - 1).type);
                    }
                };


        room_comBox.setOnAction(event);*/

      /*  test = Integer.parseInt((String) room_comBox.getValue());
        room_nr_txt.setText(arrayList.get(test-1).room_NR);
        room_type_txt.setText(arrayList.get(test-1).type);
        room_det_txt.setText(arrayList.get(test-1).details);
        room_location_txt.setText(arrayList.get(test-1).location);
        room_size_txt.setText(arrayList.get(test-1).size);*/

    }
    @FXML
    void initialize() {
        assert p1bookBtn != null : "fx:id=\"p1bookBtn\" was not injected: check your FXML file 'changedetails.fxml'.";
        assert btn_sign_out != null : "fx:id=\"btn_sign_out\" was not injected: check your FXML file 'changedetails.fxml'.";
        assert starNrTravelers != null : "fx:id=\"starNrTravelers\" was not injected: check your FXML file 'changedetails.fxml'.";
        assert starFirst != null : "fx:id=\"starFirst\" was not injected: check your FXML file 'changedetails.fxml'.";
        assert room_comBox != null : "fx:id=\"room_comBox\" was not injected: check your FXML file 'changedetails.fxml'.";
        assert starLast != null : "fx:id=\"starLast\" was not injected: check your FXML file 'changedetails.fxml'.";
        assert starPnr != null : "fx:id=\"starPnr\" was not injected: check your FXML file 'changedetails.fxml'.";
        assert starAdress != null : "fx:id=\"starAdress\" was not injected: check your FXML file 'changedetails.fxml'.";
        assert starPhone != null : "fx:id=\"starPhone\" was not injected: check your FXML file 'changedetails.fxml'.";
        assert starEmail != null : "fx:id=\"starEmail\" was not injected: check your FXML file 'changedetails.fxml'.";
        assert room_nr_txt != null : "fx:id=\"room_nr_txt\" was not injected: check your FXML file 'changedetails.fxml'.";
        assert room_location_txt != null : "fx:id=\"room_location_txt\" was not injected: check your FXML file 'changedetails.fxml'.";
        assert room_size_txt != null : "fx:id=\"room_size_txt\" was not injected: check your FXML file 'changedetails.fxml'.";
        assert room_type_txt != null : "fx:id=\"room_type_txt\" was not injected: check your FXML file 'changedetails.fxml'.";
        assert room_det_txt != null : "fx:id=\"room_det_txt\" was not injected: check your FXML file 'changedetails.fxml'.";
        assert beds_number != null : "fx:id=\"beds_number\" was not injected: check your FXML file 'changedetails.fxml'.";

        assert change_btn != null : "fx:id=\"change_btn\" was not injected: check your FXML file 'changedetails.fxml'.";
        room_nr_txt.setEditable(false);
        dbm = new DBM2();
        arrayList = dbm.returnRoom();
        for (int i = 0; i < arrayList.size(); i++) {
            room_comBox.getItems().add(arrayList.get(i).getRoom_NR());
        }

        status.getItems().add("Available");
        status.getItems().add("Booked");
    }

    public void status_choosed(ActionEvent actionEvent) {
        statusString = status.getValue();
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/adminstart.fxml"));

        Scene twoPageScene = new Scene(twoPageParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }
}

package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.DBM2;
import sample.model.poco.Room;

public class ViewRoomsController {
    int selectedRoom;
    DBM2 dbm = new DBM2();
    ArrayList<Room> arrayList = new ArrayList<>();
    int test = 0;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back_btn;

    @FXML
    private ComboBox<String> rooms_number;

    @FXML
    private TextField size_txt;

    @FXML
    private TextField beds_txt;

    @FXML
    private TextField location_txt;

    @FXML
    private TextField type_txt;

    @FXML
    private TextField ststus_txt;

    @FXML
    private TextArea details_txt;

    @FXML
    private Button out_btn;

    @FXML
    void back_clicked(ActionEvent event) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/receptionstart.fxml"));

        Scene twoPageScene = new Scene(twoPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }



    @FXML
    void room_choosed(ActionEvent event) {
        selectedRoom = Integer.parseInt(rooms_number.getValue());

        details_txt.setText(arrayList.get(rooms_number.getSelectionModel().getSelectedIndex()).getDetails());
        type_txt.setText(arrayList.get(rooms_number.getSelectionModel().getSelectedIndex()).type);
        beds_txt.setText(arrayList.get(rooms_number.getSelectionModel().getSelectedIndex()).bed_NR);
        size_txt.setText(arrayList.get(rooms_number.getSelectionModel().getSelectedIndex()).getSize());
        location_txt.setText(arrayList.get(rooms_number.getSelectionModel().getSelectedIndex()).getLocation());
        ststus_txt.setText(arrayList.get(rooms_number.getSelectionModel().getSelectedIndex()).getSize());
    }

    @FXML
    void sign_out_clicked(ActionEvent event) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        Scene twoPageScene = new Scene(twoPageParent,1273,652);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }

    @FXML
    void initialize() {
        assert back_btn != null : "fx:id=\"back_btn\" was not injected: check your FXML file 'ViewRooms.fxml'.";
        assert rooms_number != null : "fx:id=\"rooms_number\" was not injected: check your FXML file 'ViewRooms.fxml'.";
        assert size_txt != null : "fx:id=\"size_txt\" was not injected: check your FXML file 'ViewRooms.fxml'.";
        assert beds_txt != null : "fx:id=\"beds_txt\" was not injected: check your FXML file 'ViewRooms.fxml'.";
        assert location_txt != null : "fx:id=\"location_txt\" was not injected: check your FXML file 'ViewRooms.fxml'.";
        assert type_txt != null : "fx:id=\"type_txt\" was not injected: check your FXML file 'ViewRooms.fxml'.";
        assert ststus_txt != null : "fx:id=\"ststus_txt\" was not injected: check your FXML file 'ViewRooms.fxml'.";
        assert details_txt != null : "fx:id=\"details_txt\" was not injected: check your FXML file 'ViewRooms.fxml'.";
        assert out_btn != null : "fx:id=\"out_btn\" was not injected: check your FXML file 'ViewRooms.fxml'.";
        arrayList = dbm.returnRoom();
        for (int i = 0; i < arrayList.size(); i++) {
            rooms_number.getItems().add(arrayList.get(i).room_NR);
        }
    }
}

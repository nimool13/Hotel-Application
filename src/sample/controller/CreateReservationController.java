package sample.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.model.DBM2;
import sample.model.MyConnection;
import sample.model.poco.Room;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateReservationController {

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
    private Label create_res;

    @FXML
    private TextField user_nameTxt;

    @FXML
    private TextField user_Lname;

    @FXML
    private TextField user_adress;

    @FXML
    private ComboBox<String> room_NMR;

    @FXML
    private ComboBox<String> methode;

    @FXML
    private DatePicker from_picker;

    @FXML
    private DatePicker to_picker;

    @FXML
    private CheckBox paid_box;

    @FXML
    private CheckBox nPaid_box;

    @FXML
    private Button p1nextBtn;
    String dateFrom = "";
    DBM2 dbm;
    String met = "";
    ArrayList<Room> arrayList = new ArrayList<>();
    String selectedRoom = "";
    @FXML
    void addBtnClicked(ActionEvent event) {
        String sql = "{call add_reservation(?,?,?,?,?,?,?,?)}";
        try{

            Connection con = MyConnection.getConnection();
            Statement st=con.createStatement();
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1,Integer.parseInt(selectedRoom));
            cs.setString(2, String.valueOf(from_picker.getValue()));
            if (paid_box.isSelected()){
                cs.setInt(3,1);
            }
            else {
                cs.setInt(3,2);
            }

            cs.setString(4,to_picker.getValue().toString());
            cs.setString(5,user_nameTxt.getText());
            cs.setString(6,user_Lname.getText());
            System.out.println(user_adress.getText());
            cs.setString(7,user_adress.getText());
            if (met.equalsIgnoreCase("Cash")){
                cs.setInt(8,3);
            }
            else  if (met.equalsIgnoreCase("Bank transfers")) {
                cs.setInt(8,2);
            }
            else {
                cs.setInt(8,1);
            }


            cs.executeQuery();




        }

        catch(Exception e1){
            e1.printStackTrace();
        }
    }

    @FXML
    void bookBtnClicked(ActionEvent event) {

    }

    @FXML
    void from_picker_chosed(ActionEvent event) {
        to_picker.setDisable(false);
        to_picker.setValue (null);
        dateFrom = String.valueOf(from_picker.getValue());

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
    void methode_choosed(ActionEvent event) {
    met = methode.getValue();
    }

    @FXML
    void mouse_clicked(MouseEvent event) {

    }

    @FXML
    void nPaid_clicked(MouseEvent event) {

    }

    @FXML
    void nextBtnClicked(ActionEvent event) {

    }

    @FXML
    void paid_clicked(MouseEvent event) {

    }

    @FXML
    void room_choosed(ActionEvent event) {
    selectedRoom = String.valueOf(room_NMR.getValue());
    }

    @FXML
    void to_picker_choosed(ActionEvent event) {
        room_NMR.setDisable(false);
        dbm = new DBM2();
        arrayList = new ArrayList<>();
        arrayList = dbm.returnAvailableRooms(dateFrom,String.valueOf(to_picker.getValue()));
        room_NMR.getItems().clear();
        for (int i = 0; i < arrayList.size(); i++) {

            room_NMR.getItems().add(arrayList.get(i).getRoom_NR());
        }

    }

    @FXML
    void initialize() {
        assert p1bookBtn != null : "fx:id=\"p1bookBtn\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert btn_sign_out != null : "fx:id=\"btn_sign_out\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert starNrTravelers != null : "fx:id=\"starNrTravelers\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert starFirst != null : "fx:id=\"starFirst\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert starLast != null : "fx:id=\"starLast\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert starPnr != null : "fx:id=\"starPnr\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert starAdress != null : "fx:id=\"starAdress\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert starPhone != null : "fx:id=\"starPhone\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert starEmail != null : "fx:id=\"starEmail\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert create_res != null : "fx:id=\"create_res\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert user_nameTxt != null : "fx:id=\"user_nameTxt\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert user_Lname != null : "fx:id=\"user_Lname\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert user_adress != null : "fx:id=\"user_adress\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert room_NMR != null : "fx:id=\"room_NMR\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert methode != null : "fx:id=\"methode\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert from_picker != null : "fx:id=\"from_picker\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert to_picker != null : "fx:id=\"to_picker\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert paid_box != null : "fx:id=\"paid_box\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert nPaid_box != null : "fx:id=\"nPaid_box\" was not injected: check your FXML file 'createreservation.fxml'.";
        assert p1nextBtn != null : "fx:id=\"p1nextBtn\" was not injected: check your FXML file 'createreservation.fxml'.";
     /*   dbm = new DBM2();
        arrayList = dbm.returnRoom();
        for (int i = 0; i < arrayList.size(); i++) {
            room_NMR.getItems().add(arrayList.get(i).getRoom_NR());
        }*/
        room_NMR.setDisable(true);
        methode.getItems().add("Credit/Debit card payments");
        methode.getItems().add("Bank transfers");
        methode.getItems().add("Cash");
        from_picker.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(from_picker.getValue().plusDays(1))) {
                            setDisable(true);

                        }
                    }
                };
            }
        };

        to_picker.setDayCellFactory(dayCellFactory);
        to_picker.setDisable(true);
    }

    public void paid_clicked(javafx.scene.input.MouseEvent mouseEvent) {
    }

    public void nPaid_clicked(javafx.scene.input.MouseEvent mouseEvent) {
    }


    public void goBack(ActionEvent actionEvent) throws IOException {
        Parent twoPageParent = FXMLLoader.load(getClass().getResource("../view/receptionstart.fxml"));
        Scene twoPageScene = new Scene(twoPageParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }
}

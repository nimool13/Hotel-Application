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
import sample.model.poco.Booking;
import sample.model.poco.Customer;
import sample.model.poco.Room;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModifyBookingController {

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
    private Button back_btn;

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
    Customer customer;
    @FXML
    private DatePicker from_picker;

    @FXML
    private DatePicker to_picker;
    ArrayList<Room> arrayList = new ArrayList<>();
    @FXML
    private CheckBox paid_box;

    @FXML
    private CheckBox nPaid_box;

    @FXML
    private Button p1nextBtn;

    int roomNR = 0;

    DBM2 dbm2 ;
    Booking booking;
    String check = "";

    int resNR = 0;

    @FXML
    void addBtnClicked(ActionEvent event) {
        String sql = "{call change_reservation(?,?,?,?,?,?,?,?,?)}";
        try{

            Connection con = MyConnection.getConnection();
            Statement st=con.createStatement();
            CallableStatement cs = con.prepareCall(sql);
             cs.setString(1,user_Lname.getText());
            cs.setString(2,user_nameTxt.getText());
            cs.setInt(3,roomNR);
            cs.setString(4,from_picker.getValue().toString());

            if (paid_box.isSelected()){
                cs.setInt(5,1);
            }
            else {
                cs.setInt(5,2);
            }

            cs.setString(6,to_picker.getValue().toString());
           cs.setInt(7,resNR);
           cs.setString(8,methode.getValue().toString());
            cs.setString(9,user_adress.getText());

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
    void methode_choosed(ActionEvent event) {

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
    roomNR = Integer.parseInt(room_NMR.getValue());
    }

    @FXML
    void to_picker_choosed(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert back_btn != null : "fx:id=\"back_btn\" was not injected: check your FXML file 'modifybboking.fxml'.";

        assert p1bookBtn != null : "fx:id=\"p1bookBtn\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert btn_sign_out != null : "fx:id=\"btn_sign_out\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert starNrTravelers != null : "fx:id=\"starNrTravelers\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert starFirst != null : "fx:id=\"starFirst\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert starLast != null : "fx:id=\"starLast\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert starPnr != null : "fx:id=\"starPnr\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert starPhone != null : "fx:id=\"starPhone\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert starEmail != null : "fx:id=\"starEmail\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert create_res != null : "fx:id=\"create_res\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert user_nameTxt != null : "fx:id=\"user_nameTxt\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert user_Lname != null : "fx:id=\"user_Lname\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert user_adress != null : "fx:id=\"user_adress\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert room_NMR != null : "fx:id=\"room_NMR\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert methode != null : "fx:id=\"methode\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert from_picker != null : "fx:id=\"from_picker\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert to_picker != null : "fx:id=\"to_picker\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert paid_box != null : "fx:id=\"paid_box\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert nPaid_box != null : "fx:id=\"nPaid_box\" was not injected: check your FXML file 'modifybboking.fxml'.";
        assert p1nextBtn != null : "fx:id=\"p1nextBtn\" was not injected: check your FXML file 'modifybboking.fxml'.";
        booking = new Booking();
        dbm2 = new DBM2();
        if (!AllBookingsController.test.equals("")){
            resNR = Integer.parseInt(AllBookingsController.test);
            check = "AllBookingsController";
        }
        else  if (!SearchBookingController.reservationNR.equals("")) {
            resNR = Integer.parseInt(SearchBookingController.reservationNR);
            check = "SearchBookingController";
        }


        booking = dbm2.getBooking(resNR);
        arrayList = dbm2.returnRoom();
        for (int i = 0; i < arrayList.size(); i++) {
            room_NMR.getItems().add(arrayList.get(i).getRoom_NR());
        }
        room_NMR.setValue(booking.getRoomNumber());
        roomNR = Integer.parseInt(room_NMR.getValue());

        user_nameTxt.setText(booking.getfName());
        user_Lname.setText(booking.getlName());
        from_picker.setValue(LocalDate.parse(String.valueOf(booking.getDate_from())));
        to_picker.setValue(LocalDate.parse(String.valueOf(booking.getDate_to())));
        if (booking.getpStatus().equalsIgnoreCase("Paid")){
            paid_box.setSelected(true);
        }
        else {
            nPaid_box.setSelected(true);
        }

         customer = new Customer();
        customer = dbm2.getCustomer(resNR);
        user_adress.setText(customer.getAddress());
        methode.getItems().add("Credit/Debit card payments");
        methode.getItems().add("Bank transfers");
        methode.getItems().add("Cash");
        methode.setValue(customer.getPayment());



    }

    public void back_clicked(ActionEvent actionEvent) throws IOException {
        Parent twoPageParent;
        if (check.equalsIgnoreCase("AllBookingsController")){
             twoPageParent = FXMLLoader.load(getClass().getResource("../view/allbookings.fxml"));
        }
        else{
            twoPageParent = FXMLLoader.load(getClass().getResource("../view/searchbooking.fxml"));
        }




        Scene twoPageScene = new Scene(twoPageParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(twoPageScene);
        window.show();
    }
}

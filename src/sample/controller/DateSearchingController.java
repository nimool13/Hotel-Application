package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.model.DBM2;
import sample.model.poco.Booking;

public class DateSearchingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button search_btn;

    @FXML
    private ListView<String> res_number;

    @FXML
    private ListView<String> user_list;
    @FXML
    private DatePicker date_from_picker;
    @FXML
    private ListView<String> date_list;
    @FXML
    private DatePicker date_to_picker;
    @FXML
    private ListView<String> status_list;

    @FXML
    private ListView<String> roomNumber_list;
    ArrayList<Booking> bookingArrayList;
    DBM2 dbm2;
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
    void res_nmr_clicked(MouseEvent event) {

    }

    @FXML
    void searchBtn_clicked(ActionEvent event) {
        dbm2 = new DBM2();
        bookingArrayList = new ArrayList<>();
        bookingArrayList = dbm2.dateSearching(String.valueOf(date_from_picker.getValue()),String.valueOf(date_to_picker.getValue()));
        res_number.getItems().clear();
        user_list.getItems().clear();
        date_list.getItems().clear();
        status_list.getItems().clear();
        roomNumber_list.getItems().clear();

        for (int i = 0; i < bookingArrayList.size(); i++) {
            res_number.getItems().add(bookingArrayList.get(i).getResNumber());
            user_list.getItems().add(bookingArrayList.get(i).getlName()+", "+bookingArrayList.get(i).getfName());
            date_list.getItems().add(bookingArrayList.get(i).getDate_from()+" - "+bookingArrayList.get(i).getDate_to());
            status_list.getItems().add(bookingArrayList.get(i).getpStatus());
            roomNumber_list.getItems().add(bookingArrayList.get(i).getRoomNumber());
        }



    }

    @FXML
    void initialize() {
        assert search_btn != null : "fx:id=\"search_btn\" was not injected: check your FXML file 'datesearching.fxml'.";
        assert res_number != null : "fx:id=\"res_number\" was not injected: check your FXML file 'datesearching.fxml'.";
        assert user_list != null : "fx:id=\"user_list\" was not injected: check your FXML file 'datesearching.fxml'.";
        assert date_list != null : "fx:id=\"date_list\" was not injected: check your FXML file 'datesearching.fxml'.";
        assert status_list != null : "fx:id=\"status_list\" was not injected: check your FXML file 'datesearching.fxml'.";
        assert roomNumber_list != null : "fx:id=\"roomNumber_list\" was not injected: check your FXML file 'datesearching.fxml'.";
        assert note_txt != null : "fx:id=\"note_txt\" was not injected: check your FXML file 'datesearching.fxml'.";
        assert go_back != null : "fx:id=\"go_back\" was not injected: check your FXML file 'datesearching.fxml'.";
        date_from_picker.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(date_from_picker.getValue().plusDays(1))) {
                            setDisable(true);

                        }
                    }
                };
            }
        };

        date_to_picker.setDayCellFactory(dayCellFactory);

    }
}

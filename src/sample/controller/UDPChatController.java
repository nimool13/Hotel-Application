package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import sample.model.UDPChatCommunicator;

import java.net.URL;
import java.util.ResourceBundle;

public class UDPChatController implements Initializable {

    @FXML
    public TextArea txtAreaChat;
    @FXML
    public TextField txtMessage;
    @FXML
    public TextField txtName;
    @FXML
    public ToggleGroup gg;

    public  static  String Name = "";

    private UDPChatCommunicator _communicator = new UDPChatCommunicator(this);



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        _communicator.startListen();
          if (AdminStartController.SENDER != null || !AdminStartController.SENDER.equals("")){
            Name = AdminStartController.SENDER;
        }
        else {
            Name = ReceptionStartController.SENDER;
        }

    }


    public void receiveMessage(String message) {
        txtAreaChat.setText(txtAreaChat.getText() + "\n" + message);
    }


    public void error(Exception e) {
        showAlert("An error has occured and the application will close: \n" + e.getMessage(), "Error Error!");
        System.exit(1);
    }




    @FXML
    private void handleSendButton() {
        if (inputValid(txtName.getText(), txtMessage.getText())) {
            this.sendMessage(txtName.getText(), txtMessage.getText());
            txtMessage.setText("");
        }
    }

    private boolean inputValid(String name, String message) {
        if (name.length() == 0) {
            this.showAlert("Please write your name to use the chat", "Fail");
            return false;
        }
        if (message.length() == 0) {
            this.showAlert("Please write a real message.", "Fail");
            return false;
        }
        return true;
    }

    
    private void sendMessage(String name, String message) {
        try {
            _communicator.sendChat(name, message);
        } catch (Exception e) {
            this.error(e);
        }
    }

    private void showAlert(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);

        alert.showAndWait();
    }

}

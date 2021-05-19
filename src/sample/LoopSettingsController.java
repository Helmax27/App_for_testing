package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import sun.rmi.runtime.NewThreadAction;

import java.net.URL;
import java.util.ResourceBundle;

public class LoopSettingsController {
    @FXML
    public TextField taLoop;
    @FXML
    public Button buttonCancelLoop;
    @FXML
    public Button buttonSaveLoop;

    @FXML
    private void onSaveButton(ActionEvent actionEvent) {
        if (taLoop.getText().equals("")) {
            showAlert("Warning alert", "Please enter any value");
        } else {
            NewsampleController.
        }
    }
    @FXML
    private void onCancelLoop() {
        Stage stage = (Stage) buttonCancelLoop.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        taLoop.setTextFormatter(new TextFormatter<Integer>(change -> {
            if (!(change.getControlNewText().matches("[0-9]{0,3}"))) {
                return null;
            } else {
                return change;
            }
        }));
    }
}

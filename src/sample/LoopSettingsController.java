package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoopSettingsController {
    @FXML
    public TextField taLoop;
    @FXML
    public Button buttonCancelLoop;
    @FXML
    public Button buttonSaveLoop;
    public Label l;

    public void setl(Label l) {
        this.l = l;
    }

    public Label getL() {
        return l;
    }

    @FXML
    private void onSaveButton(ActionEvent actionEvent) {
        if (taLoop.getText().equals("")) {
            showAlert("Warning alert", "Please enter any value");
        } else {
            l.setText(taLoop.getText());
        }
        onCancelLoop();
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

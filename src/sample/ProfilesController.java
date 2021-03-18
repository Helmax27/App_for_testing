package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;

public class ProfilesController {
    @FXML
    public Label pupLabelProfileLable;
    @FXML
    public Button buttonAddNewProfile;
    @FXML
    public Label labelNewProfileName;
    @FXML
    public TextField taforNewProfileName;
    @FXML
    public Label labelConnectionType;
    @FXML
    public Label labelPort;
    @FXML
    public TextField taPort;
    @FXML
    public Button buttonCancelSettings;
    @FXML
    public Button buttonSaveSettings;
    @FXML
    public RadioButton radioButtonCOM;
    @FXML
    public RadioButton radioButtonTCP;
    @FXML
    public Label pupLabelParameters;

    @FXML
    public void onAddNewProfile(ActionEvent actionEvent) throws IOException {


        int port = Integer.parseInt(taPort.getText());
        String portType = "";
        if (radioButtonCOM.isSelected()) {
            portType = "COM";
        } else {
            portType = "TCP";
        }

        Profilesdetails profilesdetails = new Profilesdetails(taforNewProfileName.getText(), portType, port);
        Writer writer = Files.newBufferedWriter(Paths.get("Existingprofiles.json"));
    }


    @FXML
    public void onCancelProfileSettings(ActionEvent actionEvent) {
    }

    @FXML
    public void onSaveProfileSettings(ActionEvent actionEvent) {
        
    }

    @FXML
    public void comChecked() {
        radioButtonCOM.setSelected(true);
        radioButtonTCP.setSelected(false);
    }

    @FXML
    public void tcpChecked() {
        radioButtonCOM.setSelected(false);
        radioButtonTCP.setSelected(true);
    }




    @FXML
    public void initialize() {
        taforNewProfileName.setTextFormatter(new TextFormatter<Integer>(change -> {
            if (!(change.getControlNewText().matches("[a-zA-Z_ 0-9]{1,20}"))) {
                return null;
            } else {
                return change;
            }
        }));
        initDate();

    }

    @FXML
    private void initDate() {
    }
}

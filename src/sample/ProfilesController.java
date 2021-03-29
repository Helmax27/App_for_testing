package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

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
    public ListView lv;
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

    public ArrayList<Profilesdetails> profiles;
    public String currentProfileName;

    @FXML
    public void onAddNewProfile(ActionEvent actionEvent) throws IOException {
        ObservableList<String> profileNames = lv.getItems();
        boolean count = true;
        //TODO check that we didn't have temp profile
        for (String name : profileNames) {
            if (name.equals("temp")) {
                //TODO alert window
                showAlert("Warning alert", "Profile cannot be saved with the temp name, please change");
                count = false;
            }
        }
        if (count){
            profileNames.add("temp");
            lv.setItems(profileNames);
            currentProfileName = "temp";
        }

    }

    @FXML
    public void onCancelProfileSettings(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancelSettings.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.showAndWait();
    }

    @FXML
    public void onSaveProfileSettings(ActionEvent actionEvent) throws FileNotFoundException {
        int port = Integer.parseInt(taPort.getText());
        String portType = "";
        if (radioButtonCOM.isSelected()) {
            portType = "COM";
        } else {
            portType = "TCP";
        }
        if (currentProfileName.equals("temp")) {
            ObservableList<String> profileNames = lv.getItems();
            int count = 0;
            for (String name : profileNames) {
                if (taforNewProfileName.getText().equals(name)) {
                    //TODO alert window
                    showAlert("Warning alert", "This name already exists, please change");
                    count += 1;
                }
            }
            if (count == 0) profiles.add(new Profilesdetails(taforNewProfileName.getText(), portType, port));
        } else {
            ObservableList<String> profileNames = lv.getItems();
            int count = 0;
            if (!currentProfileName.equals(taforNewProfileName.getText())) {
                for (String name : profileNames) {
                    if (taforNewProfileName.getText().equals(name)) {
                        //TODO alert window
                        showAlert("Warning alert", "This name already exists, please change");
                        count += 1;
                    }
                }
            }
            if (count == 0) {
                for (Profilesdetails profile : profiles) {
                    if (profile.profileName.equals(currentProfileName)) {
                        profile.setProfileName(taforNewProfileName.getText());
                        profile.setPort(port);
                        profile.setConnectionType(portType);
                    }
                }
            }
        }

        //TODO save profiles to json file and update listview
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("C:\\Users\\helen\\IdeaProjects\\App for testing\\src\\sample\\Profiles\\Existingprofiles.json")) {
            gson.toJson(profiles, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        readProfiles();

        taforNewProfileName.setTextFormatter(null);
        taPort.setTextFormatter(null);
        initDate();

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
        taPort.setTextFormatter(new TextFormatter<Integer>(change -> {
            if (!(change.getControlNewText().matches("[0-9]{1,4}"))) {
                return null;
            } else {
                return change;
            }
        }));
        initDate();
    }

    @FXML
    private void initDate() {
        try {
            profiles = readProfiles();
            ObservableList<String> profileNames = FXCollections.observableArrayList();
            for (Profilesdetails pr : profiles) {
                profileNames.add(pr.profileName);
            }
            lv.setItems(profileNames);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Profilesdetails> readProfiles() throws FileNotFoundException {
        ArrayList<Profilesdetails> profiles = new ArrayList<>();
        Gson gson = new Gson();
        Profilesdetails[] profilesdetails = gson.fromJson(new FileReader("C:\\Users\\helen\\IdeaProjects\\App for testing\\src\\sample\\Profiles\\Existingprofiles.json"), Profilesdetails[].class);
        for (Profilesdetails pr : profilesdetails) {
            profiles.add(pr);
        }
        return profiles;
    }
}

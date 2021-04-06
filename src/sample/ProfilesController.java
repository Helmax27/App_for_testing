package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.layout.Pane;

public class ProfilesController {
    final ListView listv = new ListView(FXCollections.observableList(Arrays.asList()));
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


    //Creating a new Profile after click on the Add Profile button
    @FXML
    public void onAddNewProfile(ActionEvent actionEvent) throws IOException {
        taPort.setDisable(false);
        taforNewProfileName.setDisable(false);
        radioButtonCOM.setDisable(false);
        radioButtonTCP.setDisable(false);
        ObservableList<String> profileNames = lv.getItems();
        boolean count = true;
        //A new profile is created with a temporary name
        //TODO check that we didn't have temp profile
        for (String name : profileNames) {
            if (name.equals("temp")) {
                //TODO alert window
                showAlert("Warning alert", "Profile cannot be saved with the temp name, please change");
                count = false;
            }
        }
        if (count) {
            profileNames.add("temp");
            lv.setItems(profileNames);
            currentProfileName = "temp";
        }

        lv.getSelectionModel().selectLast();


    }

    //Closing the profile window
    @FXML
    public void onCancelProfileSettings(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancelSettings.getScene().getWindow();
        stage.close();
    }

    //Creating alert alerts
    private void showAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.showAndWait();
    }

    //Saving the entered settings after clicking on the Save button
    @FXML
    public void onSaveProfileSettings(ActionEvent actionEvent) throws FileNotFoundException {
        //reading data
        int port = Integer.parseInt(taPort.getText());
        String portType = "";
        if (radioButtonCOM.isSelected()) {
            portType = "COM";
        } else {
            portType = "TCP";
        }
        //Profile name validation
        if (currentProfileName.equals("temp")) {
            ObservableList<String> profileNames = lv.getItems();
            int count = 0;
            for (String name : profileNames) {
                //Checking that the profile is not saved with temp name
                if (taforNewProfileName.getText().equals(name)) {
                    //TODO alert window
                    showAlert("Warning alert", "The profile is not saved with temp name, please change");
                    count += 1;
                }
            }
            //Save the profile with a unique name
            if (count == 0) profiles.add(new Profilesdetails(taforNewProfileName.getText(), portType, port));
        } else {
            ObservableList<String> profileNames = lv.getItems();
            int count = 0;
            //Checking that the profile name  is unique
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

        lv.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                String item = (String)lv.getSelectionModel().getSelectedItem();
                taPort.setDisable(false);
                taforNewProfileName.setDisable(false);
                radioButtonCOM.setDisable(false);
                radioButtonTCP.setDisable(false);
                for (Profilesdetails profile:profiles){
                    if (profile.profileName.equals(item)){
                        currentProfileName = profile.getProfileName();
                        taPort.setText(String.valueOf(profile.getPort()));
                        taforNewProfileName.setText(profile.getProfileName());
                        if (profile.getConnectionType().equals("TCP")){
                            radioButtonTCP.setSelected(true);
                            radioButtonCOM.setSelected(false);
                        }
                        else{
                            radioButtonCOM.setSelected(true);
                            radioButtonTCP.setSelected(false);
                        }

                    }
                }
            }
        });


        taPort.setDisable(true);
        taforNewProfileName.setDisable(true);
        radioButtonCOM.setDisable(true);
        radioButtonTCP.setDisable(true);
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

    @FXML
    public void handleMouseClick(MouseEvent arg0) {
        Object item = lv.getSelectionModel().getSelectedItem();
        taPort.setDisable(false);
        taforNewProfileName.setDisable(false);
        radioButtonCOM.setDisable(false);
        radioButtonTCP.setDisable(false);


    }

}

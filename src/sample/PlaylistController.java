package sample;

import com.google.gson.Gson;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PlaylistController {
    @FXML
    public Button endButton;
    @FXML
    public Button onedownButton;
    @FXML
    public Button oneupButton;
    @FXML
    public Button homeButton;
    @FXML
    public Button deletItemButton;
    @FXML
    public Button transferButton;
    @FXML
    public Button savePlaylistButton;
    @FXML
    public Button cancelPlaylistButton;
    @FXML
    public TableView<String> availableTestsTV;
    @FXML
    public TableColumn<String, String> availableTestscolumn;
    @FXML
    public TableView<Tests> currentPlaylistTV;
    @FXML
    public TableColumn<Tests, String> currentPlaylistcolumn;
    public ObservableList<String> testName = FXCollections.observableArrayList();


    public String currentProfileName;
    public ArrayList<Tests> availableTests;
    public String currentTestName;
    public LinkedList<String> currentPlaylist;

    public void setCurrentProfileName(String currentProfileName) {
        this.currentProfileName = currentProfileName;
    }


    @FXML
    private void endAction(ActionEvent actionEvent) {
    }

    @FXML
    private void onedownAction(ActionEvent actionEvent) {
    }

    @FXML
    private void homeAction(ActionEvent actionEvent) {
    }

    @FXML
    private void deleteItem(ActionEvent actionEvent) {
    }

    @FXML
    private void oneupAction(ActionEvent actionEvent) {
    }

    @FXML
    private void transferAction(ActionEvent actionEvent) {
    }

    @FXML
    private void savePlaylist(ActionEvent actionEvent) {
    }

    @FXML
    private void cancelPlaylist(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelPlaylistButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() throws FileNotFoundException {
        initDate();
        availableTestscolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> param) {
                return param.getValue();
            }
        });
       /* availableTestscolumn.setCellFactory(tc -> {
            TableCell<String, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(availableTestscolumn.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });*/

        availableTestsTV.setItems(testName);

    }

    @FXML
    private void initDate() throws FileNotFoundException {

        availableTests = readTests();
        System.out.println(currentProfileName);


        for (Tests tn : availableTests) {
            if (tn.profileName.equals(currentProfileName)) {
                for (Tests.TestList tslist : tn.testList) {
                    for (Tests.Test lt : tslist.listTests) {
                        testName.add(tslist.testSuit + "." + lt.testName);
                    }
                }
            }
        }

    }

    public ArrayList<Tests> readTests() throws FileNotFoundException {
        ArrayList<Tests> availableTests = new ArrayList<>();
        Gson gson = new Gson();
        Tests[] test = gson.fromJson(new FileReader("src\\sample\\Test1.json"), Tests[].class);
        List<Tests.TestList> tstlist = new ArrayList<>();
        List<Tests.Test> listtst = new ArrayList<>();
        List<Tests.Params> paramsList = new ArrayList<>();
        for (Tests ts : test) {
            for (Tests.TestList tslist : ts.testList) {
                for (Tests.Test lt : tslist.listTests) {
                    for (Tests.Params pr : lt.params) {
                        paramsList.add(pr);
                    }
                    listtst.add(lt);
                }
                tstlist.add(tslist);
            }
            availableTests.add(ts);
        }
        return availableTests;
    }
}

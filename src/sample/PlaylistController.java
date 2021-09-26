package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
    public TableView<String> currentPlaylistTV;
    @FXML
    public TableColumn<String, String> currentPlaylistcolumn;
    public ObservableList<String> testName = FXCollections.observableArrayList();

    public String currentProfileName;
    public ArrayList<Tests> availableTests;
    public String currentTestName;
    public ObservableList<String> testFromPlayList = FXCollections.observableArrayList();

    //
    public void setCurrentProfileName(String currentProfileName) {
        this.currentProfileName = currentProfileName;
    }

    //Move the test to the last position
    @FXML
    private void endAction(ActionEvent actionEvent) {
        currentTestName= currentPlaylistTV.getSelectionModel().getSelectedItem();
        int index = testFromPlayList.indexOf(currentTestName);
        testFromPlayList.set(testFromPlayList.size()+1,currentTestName);
        testFromPlayList.remove(index);

    }

    //Move the test one position down
    @FXML
    private void onedownAction(ActionEvent actionEvent) {
        currentTestName= currentPlaylistTV.getSelectionModel().getSelectedItem();
        int index = testFromPlayList.indexOf(currentTestName);
        Collections.swap(testFromPlayList, index+1, index);
    }

    //Move the test to the first position
    @FXML
    private void homeAction(ActionEvent actionEvent) {
        currentTestName= currentPlaylistTV.getSelectionModel().getSelectedItem();
        int index = testFromPlayList.indexOf(currentTestName);
        testFromPlayList.set(0,currentTestName);
        testFromPlayList.remove(index+1);
    }

    //Deleting the selected test
    @FXML
    private void deleteItem(ActionEvent actionEvent) {
        currentTestName= currentPlaylistTV.getSelectionModel().getSelectedItem();
        if (showConfirmation()) {
            for (String testPL: testFromPlayList) {
                if (testPL.equals(currentTestName)) {
                    testFromPlayList.remove(testPL);
                    break;
                }
            }
        }
    }

    //Move the test one position up
    @FXML
    private void oneupAction(ActionEvent actionEvent) {
        currentTestName= currentPlaylistTV.getSelectionModel().getSelectedItem();
        int index = testFromPlayList.indexOf(currentTestName);
        Collections.swap(testFromPlayList, index-1, index);
    }

    //Transfer of the selected test to the Play list
    @FXML
    private void transferAction(ActionEvent actionEvent) {
            String str = availableTestsTV.getSelectionModel().getSelectedItem();
            if (str != null) {
                testFromPlayList.add(str);
            }
    }

    //Call alert confirm deletion of the selected test
    private boolean showConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Profile");
        alert.setHeaderText("Are you sure that you want to move this test?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }

    //Saving the created Play list in new json
    @FXML
    private void savePlaylist(ActionEvent actionEvent) {
        ArrayList <Tests> inTests=new ArrayList<>();
        ArrayList<NewTestList> outTests = new ArrayList<>();
        if (!testFromPlayList.equals(" ")) {
            inTests= (ArrayList<Tests>) availableTests.stream().filter(currentProf->currentProf.profileName.equals(currentProfileName)).collect(Collectors.toList());
            System.out.println(inTests);
            for(String test: testFromPlayList){
                String[] testStr = test.split("\\.");
                for (Tests.TestList tl: inTests.get(0).testList) {
                    if (tl.testSuit.equals(testStr[0])) {
                        for (Tests.Test ts : tl.listTests) {
                            if (ts.testName.equals(testStr[1])) {
                                NewTestList ntest = new NewTestList(tl.testSuit, ts.testName, ts.params);
                                outTests.add(ntest);
                            }
                        }
                    }
                }
            }

        }
        NewTest result = new NewTest(currentProfileName, outTests);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.serializeNulls().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("src\\sample\\Currentplaylist.json")) {
            gson.toJson(result, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) savePlaylistButton.getScene().getWindow();
        stage.close();
    }

    //Closing a window with the Play list
    @FXML
    private void cancelPlaylist(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelPlaylistButton.getScene().getWindow();
        stage.close();
    }

    //Filling in the test table
    @FXML
    public void initialize() throws FileNotFoundException {
        initDate();
        availableTestscolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue());
            }
        });
        availableTestsTV.setItems(testName);
        currentPlaylistcolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue());
            }
        });
        currentPlaylistTV.setItems(testFromPlayList);
    }

    //Reading raw data from json file
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
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.serializeNulls().setPrettyPrinting().create();
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

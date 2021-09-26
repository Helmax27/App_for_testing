package sample;

import com.fazecast.jSerialComm.SerialPort;
import com.google.gson.Gson;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.pojo.MessageTable;
import sample.pojo.Testlist;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewsampleController implements Initializable {
    public Button previousButton = new Button();
    @FXML
    public BorderPane borderPane;
    @FXML
    public MenuBar menuBar;
    @FXML
    public Menu testItem;
    @FXML
    public MenuItem exitItem;
    @FXML
    public MenuItem startItem;
    @FXML
    public Menu viewItem;
    @FXML
    public MenuItem showLogItem;
    @FXML
    public MenuItem showDescriptionItem;
    @FXML
    public Menu settingItem;
    @FXML
    public MenuItem profilesItem;
    @FXML
    public MenuItem playlistItem;
    @FXML
    public MenuItem loopSettingItem;
    @FXML
    public Menu helpItem;
    @FXML
    public MenuItem aboutItem;
    @FXML
    public Button startButton;
    @FXML
    public Button stopButton;
    @FXML
    public AnchorPane anPanStop;
    @FXML
    public ImageView imStop;
    @FXML
    public Button updateButton;
    @FXML
    public Button viewButton;
    @FXML
    public TextField entryTextField;
    @FXML
    public Button editButton;
    @FXML
    public Label profileListLable;
    @FXML
    public Button profile1Button;
    @FXML
    public Button profile2Button;
    @FXML
    public Button profile3Button;
    @FXML
    public Button profile4Button;
    @FXML
    public Label chooseProfileLable;
    @FXML
    public Label statusLable;
    @FXML
    public Label loopLable;
    @FXML
    public TableView<Testlist> testTableView;
    @FXML
    public TableColumn<Testlist, CheckBox> selectTableColumn;
    @FXML
    public TableColumn<Testlist, String> nameTableColumn;
    @FXML
    public TableColumn<Testlist, Integer> durationTableColumn;
    @FXML
    public TableColumn<Testlist, Integer> passTableColumn;
    @FXML
    public TableColumn<Testlist, Integer> failTableColumn;
    @FXML
    public TableColumn<Testlist, String> parametersTableColumn;
    @FXML
    public TableView statusTableView;
    @FXML
    public TableColumn timeColumn;
    @FXML
    public TableColumn messageColumn;
    public ObservableList<Testlist> testlistsData = FXCollections.observableArrayList();
    public ObservableList<MessageTable> messageTableData = FXCollections.observableArrayList();
    public ArrayList<Profilesdetails> profiles;
    public String activeProfile;
    public ObservableList<String> profileNames = FXCollections.observableArrayList();
    @FXML
    public ListView buttonList;
    @FXML
    public CheckBox selectAll;
    public String activeProfileName;
    public Connect connection;

    public String getActiveProfileName() {
        return activeProfileName;
    }

    public void setActiveProfileName(String activeProfileName) {
        this.activeProfileName = activeProfileName;
    }

    @FXML
    private void onClick(ActionEvent actionEvent) {
        return;
    }

    @FXML
    private void exitAction(ActionEvent actionEvent) {
    }
    //Creating a list of profiles
    @FXML
    private void startAction(ActionEvent actionEvent) throws InterruptedException {
        int activePort;
        String conType;
        ArrayList<Testlist> selectedTests = new ArrayList<Testlist>();
        for (Profilesdetails pr : profiles) {
            if (pr.profileName.equals(activeProfile)) {
                conType = pr.connectionType;
                activePort = pr.port;

            }
        }
        ObservableList<Testlist> tabelItem = testTableView.getItems();
        for (Testlist ti : tabelItem) {
            System.out.println(ti.getSelect().isSelected());
            if (ti.getSelect().isSelected()) {
                selectedTests.add(ti);
            }
        }
        if (selectedTests.size() > 0) {

        }

    }

    @FXML
    private void showLogAction(ActionEvent actionEvent) {
    }

    @FXML
    private void showDescriptionAction(ActionEvent actionEvent) {
    }
    //Call Profiles window
    @FXML
    private void profileAction(ActionEvent actionEvent) throws IOException {
        MenuItem menuItem = (MenuItem) actionEvent.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profiles.fxml"));
        File prof = new File("src\\sample\\Profiles\\Existingprofiles.json");
        if (!prof.exists()) {
            Writer writer = Files.newBufferedWriter(Paths.get("src\\sample\\Profiles\\Existingprofiles.json"));
            writer.write("[]");
            writer.close();
        }
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Profiles");
        stage.setScene(new Scene(root1, 742, 462));
        stage.setOnHidden (event -> {
            try {
                initDate();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        stage.show();
    }
    //Call Play window
    @FXML
    private void playlistAction(ActionEvent actionEvent) {
        MenuItem menuItem = (MenuItem) actionEvent.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("playlist.fxml"));
        fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> controllerClass) {
                if (controllerClass == PlaylistController.class) {
                    PlaylistController controller = new PlaylistController();
                    controller.setCurrentProfileName(chooseProfileLable.getText());
                    return controller ;
                } else {
                    try {
                        return controllerClass.newInstance();
                    } catch (Exception exc) {
                        throw new RuntimeException(exc); // just bail
                    }
                }
            }
        });
        Parent root3 = null;
        try {
            root3 = (Parent) fxmlLoader.load();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Playlist");
        stage.setScene(new Scene(root3, 867, 576));
        stage.show();
    }
    //Call Loop window
    @FXML
    private void loopSettingaction(ActionEvent actionEvent) {
        MenuItem menuItem = (MenuItem) actionEvent.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loopSettings.fxml"));
        Parent root2 = null;
        try {
            root2 = (Parent) fxmlLoader.load();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Stage stage = new Stage();
        LoopSettingsController controller = fxmlLoader.getController();
        controller.setl(loopLable);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Loops");
        stage.setScene(new Scene(root2, 450, 248));
        stage.show();
    }

    @FXML
    private void helpAction(ActionEvent actionEvent) {
    }

    @FXML
    private void aboutAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initDate();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<Testlist, String>("name"));
        nameTableColumn.setCellFactory(tc -> {
            TableCell<Testlist, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(nameTableColumn.widthProperty().subtract(25));
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        // Defines how to fill data for each cell in Test table
        selectTableColumn.setCellValueFactory(new PropertyValueFactory<Testlist, CheckBox>("select"));
        durationTableColumn.setCellValueFactory(new PropertyValueFactory<Testlist, Integer>("duration"));
        passTableColumn.setCellValueFactory(new PropertyValueFactory<Testlist, Integer>("pass"));
        failTableColumn.setCellValueFactory(new PropertyValueFactory<Testlist, Integer>("fail"));
        parametersTableColumn.setCellValueFactory(new PropertyValueFactory<Testlist, String>("parameters"));
        parametersTableColumn.setCellFactory(tc -> {
            TableCell<Testlist, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(parametersTableColumn.widthProperty().subtract(25));
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        //Selection of elements in Test table
        selectAll.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                ObservableList<Testlist> tabelItem = testTableView.getItems();
                for (Testlist ti : tabelItem) {
                    if (selectAll.isSelected()) {
                        ti.getSelect().setSelected(true);
                    } else
                        ti.getSelect().setSelected(false);
                }
            }
        });
        testTableView.setItems(testlistsData);

        // Defines how to fill data for each cell in Status table
        timeColumn.setCellValueFactory(new PropertyValueFactory<MessageTable, String>("time"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<MessageTable, String>("message"));
        statusTableView.setItems(messageTableData);

    }
    //Parsing a json file with Profile details
    public ArrayList<Profilesdetails> readProfiles() throws FileNotFoundException {
        ArrayList<Profilesdetails> profiles = new ArrayList<>();
        Gson gson = new Gson();
        Profilesdetails[] profilesdetails = gson.fromJson(new FileReader("src\\sample\\Profiles\\Existingprofiles.json"), Profilesdetails[].class);
        for (Profilesdetails pr : profilesdetails) {
            profiles.add(pr);
        }
        return profiles;
    }

    private void initDate() throws FileNotFoundException {
        Gson gson =new Gson();
        NewTest testlist= gson.fromJson(new FileReader("src\\sample\\Currentplaylist.json"), NewTest.class);
        for(NewTestList tl: testlist.testList){
                testlistsData.add(new Testlist(tl.testSuit+"."+tl.testName, 0, 0, 0, tl.params.toString().substring(1, tl.params.toString().length()-1)));
            }

        messageTableData.add(new MessageTable("08.03.02.147", "initialized"));
        messageTableData.add(new MessageTable("08.06.38.329", "started"));
        messageTableData.add(new MessageTable("08.06.38.341", "reading"));
        messageTableData.add(new MessageTable("08.06.38.342", "finished"));

        try {
            profiles = readProfiles();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        profileNames.removeAll();
        buttonList.getItems().clear();

        for (Profilesdetails pr : profiles) {
            profileNames.add(pr.profileName);
        }
        //Generating buttons of Profile List
        buttonList.setItems(profileNames);
        buttonList.setCellFactory(param -> new XCell());

        //Enabling active profile label
        chooseProfileLable.setText(profileNames.get(0));
        System.out.println("_____________________________");
    }

    //Generating profile buttons
    public void setLabelText(String text) {
        chooseProfileLable.setText(text);
        activeProfile = text;
    }

    //Editing test parameters
    @FXML
    private void editAction(ActionEvent actionEvent) {
        parametersTableColumn.setOnEditCommit((TableColumn.CellEditEvent<Testlist, String> event) -> {
            TablePosition<Testlist, String> pos = event.getTablePosition();
            String newParameters = event.getNewValue();
            int row = pos.getRow();
            Testlist tsl = event.getTableView().getItems().get(row);
            tsl.setParameters(newParameters);
        });
    }

    class XCell extends ListCell<String> {
        Button button = new Button();

        public XCell() {
            super();
                button.setOnMouseClicked(event -> {
                        NewsampleController.this.setLabelText(button.getText());
                        button.setStyle("-fx-background-color: #18418E; -fx-text-fill: #FFFFFF");
                        if (!previousButton.equals(button)) {
                            previousButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000");
                            previousButton = button;
                        }
                        if (profiles.get(0).connectionType.equals("COM")) {
                            SerialPort sr= SerialPort.getCommPort("COM"+profiles.get(0).port);
                            sr.openPort(0);
                            connection = new Connect(sr);
                            connection.start();
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("connection.fxml"));
                            Parent root4 = null;
                            try {
                                root4 = (Parent) fxmlLoader.load();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            Stage stage = new Stage();
                            ConnectionController controller = fxmlLoader.getController();
                            controller.setPort(sr);
                            controller.setConnect(connection);
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setTitle("Connection");
                            stage.setScene(new Scene(root4, 400, 233));
                            stage.show();
                        }
                });
        }


        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);
            if (item != null && !empty) {
                button.setPrefSize(166, 86);

                button.setMinSize(100, Control.USE_COMPUTED_SIZE);
                if (profileNames.get(0) == item) {
                    //button.setStyle("-fx-background-color: #18418E; -fx-text-fill: #FFFFFF");
                    button.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000");
                    previousButton = button;
                }
                button.setText(item);
                setGraphic(button);
            }
        }

    }

}

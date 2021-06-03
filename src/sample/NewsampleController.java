package sample;

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
        /*if (selectTableColumn != null) {
            Thread.sleep(1000);
            if (ProfilesController.class.getP)
            String command = commandsMap.get(selectTableColumn);
            byte[] byteArray = command.getBytes();
            currentport.writeBytes(byteArray, byteArray.length);
            Thread.sleep(1000);
            String answer = connection.answer;
            JsonReader reader = new JsonReader(new StringReader(answer));
            reader.setLenient(true);
            Answer jsonAnswer = new Gson().fromJson(answer, Answer.class);
            List<Lists> ls = new ArrayList<>();
            List<Logs> lg = new ArrayList<>();
            List<Application.Parameters> parametersList = new ArrayList<>();
            if (jsonAnswer != null){
                if (jsonAnswer.list != null) {
                    for (Lists lst : jsonAnswer.list) {
                        ls.add(lst);
                        if (lst.parameters != null) {
                            for (Parameters pr : lst.parameters) {
                                parametersList.add(pr);
                            }
                        }
                    }
                }
                if (jsonAnswer.logs != null) {
                    for (Logs log : jsonAnswer.logs) {
                        lg.add(log);
                    }
                }
            }
        }*/
    }

    @FXML
    private void showLogAction(ActionEvent actionEvent) {
    }

    @FXML
    private void showDescriptionAction(ActionEvent actionEvent) {
    }

    @FXML
    private void profileAction(ActionEvent actionEvent) throws IOException {
        MenuItem menuItem = (MenuItem) actionEvent.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profiles.fxml"));
        File prof = new File("C:\\Users\\helen\\IdeaProjects\\App for testing\\src\\sample\\Profiles\\Existingprofiles.json");
        if (!prof.exists()) {
            Writer writer = Files.newBufferedWriter(Paths.get("C:\\Users\\helen\\IdeaProjects\\App for testing\\src\\sample\\Profiles\\Existingprofiles.json"));
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
        stage.show();
    }

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
        initDate();
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
        timeColumn.setCellValueFactory(new PropertyValueFactory<MessageTable, String>("time"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<MessageTable, String>("message"));
        statusTableView.setItems(messageTableData);

        try {
            profiles = readProfiles();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Profilesdetails pr : profiles) {
            profileNames.add(pr.profileName);
        }
        buttonList.setItems(profileNames);
        buttonList.setCellFactory(param -> new XCell());
        chooseProfileLable.setText(profileNames.get(0));
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

    private void initDate() {
        testlistsData.add(new Testlist("TestSuite1.testcase1", 10, 0, 1, "param1=data1, param2=data2, param3=data3, param4=data4, param5=data5"));
        testlistsData.add(new Testlist("TestSuite2.testcase2.TestSuite2.testcase2.TestSuite2.testcase2.", 100, 1, 0, "param1=data1"));
        testlistsData.add(new Testlist("TestSuite3.testcase3", 10, 1, 0, "param1=data1, param2=data2, param3=data3, param4=data4, ,param5=data5, param6=data6"));

        messageTableData.add(new MessageTable("08.03.02.147", "initialized"));
        messageTableData.add(new MessageTable("08.06.38.329", "started"));
        messageTableData.add(new MessageTable("08.06.38.341", "reading"));
        messageTableData.add(new MessageTable("08.06.38.342", "finished"));
    }

    public void setLabelText(String text) {
        chooseProfileLable.setText(text);
        activeProfile = text;
    }

    class XCell extends ListCell<String> {
        Button button = new Button();

        public XCell() {
            super();
            button.setOnMouseClicked(event -> {

                NewsampleController.this.setLabelText(button.getText());
                button.setStyle("-fx-background-color: #18418E; -fx-text-fill: #FFFFFF");
                previousButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000");
                previousButton = button;
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
                    button.setStyle("-fx-background-color: #18418E; -fx-text-fill: #FFFFFF");
                    previousButton = button;
                }
                button.setText(item);
                setGraphic(button);
            }
        }
    }
}

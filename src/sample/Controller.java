package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public VBox mainBox;
    @FXML
    public AnchorPane stage;
    @FXML
    public Menu testButton;
    @FXML
    public MenuItem exitItem;
    @FXML
    public MenuItem startItem;
    @FXML
    public Menu viewButton;
    @FXML
    public MenuItem showLogItem;
    @FXML
    public MenuItem showDescriptionItem;
    @FXML
    public Menu settingButton;
    @FXML
    public MenuItem profilesItem;
    @FXML
    public MenuItem parametersItem;
    @FXML
    public MenuItem loopSettingsItem;
    @FXML
    public Menu helpButton;
    @FXML
    public MenuItem aboutItem;
    @FXML
    public VBox textBox;
    @FXML
    public TextArea outputtab;
    @FXML
    public HBox tabtitle;
    @FXML
    public Label timelable;
    @FXML
    public Label messagelabel;
    @FXML
    public Button start;
    @FXML
    public AnchorPane anPanStart;
    @FXML
    public ImageView imStart;
    @FXML
    public Button stop;
    @FXML
    public AnchorPane anPanStop;
    @FXML
    public ImageView imStop;
    @FXML
    public Button view;
    @FXML
    public AnchorPane anPanView;
    @FXML
    public ImageView imView;
    @FXML
    public Button update;
    @FXML
    public AnchorPane anPanUpdate;
    @FXML
    public ImageView imUpdate;
    @FXML
    public Button edit;
    @FXML
    public AnchorPane anPanEdit;
    @FXML
    public ImageView imEdit;
    @FXML
    public TextField textField;
    @FXML
    public Label labelProfile;
    @FXML
    public Label profileState;
    @FXML
    public Label runState;
    @FXML
    public Label loop;
    @FXML
    public Label loopNumber;
    @FXML
    public Label profileList;
    @FXML
    public Button profile1;
    @FXML
    public Button profile2;
    @FXML
    public Button profile3;
    @FXML
    public Button profile4;
    @FXML
    public Label testList;
    @FXML
    public TableView tabTests;
    @FXML
    public TableColumn nameField;
    @FXML
    public TableColumn durationField;
    @FXML
    public TableColumn passField;
    @FXML
    public TableColumn failField;
    @FXML
    public TableColumn parametersField;

    public void onClick () {
        System.out.println();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameField.setCellValueFactory(new PropertyValueFactory<String, String>("12345"));
    }
}

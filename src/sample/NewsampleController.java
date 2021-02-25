package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import sample.pojo.MessageTable;
import sample.pojo.Testlist;

import java.net.URL;
import java.util.ResourceBundle;

public class NewsampleController implements Initializable {
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
    public MenuItem parametersItem;
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

    @FXML
    private void onClick(ActionEvent actionEvent) {
    }

    @FXML
    private void exitAction(ActionEvent actionEvent) {
    }

    @FXML
    private void startAction(ActionEvent actionEvent) {
    }

    @FXML
    private void showLogAction(ActionEvent actionEvent) {
    }

    @FXML
    private void showDescriptionAction(ActionEvent actionEvent) {
    }

    @FXML
    private void profileAction(ActionEvent actionEvent) {
    }

    @FXML
    private void parametersAction(ActionEvent actionEvent) {
    }

    @FXML
    private void loopSettingaction(ActionEvent actionEvent) {
    }

    @FXML
    private void helpAction(ActionEvent actionEvent) {
    }

    @FXML
    private void aboutAction(ActionEvent actionEvent) {
    }
    public ObservableList<Testlist> testlistsData= FXCollections.observableArrayList();
    public ObservableList<MessageTable> messageTableData =FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initDate();
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<Testlist, String >("name"));
        nameTableColumn.setCellFactory(tc -> {
            TableCell<Testlist, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(nameTableColumn.widthProperty().subtract(25));
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });

        durationTableColumn.setCellValueFactory(new PropertyValueFactory<Testlist, Integer>("duration"));
        passTableColumn.setCellValueFactory(new PropertyValueFactory<Testlist, Integer>("pass"));
        failTableColumn.setCellValueFactory(new PropertyValueFactory<Testlist, Integer>("fail"));
        parametersTableColumn.setCellValueFactory(new PropertyValueFactory<Testlist, String>("parameters"));
        parametersTableColumn.setCellFactory(tc -> {
            TableCell<Testlist, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(nameTableColumn.widthProperty().subtract(25));
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });

        testTableView.setItems(testlistsData);

        timeColumn.setCellValueFactory(new PropertyValueFactory<MessageTable, String>("time"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<MessageTable, String>("message"));

        statusTableView.setItems(messageTableData);


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

}

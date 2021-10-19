package sample;

import com.fazecast.jSerialComm.SerialPort;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;


public class ConnectionController {
    @FXML
    public ProgressBar connectionProgressBar;
    @FXML
    public Button buttonCancelConnection;
    @FXML
    public Button buttonStartlConnection;

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String pn;

    public void setConnectLabel(Label connectLabel) {
        this.connectLabel = connectLabel;
    }

    @FXML
    public Label connectLabel;
    public Connect connect;
    public SerialPort port;
    private HashMap<String, String> commandsMap;



    public void setPort(SerialPort port) {
        this.port = port;
    }
    public void setConnect(Connect connect) {
        this.connect=connect;
    }

    @FXML
    private void onCancelConnection(ActionEvent actionEvent) {
        buttonStartlConnection.setDisable(false);
        buttonCancelConnection.setDisable(true);
        connectionProgressBar.progressProperty().unbind();
        connectionProgressBar.setProgress(0);
        Stage stage = (Stage) buttonCancelConnection.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onStartConnection(ActionEvent actionEvent) throws InterruptedException {
        buttonStartlConnection.setDisable(true);
        connectionProgressBar.setProgress(0);
        buttonCancelConnection.setDisable(false);
        //Sending commands to connect
        new Thread(() -> {
            double i = 0;
            for (String value : commandsMap.values()) {

                try {
                    Thread.sleep(1100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String command = value;
                System.out.println(command);
                byte[] byteArray = command.getBytes();
                port.writeBytes(byteArray, byteArray.length);
                i += 0.25;
                connectionProgressBar.setProgress(i);
                try {
                    Thread.sleep(1100);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                String answer = connect.answer;
                JsonReader reader = new JsonReader(new StringReader(answer));
                reader.setLenient(true);
                //Receiving and recording tests
                if (answer.contains("get_test_suites")) {
                    answer = "{\"answer\" :\"get_test_suites\",\"status\":\"pass\",\"list\": [{\"test_name\":\"testSuite1\"}, {\"test_name\":\"testSuite2\"}]}";
                }
                if (answer.contains("get_test_list")) {
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.setPrettyPrinting().serializeNulls().create();
                    answer = answer.replace("\n", "").replace("\r","");
                    JsonObject ans = gson.fromJson (answer, JsonElement.class).getAsJsonObject();
                    ans.remove("answer");
                    ans.remove("status");
                    JsonElement listE = ans.get("list");
                    ans.remove("list");
                    ans.addProperty("profileName", pn);
                    ans.add("list", listE);
                    JsonArray list = (JsonArray) ans.get("list");
                    for (JsonElement lt: list) {
                        lt.getAsJsonObject().remove("test_description");
                        lt.getAsJsonObject().add("testSuit", ans.get("suite_name"));
                    }
                    ans.remove("suite_name");
                    try (FileWriter writer = new FileWriter("src\\sample\\Getanswer.json")) {
                        gson.toJson(ans, writer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Platform.runLater(() ->{
                connectLabel.setText("Done");
            });
        }).start();
        connectLabel.setText("In progress");
    }

    @FXML
    public void initialize() {
        initDate();
    }

    @FXML
    private void initDate() {
        commandsMap = new HashMap<String, String>() {{
            put("connection data", "{\"command\" : \"connection_data\",\"server_ip\":\"127.0.0.1\",\"server_port\":\"8002\"}");
            put("get test suites", "{\"command\" : \"get_test_suites\"}");
            put("get test list", "{\"command\" : \"get_test_list\",\"suite_name\" : \"testSuite\"}");
            put("set global param", "{\"command\" : \"set_global_param\",\"param\" : [\"param_name\":\"log_level\", \"param_value\":\"debug\"]}");
        }};
    }
}


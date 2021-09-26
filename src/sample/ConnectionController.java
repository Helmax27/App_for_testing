package sample;

import com.fazecast.jSerialComm.SerialPort;
import com.google.gson.stream.JsonReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.StringReader;
import java.util.HashMap;

public class ConnectionController {
    @FXML
    public ProgressBar connectionProgressBar;
    @FXML
    public Button buttonCancelConnection;
    @FXML
    public Button buttonStartlConnection;
    private HashMap<String, String> commandsMap;

    public void setConnect(Connect connect) {
        this.connect = connect;
    }

    public Connect connect;

    public void setPort(SerialPort port) {
        this.port = port;
    }

    public SerialPort port;


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
        double i=0;
        connectionProgressBar.setProgress(i);

        buttonCancelConnection.setDisable(false);
        for (String value: commandsMap.values()) {
            Thread.sleep(1000);
            String command=value;
            System.out.println(command);
            byte [] byteArray= command.getBytes();
            port.writeBytes(byteArray, byteArray.length);
            i+=0.25;
            connectionProgressBar.setProgress(i);
            Thread.sleep(1000);
            String answer=connect.answer;
            //JsonReader reader = new JsonReader(new StringReader(answer));
            //reader.setLenient(true);

        }

    }
    @FXML
    public void initialize()  {
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


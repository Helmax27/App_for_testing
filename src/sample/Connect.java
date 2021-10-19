package sample;

import com.fazecast.jSerialComm.SerialPort;
import javafx.scene.control.Alert;

import java.nio.charset.StandardCharsets;

class Connect extends Thread {
    SerialPort serialPort;
    String answer;

    public Connect(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    private void showAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void run() {
        while (true) {
            while (serialPort.bytesAvailable() == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                byte[] inputButes = new byte[serialPort.bytesAvailable()];
                serialPort.readBytes(inputButes, inputButes.length);
                String s = new String(inputButes, StandardCharsets.UTF_8);
                answer = s + "\n";
                System.out.println(" message: " + s);
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Warning alert", "There is no connection to Serial port");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package sample;

import com.fazecast.jSerialComm.SerialPort;
import java.nio.charset.StandardCharsets;

class Connect extends Thread {
    SerialPort serialPort;
    String answer;

    public Connect(SerialPort serialPort) {
        this.serialPort = serialPort;
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
            byte[] inputButes = new byte[serialPort.bytesAvailable()];
            serialPort.readBytes(inputButes, inputButes.length);
            String s = new String(inputButes, StandardCharsets.UTF_8);
            answer = s + "\n";
            System.out.println(" message: " + s);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

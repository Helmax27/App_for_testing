package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("lastsample.fxml"));
        primaryStage.setTitle("Dm5 Tester");
        Scene scene = new Scene(root, 1500, 800);
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.setMaximized(true);
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(700);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

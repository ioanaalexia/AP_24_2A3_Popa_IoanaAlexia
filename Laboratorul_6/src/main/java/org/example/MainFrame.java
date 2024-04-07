package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFrame extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        ConfigPanel configPanel = new ConfigPanel();
        DrawingPanel drawingPanel = new DrawingPanel(configPanel, 400, 400, 10, 10);
        ControlPanel controlPanel = new ControlPanel(drawingPanel);

        root.setTop(configPanel);
        root.setCenter(drawingPanel);
        root.setBottom(controlPanel);

        Scene scene = new Scene(root, 450, 550);

        primaryStage.setTitle("My Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

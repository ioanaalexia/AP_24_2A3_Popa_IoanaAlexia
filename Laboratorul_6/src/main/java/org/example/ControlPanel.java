package org.example;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


import javafx.geometry.Insets;

public class ControlPanel extends HBox {
    private Button saveButton;
    private Button loadButton;
    private Button exitButton;
    private Button saveGameStateButton;

    private DrawingPanel drawingPanel;

    public ControlPanel(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
        setPadding(new Insets(10));
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setSpacing(10);

        saveButton = new Button("Save");
        loadButton = new Button("Load");
        saveGameStateButton = new Button("Save State");
        exitButton = new Button("Exit");


        exitButton.setOnAction(event -> System.exit(0));
        saveButton.setOnAction(event -> drawingPanel.saveGame());
        loadButton.setOnAction(event -> drawingPanel.loadGame());
        saveGameStateButton.setOnAction(event -> drawingPanel.saveGameState());

        getChildren().addAll(saveButton, loadButton, saveGameStateButton, exitButton);
    }
}

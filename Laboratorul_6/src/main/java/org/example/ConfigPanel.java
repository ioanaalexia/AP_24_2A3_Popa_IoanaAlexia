package org.example;

import javafx.geometry.Insets;
import javafx.scene.control.Button;

import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;

public class ConfigPanel extends HBox {
    private Spinner<Integer> rowsSpinner;
    private Spinner<Integer> colsSpinner;
    private Button createButton;

    public ConfigPanel() {
        setPadding(new Insets(10));
        setSpacing(10);

        rowsSpinner = new Spinner<>(3, 20, 10);
        colsSpinner = new Spinner<>(3, 20, 10);
        createButton = new Button("Create");

        getChildren().addAll(rowsSpinner, colsSpinner, createButton);

    }

    public Spinner<Integer> getRowsSpinner() {
        return rowsSpinner;
    }

    public Spinner<Integer> getColsSpinner() {
        return colsSpinner;
    }

    public Button getCreateButton() {
        return createButton;
    }
}
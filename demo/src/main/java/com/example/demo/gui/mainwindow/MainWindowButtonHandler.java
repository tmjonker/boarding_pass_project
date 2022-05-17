package com.example.demo.gui.mainwindow;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class MainWindowButtonHandler {

    private GridPane gridPane;

    private Button submitButton;
    private Button resetButton;

    public MainWindowButtonHandler(GridPane gridPane) {

        this.gridPane = gridPane;
    }

    public void generateButtons() {

        submitButton = new Button("Submit");

        resetButton = new Button("Reset");

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(15, 0, 0, 0));
        buttonBox.getChildren().addAll(submitButton, resetButton);

        gridPane.add(buttonBox, 1, 10);
    }

    public Button getResetButton() {
        return resetButton;
    }

    public Button getSubmitButton() {
        return submitButton;
    }
}

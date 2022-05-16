package com.example.demo;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainWindow {

    private final Stage stage;
    private GridPane gridPane;

    public MainWindow(Stage stage) {

        this.stage = stage;

        generateWindow();
    }

    private void generateWindow() {

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Text titleText = new Text("Create Boarding Pass");
        gridPane.add(titleText, 0, 0, 2, 1);

        Label fullNameLabel = new Label("Full Name: ");
        gridPane.add(fullNameLabel, 0, 1);

        TextField fullNameField = new TextField();
        gridPane.add(fullNameField, 1, 1);

        Label emailAddressLabel = new Label("Email Address:");
        gridPane.add(emailAddressLabel, 0, 2);

        TextField emailAddressField = new TextField();
        gridPane.add(emailAddressField, 1, 2);

        Label phoneNumberLabel = new Label("Phone Number:");
        gridPane.add(phoneNumberLabel, 0, 3);

        TextField phoneNumberField = new TextField();
        gridPane.add(phoneNumberField, 1, 3);

        Label genderLabel = new Label("Gender: ");
        gridPane.add(genderLabel, 0, 4);

        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll(
                "Male",
                "Female",
                "Intersex"
        );
        gridPane.add(genderBox, 1, 4);

        Label birthDateLabel = new Label("Birth Date: ");
        gridPane.add(birthDateLabel, 0, 5);

        DatePicker birthDatePicker = new DatePicker();
        birthDatePicker.setOnAction(e -> {
            getBirthDate();
        });
        gridPane.add(birthDatePicker, 1, 5);

        Label travelDateLabel = new Label("Travel Date: ");
        gridPane.add(travelDateLabel, 0, 6);

        DatePicker travelDatePicker = new DatePicker();
        travelDatePicker.setOnAction(e -> {
            getTravelDate();
        });
        gridPane.add(travelDatePicker, 1, 6);

        Label destinationLabel = new Label("Destination: ");
        gridPane.add(destinationLabel, 0, 7);

        ComboBox<String> destinationBox = new ComboBox<>();
        destinationBox.getItems().addAll(
                "ATL",
                "DFW",
                "DEN",
                "ORD",
                "LAX",
                "CLT",
                "LAS",
                "PHX",
                "MCO"
        );
        gridPane.add(destinationBox, 1, 7);

        Label departureTimeLabel = new Label("Departure Time: ");
        gridPane.add(departureTimeLabel, 0, 8);

        ComboBox<String> departureTimeBox = new ComboBox<>();
        departureTimeBox.setItems(FXCollections.observableArrayList(DepartureTimes.generateDepartureTimes()));
        gridPane.add(departureTimeBox, 1, 8);

        Button submitButton = new Button("Submit");

        Button resetButton = new Button("Reset");

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(submitButton, resetButton);
        gridPane.add(buttonBox, 1, 9);

        Scene scene = new Scene(gridPane, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    private String getBirthDate() {

        return null;
    }

    private String getTravelDate() {

        return null;
    }
}

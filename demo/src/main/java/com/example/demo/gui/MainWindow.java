package com.example.demo.gui;

import com.example.demo.alertgenerator.AlertGenerator;
import com.example.demo.departuretimes.DepartureTimes;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Period;

public class MainWindow {

    private final Stage stage;
    private GridPane gridPane;

    private TextField fullNameField;
    private TextField emailAddressField;
    private TextField phoneNumberField;
    private ComboBox<String> genderBox;
    private DatePicker birthDatePicker;
    private DatePicker travelDatePicker;
    private ComboBox<String> destinationBox;
    private ComboBox<String> departureTimeBox;

    private int age;
    private String departureDate;

    public MainWindow(Stage stage) {

        this.stage = stage;

        generateWindow();
    }

    private void generateWindow() {

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));

        Image image = new Image("airplane.png");
        ImageView imageView = new ImageView(image);

        gridPane.add(imageView, 0, 0);

        Text titleText = new Text("Create Boarding Pass");
        titleText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
        gridPane.add(titleText, 1, 0, 2, 1);

        Label fullNameLabel = new Label("Full Name: ");
        gridPane.add(fullNameLabel, 0, 1);

        fullNameField = new TextField();
        gridPane.add(fullNameField, 1, 1);

        Label emailAddressLabel = new Label("Email Address:");
        gridPane.add(emailAddressLabel, 0, 2);

        emailAddressField = new TextField();
        gridPane.add(emailAddressField, 1, 2);

        Label phoneNumberLabel = new Label("Phone Number:");
        gridPane.add(phoneNumberLabel, 0, 3);

        phoneNumberField = new TextField();
        gridPane.add(phoneNumberField, 1, 3);

        Label genderLabel = new Label("Gender: ");
        gridPane.add(genderLabel, 0, 4);

        genderBox = new ComboBox<>();
        genderBox.getItems().addAll(
                "Male",
                "Female",
                "Intersex"
        );
        gridPane.add(genderBox, 1, 4);

        Label birthDateLabel = new Label("Birth Date: ");
        gridPane.add(birthDateLabel, 0, 5);

        birthDatePicker = new DatePicker();
        birthDatePicker.setOnAction(e -> {
            getAge(); // assigns age value to age variable.
        });
        // Prevents user from selecting a future date for their birthdate.
        birthDatePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) > 0);
            }
        });
        gridPane.add(birthDatePicker, 1, 5);

        Label travelDateLabel = new Label("Departure Date: ");
        gridPane.add(travelDateLabel, 0, 6);

        travelDatePicker = new DatePicker();
        travelDatePicker.setOnAction(e -> {
            getTravelDate(); // assigns departure date to departureDate variable in 'YYYY-MM-DD' format.
        });
        // Prevents user from selecting a past date for their departure date.
        travelDatePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0);
            }
        });
        gridPane.add(travelDatePicker, 1, 6);

        Label destinationLabel = new Label("Destination: ");
        gridPane.add(destinationLabel, 0, 7);

        destinationBox = new ComboBox<>();
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

        departureTimeBox = new ComboBox<>();
        departureTimeBox.setItems(FXCollections.observableArrayList(DepartureTimes.generateDepartureTimes()));
        gridPane.add(departureTimeBox, 1, 8);

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> onSubmitClick());

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> onResetClick());

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(15, 0, 0, 0));
        buttonBox.getChildren().addAll(submitButton, resetButton);
        gridPane.add(buttonBox, 1, 9);

        Scene scene = new Scene(gridPane, 450, 500);
        stage.setScene(scene);
        stage.setTitle("Create Boarding Pass");
        stage.setResizable(false);
        stage.show();
    }

    private void getAge() {

        LocalDate currentDate = LocalDate.now();
        LocalDate birthdate = birthDatePicker.getValue();

        age = Period.between(birthdate, currentDate).getYears();
    }

    private void getTravelDate() {

        departureDate = travelDatePicker.getValue().toString();
        System.out.println(departureDate);
    }

    private void onSubmitClick() {

        if (fullNameField.getText().equals("")) {
            AlertGenerator.generateAlert("Missing 'Full Name'", "The 'Full Name' field needs to be filled in");
        } else if (emailAddressField.getText().equals("")) {
            AlertGenerator.generateAlert("Missing 'Email Address'", "The 'Email Address' field needs to be filled in");
        } else if (phoneNumberField.getText().equals("")) {
            AlertGenerator.generateAlert("Missing 'Phone Number'", "The 'Phone Number' field needs to be filled in");
        } else if (birthDatePicker.getValue() == null) {
            AlertGenerator.generateAlert("Missing 'Birth Date'", "The 'Birth Date' field needs to be filled in");
        } else if (travelDatePicker.getValue() == null) {
            AlertGenerator.generateAlert("Missing 'Departure Date'", "The 'Departure Date' field needs to be filled in");
        } else if (departureTimeBox.getValue() == null) {
            AlertGenerator.generateAlert("Missing 'Departure Time'", "The 'Departure Time' field needs to be filled in");
        } else if (destinationBox.getValue() == null) {
            AlertGenerator.generateAlert("Missing 'Destination'", "The 'Destination' field needs to be filled in");
        } else if (genderBox.getValue() == null) {
            AlertGenerator.generateAlert("Missing 'Gender'", "The 'Gender' field needs to be filled in");
        }


        // process data and generate boarding pass.
    }

    private void onResetClick() {

        fullNameField.setText("");
        emailAddressField.setText("");
        phoneNumberField.setText("");
        birthDatePicker.setValue(null);
        travelDatePicker.setValue(null);
        departureTimeBox.setValue("");
        destinationBox.setValue("");
        genderBox.setValue("");
    }
}

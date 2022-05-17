package com.example.demo.gui;

import com.example.demo.alertgenerator.AlertGenerator;
import com.example.demo.departuretimes.DepartureTimes;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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
        generateFields();
        generateButtons();
        showStage();
    }

    private void generateWindow() {

        gridPane = new GridPane();
        gridPane.setOpacity(0.85);
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
    }

    private void generateFields() {

        Label fullNameLabel = new Label("Full Name: ");
        fullNameLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        gridPane.add(fullNameLabel, 0, 1);

        fullNameField = new TextField();
        gridPane.add(fullNameField, 1, 1);

        Label emailAddressLabel = new Label("Email Address:");
        emailAddressLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        gridPane.add(emailAddressLabel, 0, 2);

        emailAddressField = new TextField();
        gridPane.add(emailAddressField, 1, 2);

        Label phoneNumberLabel = new Label("Phone Number:");
        phoneNumberLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        gridPane.add(phoneNumberLabel, 0, 3);

        phoneNumberField = new TextField();
        gridPane.add(phoneNumberField, 1, 3);

        Label genderLabel = new Label("Gender: ");
        genderLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        gridPane.add(genderLabel, 0, 4);

        genderBox = new ComboBox<>();
        genderBox.getItems().addAll(
                "Male",
                "Female",
                "Intersex"
        );
        gridPane.add(genderBox, 1, 4);

        Label birthDateLabel = new Label("Birth Date: ");
        birthDateLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
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
        travelDateLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
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
        destinationLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
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
        departureTimeLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        gridPane.add(departureTimeLabel, 0, 8);

        departureTimeBox = new ComboBox<>();
        // Generates a list of random departure times.
        departureTimeBox.setItems(FXCollections.observableArrayList(DepartureTimes.generateDepartureTimes()));
        gridPane.add(departureTimeBox, 1, 8);
    }

    private void generateButtons() {

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> onSubmitClick());

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> onResetClick());

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(15, 0, 0, 0));
        buttonBox.getChildren().addAll(submitButton, resetButton);

        gridPane.add(buttonBox, 1, 9);
    }

    private void showStage() {

        Scene scene = new Scene(gridPane, 450, 500);
        Image planeBackground = new Image("airplane.jpg");
        ImagePattern pattern = new ImagePattern(planeBackground);
        scene.setFill(pattern);
        scene.getStylesheets().add("style.css");

        stage.setScene(scene);
        stage.setTitle("Create Boarding Pass");
        stage.getIcons().add(new Image("icon1.png"));
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

    // Processes data that is entered by the user and generates the boarding pass.
    private void onSubmitClick() {

        validateUserEntry(); // checks form for missing entries and alerts user if any field is not filled in.

        // process data and generate boarding pass.
    }

    private void validateUserEntry() {

        if (fullNameField.getText().equals("")) {
            AlertGenerator.generateAlert("Full Name");
        } else if (emailAddressField.getText().equals("")) {
            AlertGenerator.generateAlert("Email Address");
        } else if (phoneNumberField.getText().equals("")) {
            AlertGenerator.generateAlert("Phone Number");
        } else if (birthDatePicker.getValue() == null) {
            AlertGenerator.generateAlert("Birth Date");
        } else if (travelDatePicker.getValue() == null) {
            AlertGenerator.generateAlert("Departure Date");
        } else if (departureTimeBox.getValue() == null) {
            AlertGenerator.generateAlert("Departure Time");
        } else if (destinationBox.getValue() == null) {
            AlertGenerator.generateAlert("Destination");
        } else if (genderBox.getValue() == null) {
            AlertGenerator.generateAlert("Gender");
        }
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

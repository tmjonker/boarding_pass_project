package com.example.demo.gui.mainwindow;

import com.example.demo.alertgenerator.AlertGenerator;
import com.example.demo.departuretimes.DepartureTimes;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;
import java.time.Period;

public class MainWindowFieldHandler {

    public GridPane gridPane;

    private TextField fullNameField;
    private TextField emailAddressField;
    private TextField phoneNumberField;
    private ComboBox<String> genderBox;
    private DatePicker birthDatePicker;
    private DatePicker travelDatePicker;
    private ComboBox<String> destinationBox;
    private ComboBox<String> originBox;
    private ComboBox<String> departureTimeBox;

    private int age;
    private String departureDate;

    public MainWindowFieldHandler(GridPane gridPane) {

        this.gridPane = gridPane;
    }

    public void generateFields() {

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
                "Female"
        );
        gridPane.add(genderBox, 1, 4);

        Label birthDateLabel = new Label("Birth Date: ");
        birthDateLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        gridPane.add(birthDateLabel, 0, 5);

        birthDatePicker = new DatePicker();
        birthDatePicker.setOnAction(e -> {
            setAge(); // assigns age value to age variable.
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
            setTravelDate(); // assigns departure date to departureDate variable in 'YYYY-MM-DD' format.
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

        Label originLabel = new Label("Origin: ");
        originLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        gridPane.add(originLabel, 0, 7);

        originBox = new ComboBox<>();
        originBox.getItems().addAll(
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
        gridPane.add(originBox, 1, 7);

        Label destinationLabel = new Label("Destination: ");
        destinationLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        gridPane.add(destinationLabel, 0, 8);

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
        gridPane.add(destinationBox, 1, 8);

        Label departureTimeLabel = new Label("Departure Time: ");
        departureTimeLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        gridPane.add(departureTimeLabel, 0, 9);

        departureTimeBox = new ComboBox<>();
        // Generates a list of random departure times.
        departureTimeBox.setItems(FXCollections.observableArrayList(DepartureTimes.generateDepartureTimes()));
        gridPane.add(departureTimeBox, 1, 9);
    }

    public void checkForEmpty() {

        if (fullNameField.getText().equals("")) {
            AlertGenerator.generateFieldAlert("Full Name");
        } else if (emailAddressField.getText().equals("")) {
            AlertGenerator.generateFieldAlert("Email Address");
        } else if (phoneNumberField.getText().equals("")) {
            AlertGenerator.generateFieldAlert("Phone Number");
        } else if (birthDatePicker.getValue() == null) {
            AlertGenerator.generateFieldAlert("Birth Date");
        } else if (travelDatePicker.getValue() == null) {
            AlertGenerator.generateFieldAlert("Departure Date");
        } else if (departureTimeBox.getValue() == null) {
            AlertGenerator.generateFieldAlert("Departure Time");
        } else if (destinationBox.getValue() == null) {
            AlertGenerator.generateFieldAlert("Destination");
        } else if (genderBox.getValue() == null) {
            AlertGenerator.generateFieldAlert("Gender");
        } else if (originBox.getValue() == null) {
            AlertGenerator.generateFieldAlert("Origin");
        }
    }

    public void resetFields() {

        fullNameField.setText("");
        emailAddressField.setText("");
        phoneNumberField.setText("");
        birthDatePicker.setValue(null);
        travelDatePicker.setValue(null);
        departureTimeBox.setValue("");
        destinationBox.setValue("");
        genderBox.setValue("");
    }

    private void setAge() {

        LocalDate currentDate = LocalDate.now();
        LocalDate birthdate = birthDatePicker.getValue();

        age = Period.between(birthdate, currentDate).getYears();
    }

    private void setTravelDate() {
        // departure date is stored in YYYY-MM-DD format.
        departureDate = travelDatePicker.getValue().toString();
        System.out.println(departureDate);
    }

    public TextField getFullNameField() {
        return fullNameField;
    }

    public TextField getEmailAddressField() {
        return emailAddressField;
    }

    public TextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public ComboBox<String> getDepartureTimeBox() {
        return departureTimeBox;
    }

    public ComboBox<String> getDestinationBox() {
        return destinationBox;
    }

    public DatePicker getBirthDatePicker() {
        return birthDatePicker;
    }

    public ComboBox<String> getOriginBox() {
        return originBox;
    }

    public ComboBox<String> getGenderBox() {
        return genderBox;
    }

    public DatePicker getTravelDatePicker() {
        return travelDatePicker;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public int getAge() {
        return age;
    }
}

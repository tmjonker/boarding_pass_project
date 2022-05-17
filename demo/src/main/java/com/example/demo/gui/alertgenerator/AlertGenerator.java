package com.example.demo.gui.alertgenerator;

import javafx.scene.control.Alert;

public class AlertGenerator {

    public static void generateFieldAlert(String title) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Missing " + title);
        alert.setHeaderText("Error");
        alert.setContentText("The " + title + " field must be filled out.");

        alert.showAndWait();
    }

    public static void generateBoardingPassAlert(String title) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Cannot find " + title);
        alert.setHeaderText("Error");
        alert.setContentText("Cannot find a boarding pass with the number " + title);

        alert.showAndWait();
    }

    public static void generateSuccessDialog(String title) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Success!");
        alert.setContentText(title);

        alert.showAndWait();
    }

    public static void generateErrorDialog(String title) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Error!");
        alert.setContentText(title);

        alert.showAndWait();
    }
}

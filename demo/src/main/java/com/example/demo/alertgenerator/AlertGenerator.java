package com.example.demo.alertgenerator;

import javafx.scene.control.Alert;

public class AlertGenerator {

    public static void generateAlert(String title) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Missing " + title);
        alert.setHeaderText("Error");
        alert.setContentText("The " + title + " field must be filled out.");

        alert.showAndWait();
    }
}

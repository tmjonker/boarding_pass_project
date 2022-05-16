package com.example.demo.alertgenerator;

import javafx.scene.control.Alert;

public class AlertGenerator {

    public static void generateAlert(String title, String content) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("Error");
        alert.setContentText(content);

        alert.showAndWait();
    }
}

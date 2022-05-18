package com.example.demo.gui.alertgenerator;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AlertGenerator {

    public static void generateFieldAlert(String title) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Missing " + title);
        alert.setHeaderText("Error");
        alert.setContentText("The " + title + " field must be filled out.");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("icon1.png"));

        alert.showAndWait();
    }

    public static void generateBoardingPassAlert(String title) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Cannot find " + title);
        alert.setHeaderText("Error");
        alert.setContentText("Cannot find a boarding pass with the number " + title);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("icon1.png"));

        alert.showAndWait();
    }

    public static void generateSuccessDialog(String title) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Success!");
        alert.setContentText(title);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("icon1.png"));

        alert.showAndWait();
    }

    public static void generateErrorDialog(String title) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Error!");
        alert.setContentText(title);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("icon1.png"));

        alert.showAndWait();
    }
}

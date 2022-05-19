package com.example.demo.gui.findboardingpasswindow;

import com.example.demo.BoardingPass.BoardingPass;
import com.example.demo.BoardingPass.BoardingPassService;
import com.example.demo.boardingpassgenerator.BoardingPassGenerator;
import com.example.demo.gui.alertgenerator.AlertGenerator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

public class FindBoardingPassWindow {

    private Stage stage;
    private GridPane gridPane;

    private TextField boardingPassNumberField;
    private Button submitButton;
    private Button cancelButton;

    public FindBoardingPassWindow() {

        this.stage = new Stage();

        generateWindow();
        showStage();
    }

    private void generateWindow() {

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Text titleText = new Text("Find Boarding Pass");
        titleText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
        gridPane.add(titleText, 0, 0, 2, 1);

        Label boardingPassNumberLabel = new Label("Boarding Pass #: ");
        boardingPassNumberLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        gridPane.add(boardingPassNumberLabel, 0, 1);

        boardingPassNumberField = new TextField();
        gridPane.add(boardingPassNumberField, 1, 1);

        submitButton = new Button("Submit");
        cancelButton = new Button("Cancel");

        submitButton.setOnAction(e -> onSubmitClick());
        cancelButton.setOnAction(e -> stage.close());

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(15, 0, 0, 0));
        buttonBox.getChildren().addAll(submitButton, cancelButton);

        gridPane.add(buttonBox, 1, 2);
    }

    private void showStage() {

        Scene scene = new Scene(gridPane, 380, 200);
        Image planeBackground = new Image("airplane.jpg");
        ImagePattern pattern = new ImagePattern(planeBackground);
        scene.setFill(pattern);
        scene.getStylesheets().add("style.css");

        stage.setScene(scene);
        stage.setTitle("Find Boarding Pass");
        stage.getIcons().add(new Image("icon1.png"));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private void onSubmitClick() {
        BoardingPassService boardingPassService = new BoardingPassService();

        String boardingPassNumber = boardingPassNumberField.getText();
        BoardingPass boardingPass = null;

        if (!boardingPassNumber.equals("")) { // if boarding pass field is not blank then search for boarding pass.
            boardingPass = boardingPassService.searchFileForPass(boardingPassNumber);
        } else {
            AlertGenerator.generateFieldAlert("Boarding Pass Number");
        }

        if (boardingPass != null) {
            AlertGenerator.generateSuccessDialog("Boarding Pass has been located.");

            BoardingPassGenerator boardingPassGenerator = new BoardingPassGenerator(boardingPass);
            boardingPassGenerator.generatePdf();

            File boardingPassFile = new File("boarding-pass.pdf");

            new Thread(() -> {

                try {
                    if (Desktop.isDesktopSupported()) {

                        Desktop desktop = Desktop.getDesktop();
                        desktop.open(boardingPassFile);
                    } else {
                        System.out.println("not supported.");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }).start();
        } else {
            AlertGenerator.generateBoardingPassAlert(boardingPassNumber);
        }

        stage.close();
    }
}

package com.example.demo.gui.findboardingpasswindow;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

        stage.close();

        // Lookup boarding pass
    }
}

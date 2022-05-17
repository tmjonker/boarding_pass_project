package com.example.demo.gui.mainwindow;

import com.example.demo.BoardingPass.BoardingPass;
import com.example.demo.BoardingPass.BoardingPassService;
import com.example.demo.gui.alertgenerator.AlertGenerator;
import com.example.demo.gui.findboardingpasswindow.FindBoardingPassWindow;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow {

    private final Stage stage;
    private GridPane gridPane;
    private BorderPane borderPane;

    private MainWindowFieldHandler mainWindowFieldHandler;
    private MainWindowMenuHandler mainWindowMenuHandler;
    private MainWindowButtonHandler mainWindowButtonHandler;

    public MainWindow(Stage stage) {

        this.stage = stage;

        generateWindow();

        mainWindowFieldHandler = new MainWindowFieldHandler(gridPane);
        mainWindowFieldHandler.generateFields();

        mainWindowMenuHandler = new MainWindowMenuHandler(borderPane);
        mainWindowMenuHandler.generateMenu();
        addFunctionalityToMenuItems();

        mainWindowButtonHandler = new MainWindowButtonHandler(gridPane);
        mainWindowButtonHandler.generateButtons();
        addFunctionalityToButtons();

        showStage();
    }

    private void generateWindow() {

        borderPane = new BorderPane();
        borderPane.setOpacity(0.85);

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        borderPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));

        borderPane.setCenter(gridPane);

        Image image = new Image("airplane.png");
        ImageView imageView = new ImageView(image);

        gridPane.add(imageView, 0, 0);

        Text titleText = new Text("Create Boarding Pass");
        titleText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
        gridPane.add(titleText, 1, 0, 2, 1);
    }

    private void addFunctionalityToButtons() {

        mainWindowButtonHandler.getResetButton().setOnAction(e -> onResetClick());
        mainWindowButtonHandler.getSubmitButton().setOnAction(e -> onSubmitClick());
    }

    private void addFunctionalityToMenuItems() {

        mainWindowMenuHandler.getFindMenuItem().setOnAction(e -> {

            new FindBoardingPassWindow();
        });
    }

    private void showStage() {

        Scene scene = new Scene(borderPane, 450, 500);
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

    // Processes data that is entered by the user and generates the boarding pass.
    private void onSubmitClick() {

        validateUserEntry(); // checks form for missing entries and alerts user if any field is not filled in.

        // process data and generate boarding pass.

        BoardingPassService boardingPassService = new BoardingPassService();

        String name = mainWindowFieldHandler.getFullNameField().getText();
        String email = mainWindowFieldHandler.getEmailAddressField().getText();
        String phoneNumber = mainWindowFieldHandler.getPhoneNumberField().getText();
        String gender = mainWindowFieldHandler.getGenderBox().getValue();
        int age = mainWindowFieldHandler.getAge();
        String boardingPassNumber = boardingPassService.chars();
        String departureDate = mainWindowFieldHandler.getDepartureDate();
        String origin = mainWindowFieldHandler.getOriginBox().getValue();
        String destination = mainWindowFieldHandler.getDestinationBox().getValue();
        String departureTime = mainWindowFieldHandler.getDepartureTimeBox().getValue();
        double ticketPrice = boardingPassService.calcTicketPrice(80.00, age, gender);
        String eta = boardingPassService.calcETA(origin, destination);

        BoardingPass boardingPass = new BoardingPass(name, email, phoneNumber, gender, age, boardingPassNumber,
                departureDate, origin, destination, departureTime, ticketPrice, eta);

        boardingPass.writeToFile();

        boardingPass = boardingPassService.searchFileForPass(boardingPassNumber);

        if (boardingPass != null) {
            AlertGenerator.generateSuccessDialog("Boarding Pass has been generated.");
        } else {
            AlertGenerator.generateErrorDialog("Failed to generate boarding pass");
        }

        onResetClick(); // resets all fields to blanks.

        System.out.println(boardingPass);
    }

    private void validateUserEntry() {

        mainWindowFieldHandler.checkForEmpty();
    }

    private void onResetClick() {

        mainWindowFieldHandler.resetFields();
    }
}

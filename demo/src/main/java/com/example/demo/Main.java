package com.example.demo;

import com.example.demo.gui.mainwindow.MainWindow;
import javafx.application.Application;

import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {

        new MainWindow(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
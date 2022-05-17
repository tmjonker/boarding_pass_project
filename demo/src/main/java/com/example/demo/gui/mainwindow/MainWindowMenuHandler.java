package com.example.demo.gui.mainwindow;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class MainWindowMenuHandler {

    private BorderPane borderPane;

    private MenuItem findMenuItem;

    public MainWindowMenuHandler(BorderPane borderPane) {

        this.borderPane = borderPane;
    }

    public void generateMenu() {

        MenuBar menuBar = new MenuBar();
        Menu optionsMenu = new Menu("Options");
        menuBar.getMenus().add(optionsMenu);
        findMenuItem = new MenuItem("Find an Existing Boarding Pass");
        optionsMenu.getItems().addAll(findMenuItem);

        borderPane.setTop(menuBar);
    }

    public MenuItem getFindMenuItem() {
        return findMenuItem;
    }
}

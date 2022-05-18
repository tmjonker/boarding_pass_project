module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires barbecue;
    requires itextpdf;
    requires pdfbox;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.gui.alertgenerator;
    opens com.example.demo.gui.alertgenerator to javafx.fxml;
    exports com.example.demo.gui.departuretimes;
    opens com.example.demo.gui.departuretimes to javafx.fxml;
    exports com.example.demo.gui.mainwindow;
    opens com.example.demo.gui.mainwindow to javafx.fxml;
    exports com.example.demo.gui.findboardingpasswindow;
    opens com.example.demo.gui.findboardingpasswindow to javafx.fxml;
    exports com.example.demo.boardingpassgenerator;
    opens com.example.demo.boardingpassgenerator to javafx.fxml;
}
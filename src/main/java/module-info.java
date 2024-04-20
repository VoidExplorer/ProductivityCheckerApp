module productivitychecckerapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    requires de.jensd.fx.glyphs.commons;
    requires de.jensd.fx.glyphs.materialdesignicons;
    requires de.jensd.fx.glyphs.fontawesome;
    requires com.jfoenix;

    opens productivitycheckerapp to javafx.fxml;
    exports productivitycheckerapp;
    exports controllers;
    opens controllers to javafx.fxml;
}
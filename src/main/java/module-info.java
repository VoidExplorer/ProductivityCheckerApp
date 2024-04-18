module productivitychecckerapp.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    requires de.jensd.fx.glyphs.commons;
    requires de.jensd.fx.glyphs.materialdesignicons;
    requires de.jensd.fx.glyphs.fontawesome;

    opens productivitycheckerapp to javafx.fxml;
    exports productivitycheckerapp;
}
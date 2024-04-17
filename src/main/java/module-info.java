module productivitychecckerapp.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;

    opens productivitycheckerapp to javafx.fxml;
    exports productivitycheckerapp;
}
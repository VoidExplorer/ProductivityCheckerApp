module productivitychecckerapp.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens productivitychecckerapp to javafx.fxml;
    exports productivitychecckerapp;
}
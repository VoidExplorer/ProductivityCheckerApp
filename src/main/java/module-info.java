module productivitychecckerapp.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens productivitycheckerapp to javafx.fxml;
    exports productivitycheckerapp;
}
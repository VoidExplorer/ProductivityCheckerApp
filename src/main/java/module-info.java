module productivitychecckerapp.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens productivitychecckerapp to javafx.fxml;
    exports productivitychecckerapp;
}
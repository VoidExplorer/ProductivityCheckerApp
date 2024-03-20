module productivitychecckerapp.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens productivitychecckerapp.demo to javafx.fxml;
    exports productivitychecckerapp.demo;
}
package controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import productivitycheckerapp.MFXResourcesLoader;
import productivitycheckerapp.database;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

/* adds a listener that checks if the text in "field" exceeds the "limit"
   and uses "label" to change the color of the label to red if that's true, reverts to purple otherwise
 */
public class SceneController {
    public static void setFieldLimit(MFXTextField field, Label label, int limit) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (observable.getValue().length() > limit) {
                label.setVisible(true);
                field.setStyle("-fx-border-color: red");
            } else {
                label.setVisible(false);
                field.setStyle("-fx-border-color: #594BE8");
            }
        });
    }

}




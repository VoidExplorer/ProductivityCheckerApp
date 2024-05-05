package controllers;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.awt.*;

/* adds a listener that checks if the text in "field" exceeds the "limit"
   and uses "label" to change the color of the label to red if that's true, reverts to purple otherwise
 */
public class SceneController {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static double width = screenSize.getWidth()/2;
    private static double height = screenSize.getHeight()/2;
    public static Scene scene;

    public static double getWidth() {
        return width;
    }

    public static void setWidth(double width) {
        SceneController.width = width;
    }

    public static double getHeight() {
        return height;
    }

    public static void setHeight(double height) {
        SceneController.height = height;
    }

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

    // check if text in a field matches the other and display the specified label accordingly
    public static void addMatchChecker(MFXTextField field, MFXTextField field2, Label label) {
        field2.setText("");
        field2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (observable.getValue().equals(field.getText())) {
                label.setVisible(false);
                field2.setStyle("-fx-border-color: #594BE8");
            }
            else {
                label.setVisible(true);
                field2.setStyle("-fx-border-color: red");
            }
        });
    }

}




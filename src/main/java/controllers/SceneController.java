package controllers;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.scene.control.Label;

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




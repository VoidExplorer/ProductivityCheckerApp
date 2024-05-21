package controllers;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import productivitycheckerapp.database;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static controllers.HomeController.*;
import static controllers.SigninController.loggedInUser;

public class EditController implements Initializable {
    @FXML
    private MFXTextField titleField;

    @FXML
    private MFXTextField descriptionfield;

    @FXML
    private Label titleLabel;

    public void editTodo(ActionEvent event) {
        String title = titleField.getText();
        String description = descriptionfield.getText();
        if(title.isEmpty() || description.isEmpty()){
            System.out.println("Empty fields");
        }
        else {
            try {
                database.editTodo(title, description, currentTodoID);
                tempStage.close();
                database.reloadTodos(loggedInUser.getUsername());
                refreshTodos();
            } catch (SQLException e) {
                System.out.println(e.getMessage());;
            }
            updateCurrentTodo(title, description);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // get the old name before editing
        titleLabel.setText("Edit: " + todos.get(currentIndex).getTitle());
        System.out.println(currentIndex);
    }
}

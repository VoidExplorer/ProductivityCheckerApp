package controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import productivitycheckerapp.MFXResourcesLoader;
import productivitycheckerapp.Todo;
import productivitycheckerapp.database;

import java.io.IOException;
import java.sql.SQLException;

import static controllers.HomeController.*;
import static controllers.SigninController.loggedInUser;

public class NewController {
    static String title;

    @FXML
    private MFXTextField titleField;
    @FXML
    private MFXTextField descriptionField;

    private Stage stage;

    public void addTodo(ActionEvent event) {
        title = titleField.getText();
        String description = descriptionField.getText();
        if(title.isEmpty() || description.isEmpty()){
            System.out.println("Empty fields");
        }
        else {
            try {
                database.addTodo(loggedInUser.getUsername(), title, description);
                MFXButton button = new MFXButton(title);
                todosbox_.getChildren().add(button);
                newTodoStage.close();
                database.addLastTodo();
                refreshTodos();
                // iterating todos
                for (Todo t: todos) {
                    System.out.println(t.getDescription());
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());;
            }
        }
    }
}

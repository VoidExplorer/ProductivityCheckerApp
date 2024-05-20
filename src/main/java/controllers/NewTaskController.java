package controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import productivitycheckerapp.MFXResourcesLoader;
import productivitycheckerapp.database;

import java.io.IOException;
import java.sql.SQLException;

import static controllers.HomeController.todosbox_;
import static controllers.HomeController.newTodoStage;
public class NewTaskController {
    static String task;

    @FXML
    private MFXTextField taskField;
    @FXML
    private MFXDatePicker dueTimeField;

    private Stage stage;

    public void addTask(ActionEvent event) {
        task = taskField.getText();
        String description = dueTimeField.getText();
        if(task.isEmpty() || description.isEmpty()){
            System.out.println("Empty fields");
        }
        else {
            try {
                database.addTask(taskField.getText(),dueTimeField.getText());
                MFXButton button = new MFXButton(task);
                todosbox_.getChildren().add(button);
                newTodoStage.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());;
            }
        }
    }
}

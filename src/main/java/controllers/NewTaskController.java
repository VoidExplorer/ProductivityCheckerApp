package controllers;

import io.github.palexdev.materialfx.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import productivitycheckerapp.MFXResourcesLoader;
import productivitycheckerapp.database;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controllers.HomeController.*;
import static controllers.SigninController.isStudent;
import static controllers.SigninController.loggedInUser;

public class NewTaskController implements Initializable {
    static String task;

    @FXML
    private MFXTextField taskField;
    @FXML
    private MFXDatePicker dueTimeField;
    private Stage stage;
    @FXML
    private MFXComboBox<String> courseComboBox;


    public void addTask(ActionEvent event) {
        task = taskField.getText();
        String description = dueTimeField.getText();
        if(task.isEmpty() || description.isEmpty()){
            System.out.println("Empty fields");
        }
        else {
            try {
                database.addTask(currentTodoID,taskField.getText(),dueTimeField.getText());
                String course = isStudent ? ("\tCourse: "+courseComboBox.getSelectionModel()) : "";
                MFXCheckbox chbox = new MFXCheckbox(taskField.getText() + " | Due : "+dueTimeField.getText() + course);
                cboxes_.getChildren().add(chbox);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courseComboBox.setVisible(isStudent);
        try {
            ArrayList<String> courses = database.getCourses(loggedInUser.getUsername());
            for(String course: courses) {
                courseComboBox.getItems().add(course);
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}

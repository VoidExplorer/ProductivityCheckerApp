package controllers;

import io.github.palexdev.materialfx.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import productivitycheckerapp.NotificationManager;
import productivitycheckerapp.database;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private MFXDatePicker dueDateField;
    @FXML
    private MFXComboBox<String> courseComboBox;
    @FXML
    private MFXSlider timeSlider;


    public void addTask() throws SQLException {
        task = taskField.getText();
        String time = Double.toString(timeSlider.getValue());
        String[] t = time.split("\\.");
        int minuteValue = Integer.parseInt(t[1])*6;
        if (minuteValue == 0) {
            t[1] += "0";
        }
        else {
            t[1] = Integer.toString(minuteValue);
        }
        String dueTime = t[0] + ":" + t[1];
        String description = dueDateField.getText();
        if(task.isEmpty() || description.isEmpty()){
            System.out.println("Empty fields");
        }
        else {
            try {
                database.addTask(currentTodoID,taskField.getText(), (dueDateField.getText() + " at " + dueTime ));
                String course = isStudent ? ("\tCourse: "+courseComboBox.getSelectionModel().getSelectedItem()) : "";
                MFXCheckbox chbox = new MFXCheckbox(taskField.getText() + " | Due : "+ dueDateField.getText() +  " at " + dueTime + "\t"+ course);
                NotificationManager taskNotification = new NotificationManager("Task");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m");
                LocalDate taskDate = LocalDate.parse(dueDateField.getText(), formatter);
                LocalTime taskTime = LocalTime.parse(dueTime, timeFormatter);
                LocalDateTime dateTime = LocalDateTime.of(taskDate, taskTime);
                taskNotification.scheduleNotification(taskField.getText(), "Task Deadline has arrived", dateTime);

                cboxes_.getChildren().add(chbox);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (AWTException e) {
                System.out.println("Error in Notification Manager: " + e.getMessage());
            }
            tempStage.close();
            database.addLastTask();
            refreshTodos();
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

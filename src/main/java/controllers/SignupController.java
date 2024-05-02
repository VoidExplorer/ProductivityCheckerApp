package controllers;


import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import productivitycheckerapp.MFXResourcesLoader;
import productivitycheckerapp.database;

import java.io.IOException;
import java.sql.SQLException;


public class SignupController {
    private Stage stage;
    private Scene scene;




    @FXML
    private MFXTextField usernameField;
    @FXML
    private MFXPasswordField passwordField;
    @FXML
    private MFXPasswordField confirmpasswordField;
    @FXML
    private JFXButton signup;

    @FXML
    public void switchToSignIn(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("signin.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void signUp(ActionEvent event) throws SQLException {
            if (passwordField.getText().equals(confirmpasswordField.getText())) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                database.addUser(username, password);
            }
            else
                System.out.println("Passwords do not match");
    }
}

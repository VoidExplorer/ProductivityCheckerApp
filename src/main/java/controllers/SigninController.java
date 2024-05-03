package controllers;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import productivitycheckerapp.MFXResourcesLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SigninController implements Initializable {
    private Stage stage;
    private Scene scene;
    @FXML
    private MFXTextField usernamelimit1;
    @FXML
    private Label usernamelabel;

    @FXML
    public void switchToSignUp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("signup.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SceneController.setTextFieldLimit(usernamelimit1, usernamelabel, 15);

    }
}

package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import productivitycheckerapp.MFXResourcesLoader;
import java.io.IOException;


public class SignupController {
    private Stage stage;
    private Scene scene;

    @FXML
    public void switchToSignIn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("signin.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

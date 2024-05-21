package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import productivitycheckerapp.MFXResourcesLoader;

import java.io.IOException;

import static controllers.SceneController.scene;

public class closeController {
    public static void bringClosePopUp() throws IOException {
        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("closeNotifier.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void yes(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void no(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        System.exit(0);
    }
}

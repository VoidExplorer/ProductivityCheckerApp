package productivitycheckerapp;

import controllers.SceneController;
import fr.brouillard.oss.cssfx.CSSFX;
import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static controllers.SceneController.scene;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        CSSFX.start();
        // Setting up UserAgentBuilder with themes and configurations
        UserAgentBuilder.builder()
                .themes(JavaFXThemes.MODENA)
                .themes(MaterialFXStylesheets.forAssemble(true))
                .setDeploy(true)
                .setResolveAssets(true)
                .build()
                .setGlobal();

        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("signin.fxml"));
        Parent root = loader.load();
        scene = new Scene(root, SceneController.getWidth(), SceneController.getHeight());
        stage.setScene(scene);
        stage.setTitle("Productivity Checker App");
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.show();
        stage.setOnCloseRequest(windowEvent -> {
            System.out.println("Stage closed");
            try {
                controllers.closeController.bringClosePopUp();
            } catch (IOException e) {
                System.out.println("Error bringing pop-up: " + e.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        try {
            database.connect();
            launch();   // Launching the JavaFX application
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            database.disconnect();
        }
    }

}
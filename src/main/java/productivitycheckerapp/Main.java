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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static controllers.SceneController.scene;

public class Main extends Application {

    public static User currentUser;
    @Override
    public void start(Stage stage) throws IOException {
        CSSFX.start();

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

    }

    public static void main(String[] args) throws SQLException {
        try {
            database.connect();
            launch();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            database.disconnect();
        }
    }

}
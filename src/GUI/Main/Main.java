package GUI.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.util.Objects;
import Styles.StyleLoader;
import javafx.scene.image.Image;

public class Main extends Application {
    @Override
    public void start (Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/Main/Main.fxml")));
            Scene scene = new Scene(root);
            StyleLoader.FileLoader(scene);
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setX(10);
            stage.setY(10);
            stage.setResizable(false);
            stage.setTitle("Bank Application");
            Image icon = new Image("/Icons/MainIcon.png");
            stage.getIcons().add(icon);
            stage.show();
        } catch (Exception exception) {
            System.out.println("asfasdfasdf "+ exception.getMessage());
        }
    }
    public void launcherMethod (String [] args) {
        launch(args);
    }
}

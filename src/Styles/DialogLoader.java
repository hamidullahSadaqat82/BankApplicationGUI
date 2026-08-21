package Styles;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import java.util.Objects;

public class DialogLoader {
    private static final String classPath = "/Styles/Style.css";

    public static void dialogLoader (Alert alert) {
        alert.setX(335);
        alert.setY(300);
        DialogPane dialogVar = alert.getDialogPane();
        dialogVar.getStylesheets().add(Objects.requireNonNull(DialogLoader
                .class.getResource(classPath))
                .toExternalForm());
    }
}

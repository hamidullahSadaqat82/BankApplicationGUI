package Styles;

import javafx.scene.Scene;

import java.util.Objects;



public class StyleLoader {
    private static final String classPath = "/Styles/Style.css";
    public static void FileLoader (Scene scene) {
        scene.getStylesheets().add(Objects.requireNonNull(StyleLoader.class.getResource(classPath)).toExternalForm());
    }
}

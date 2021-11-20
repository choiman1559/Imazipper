package com.imazipper.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.File;
import java.io.IOException;

import static com.imazipper.gui.MainApplication.stage;

public class StageUtils {
    public static void startStage(FXMLLoader fxmlLoader) throws IOException {
        Scene scene = new Scene(fxmlLoader.load(), 390, 250);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static String openDirChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory");
        File file = directoryChooser.showDialog(stage);
        return file == null ? "" : file.getAbsolutePath();
    }

    public static String openFileChooser(FileChooser.ExtensionFilter... extensionFilters) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(extensionFilters);
        File selectedFile = fileChooser.showOpenDialog(stage);
        return selectedFile == null ? "" : selectedFile.getAbsolutePath();
    }
}

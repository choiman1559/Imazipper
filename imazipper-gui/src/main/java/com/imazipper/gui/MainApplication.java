package com.imazipper.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    public static Scene scene;
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        scene = new Scene(fxmlLoader.load(), 320, 240);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);

        stage.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("icon.png"))));
        stage.setResizable(false);
        stage.setTitle("Imazipper de/combiner");
        stage.setScene(scene);
        MainApplication.stage = stage;
        MainApplication.stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
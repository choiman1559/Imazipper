package com.imazipper.gui.split;

import com.imazipper.gui.MainApplication;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import static com.imazipper.gui.StageUtils.startStage;

public class SplitScreen {
    public static void start() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("split-view.fxml"));
        startStage(fxmlLoader);
    }
}

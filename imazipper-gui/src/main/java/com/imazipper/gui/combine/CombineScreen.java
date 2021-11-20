package com.imazipper.gui.combine;

import com.imazipper.gui.MainApplication;
import com.imazipper.gui.StageUtils;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class CombineScreen {
    public static void start() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("combine-view.fxml"));
        StageUtils.startStage(fxmlLoader);
        MainApplication.stage.show();
    }
}

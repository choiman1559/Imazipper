package com.imazipper.gui;

import com.imazipper.gui.combine.CombineScreen;
import com.imazipper.gui.split.SplitScreen;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainController {

    @FXML
    protected void onComButtonClick() throws IOException {
        CombineScreen.start();
    }

    @FXML
    protected void onSplitButtonClick() throws IOException {
        SplitScreen.start();
    }

    @FXML
    protected void onGitButtonClick() {
        MainApplication.hostServices.showDocument("https://github.com/choiman1559/Imazipper");
    }

    @FXML
    protected void onExitButtonClick() {
        System.exit(0);
    }
}
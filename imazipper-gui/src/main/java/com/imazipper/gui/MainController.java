package com.imazipper.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onComButtonClick() {
        welcomeText.setText("combine");
        MainApplication.stage.close();
    }

    @FXML
    protected void onSplitButtonClick() {
        welcomeText.setText("split");
    }
}
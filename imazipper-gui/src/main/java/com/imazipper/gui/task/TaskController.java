package com.imazipper.gui.task;

import com.imazipper.gui.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

import java.io.IOException;

import static com.imazipper.gui.StageUtils.startStage;

public class TaskController {
    public ProgressBar ProgressBar;
    public Label CurrentStatus;
    public TextArea LogViewer;
    public Button CloseButton;

    @FXML
    protected void onCloseClick() throws IOException {
        startStage(new FXMLLoader(MainApplication.class.getResource("main-view.fxml")));
    }

    public Button getCloseButton() {
        return CloseButton;
    }

    public javafx.scene.control.ProgressBar getProgressBar() {
        return ProgressBar;
    }

    public void setCurrentStatus(String message) {
        CurrentStatus.setText(message);
        updateLog(message);
    }

    public void updateLog(String message) {
        LogViewer.setText(LogViewer.getText() + (LogViewer.getText().isEmpty() ? "" : "\n") + message);
    }
}

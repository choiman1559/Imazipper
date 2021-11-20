package com.imazipper.gui.split;

import com.imazipper.gui.MainApplication;
import com.imazipper.gui.task.TaskScreen;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;

import static com.imazipper.gui.StageUtils.*;

public class SplitController {
    public Button FileBrowse;
    public TextField FilePath;

    public Button OutputBrowse;
    public TextField OutputPath;

    public CheckBox ForceSplit;
    public Button CancelButton;
    public Button StartCombineButton;

    @FXML
    protected void onFileBrowse() {
        FilePath.setText(openFileChooser());
    }

    @FXML
    protected void onOutputBrowse() {
        OutputPath.setText(openDirChooser());
    }

    @FXML
    protected void onCancelButtonClick() throws IOException {
        startStage(new FXMLLoader(MainApplication.class.getResource("main-view.fxml")));
    }

    @FXML
    protected void onStartButtonClick() throws IOException {
        String[] list = new String[] {FilePath.getText(), OutputPath.getText()};

        if(list[0].isEmpty() || list[1].isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Missing item");
            alert.setContentText("Please input string on all text fields!");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("OK");
            alert.showAndWait();
        } else TaskScreen.startSplitTask(list[0], list[1], ForceSplit.isSelected());
    }
}

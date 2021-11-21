package com.imazipper.gui.combine;

import com.imazipper.gui.MainApplication;
import com.imazipper.gui.task.TaskScreen;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.IOException;

import static com.imazipper.gui.StageUtils.*;

public class CombineController {
    public TextField File1Path;
    public Button File1Browse;

    public TextField File2Path;
    public Button File2Browse;

    public TextField OutputPath;
    public Button OutputBrowse;

    public Button StartCombineButton;
    public Button CancelButton;

    @FXML
    protected void onFile1Browse() {
        String result = openFileChooser(
                new FileChooser.ExtensionFilter("Image File","*.png", "*.gif", "*.jpg", "*.jpeg", "*.jp2", "*.j2k", "*.jpf", "*.jpm", "*.jpg2", "*.j2c", "*.jpc", "*.jpx", "*.mj2"),
                new FileChooser.ExtensionFilter("All File", "*.*")
        );
        if(!result.isEmpty()) File1Path.setText(result);
    }

    @FXML
    protected void onFile2Browse() {
        String result = openFileChooser();
        if(!result.isEmpty()) File2Path.setText(result);
    }

    @FXML
    protected void onOutputBrowse() {
        String result = openDirChooser();
        if(!result.isEmpty()) OutputPath.setText(result);
    }

    @FXML
    protected void onStartButtonClick() throws IOException {
        String[] list = new String[] {File1Path.getText(), File2Path.getText(), OutputPath.getText()};

        if(list[0].isEmpty() || list[1].isEmpty() || list[2].isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Missing item");
            alert.setContentText("Please input string on all text fields!");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("OK");
            alert.showAndWait();
        } else TaskScreen.startCombineTask(list[0], list[1], list[2]);
    }

    @FXML
    protected void onCancelButtonClick() throws IOException {
        new MainApplication().loadMainScreen();
    }
}

package com.imazipper.gui.task;

import com.imazipper.gui.MainApplication;
import com.imazipper.gui.combine.CombineScreen;
import com.imazipper.lib.Combiner;
import com.imazipper.lib.Splitter;
import com.imazipper.lib.TaskResult;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.File;
import java.io.IOException;

import static com.imazipper.gui.StageUtils.startStage;

public class TaskScreen {
    public static void startCombineTask(String input1, String input2, String output) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("task-view.fxml"));
        startStage(fxmlLoader);
        File file1 = new File(input1);
        File file2 = new File(input2);
        File outputFile = new File(output);

        TaskController screen = fxmlLoader.getController();
        if (file1.exists() || file2.exists() || outputFile.exists()) {
            screen.setCurrentStatus("Target Files: \n1. " + file1.getName() + "\n2. " + file2.getName() + "\nOutput: " + output);
            screen.setCurrentStatus("Starting combine file...");
            new Thread(() -> {
                TaskResult result = Combiner.Combine(file1, file2, outputFile);
                handleResult(screen, result);
            }).start();
        } else {
            showFileNotFoundDialog(screen);
        }
    }

    public static void startSplitTask(String input, String output, boolean isForceMode) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("task-view.fxml"));
        startStage(fxmlLoader);

        File inputFile = new File(input);
        File outputFile = new File(output);

        TaskController screen = fxmlLoader.getController();
        if (inputFile.exists() || outputFile.exists()) {
            screen.setCurrentStatus("Target File: " + inputFile.getName() + "\nOutput: " + output + "/Imazipper_output");
            screen.setCurrentStatus("Starting combine file...");
            new Thread(() -> {
                TaskResult result = Splitter.Split(inputFile, outputFile, isForceMode);
                handleResult(screen, result);
            }).start();
        } else {
            showFileNotFoundDialog(screen);
        }
    }

    private static void showFileNotFoundDialog(TaskController screen) {
        screen.setCurrentStatus("Error: File Not Found");
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("File not found!");
        alert.setContentText("Please check your selection and try again!");
        Button OK = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        OK.setText("OK");
        OK.setOnAction(event -> {
            try {
                CombineScreen.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        alert.showAndWait();
    }

    public static void handleResult(TaskController screen, TaskResult result) {
        Platform.runLater(() -> {
            screen.getCloseButton().setDisable(false);
            screen.getProgressBar().setVisible(false);
            if (result.isTaskSuccess()) {
                screen.setCurrentStatus("Task done successfully!");


            } else if (result.hasException()) {
                screen.setCurrentStatus("Task failed!");
                screen.updateLog("Error: " + result.getErrorCode());
                screen.updateLog("Stacktrace: " + result.getTaskException().toString());
            } else {
                screen.setCurrentStatus("Task failed!");
                screen.updateLog("Error: " + result.getErrorCode());
            }
        });
    }
}

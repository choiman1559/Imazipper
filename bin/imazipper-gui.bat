@echo off
cd /d %~dp0
cd ..
cd lib
start java --module-path apiguardian-api-1.1.2.jar;controlsfx-11.0.0.jar;imazipper_gui.jar;imazipper_library.jar;javafx-base-17.0.0.1.jar;javafx-base-17.0.0.1-win.jar;javafx-controls-17.0.0.1.jar;javafx-controls-17.0.0.1-win.jar;javafx-fxml-17.0.0.1-win.jar;javafx-graphics-17.0.0.1.jar;javafx-graphics-17.0.0.1-win.jar;jmetro-11.6.15.jar;junit-jupiter-api-5.8.1.jar;junit-jupiter-engine-5.8.1.jar;junit-platform-commons-1.8.1.jar;junit-platform-engine-1.8.1.jar;opentest4j-1.2.0.jar;picocli-4.6.2.jar --add-modules javafx.controls,javafx.fxml --module com.imazipper.gui/com.imazipper.gui.MainApplication
exit
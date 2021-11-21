module com.imazipper.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.jfxtras.styles.jmetro;
    requires com.imazipper.lib;
    requires java.desktop;

    opens com.imazipper.gui to javafx.fxml;
    exports com.imazipper.gui;

    opens com.imazipper.gui.combine to javafx.fxml;
    opens com.imazipper.gui.split to javafx.fxml;
    opens com.imazipper.gui.task to javafx.fxml;
}
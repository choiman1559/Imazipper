module com.imazipper.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.jfxtras.styles.jmetro;
    requires com.imazipper.lib;

    opens com.imazipper.gui to javafx.fxml;
    exports com.imazipper.gui;
}
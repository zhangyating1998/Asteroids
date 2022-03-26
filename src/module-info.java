module group13.application {
    requires javafx.controls;
    requires javafx.fxml;


    opens group13.application to javafx.fxml;
    exports group13.application;
    exports group13.application.common;
    opens group13.application.common to javafx.fxml;
    exports group13.application.asteroid;
    opens group13.application.asteroid to javafx.fxml;
    exports group13.application.examples;
}
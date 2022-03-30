module group13.application {
    requires javafx.controls;
    requires javafx.fxml;


    opens group13.application to javafx.fxml;
    exports group13.application;
    exports group13.application.common;
    opens group13.application.common to javafx.fxml;
    exports group13.application.characters;
    opens group13.application.characters to javafx.fxml;
    exports group13.application.examples;
    opens group13.application.examples to javafx.fxml;
}
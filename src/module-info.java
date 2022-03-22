module group13.application {
    requires javafx.controls;
    requires javafx.fxml;


    opens group13.application to javafx.fxml;
    exports group13.application;
}
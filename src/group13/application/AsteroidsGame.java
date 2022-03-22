package group13.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class AsteroidsGame extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Polygon ship = new Polygon();
        ship.setFill(Color.WHITE);
        ship.getPoints().addAll(new Double[]{
                0.0, 0.0,
                20.0, 0.0,
                10.0, 20.0
        });
        StackPane pane = new StackPane(
                new Rectangle(800, 600, Color.BLACK),
                ship
        );

        stage.setScene(new Scene(pane, 800, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
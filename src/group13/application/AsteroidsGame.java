package group13.application;

import group13.application.game.GameEngine;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main program that starts the game engine.
 *
 * @author yulong
 */
public class AsteroidsGame extends Application {
    // Main method with exception handling
    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    @Override
    public void start(Stage stage) {
        GameEngine engine = new GameEngine(stage);
        engine.newGame();
    }
}
package group13.application;

import group13.application.asteroid.LargeAsteroid;
import group13.application.ship.Ship;
import javafx.animation.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import static group13.application.common.Constants.*;

/**
 * Main program that starts the game.
 *
 * @author yulong
 */
public class AsteroidsGame extends Application {

    // Game level starts from GAME_LEVEL_START, which is 1
    private int gameLevel = GAME_LEVEL_START;

    private Ship playerShip;

    private int i = 0;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        // Asteroids should be able to move randomly, and split when crashes
        LargeAsteroid largeAsteroid = new LargeAsteroid();

        // enemy ship should be able to move randomly, and shoot towards player ship
        // TODO enemy ship should be add into scene after a period of time
        Ship enemyShip = new Ship();

        // player ship should be able to move by keyboard, and can shoot bullets.
        playerShip = new Ship();

        // TODO the location of the player ship should be calculated based on the other objects in the scene
        // set the location of the player ship

        StackPane pane = new StackPane(
                // add more asteroids
                largeAsteroid,
                // add enemy ship
                enemyShip,
                // add player ship
                playerShip
        );

        stage.show();
        stage.setTitle("Asteroids");
        stage.setScene(new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT));

        // Start the game time line, which is running forever
        // This is used to detect game events such as collision
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                i++;
                System.out.println("The current frame number: " + i);
                // TODO detect collision
                detectCollision(pane);
            }
        };
        KeyFrame keyFrame = new KeyFrame(Duration.INDEFINITE);
        //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        timer.start();
    }

    // TODO
    private void detectCollision(StackPane pane) {
        ObservableList<Node> observableList = pane.getChildren();
        for (Node node : observableList) {
            if (!(node instanceof Ship)) {
                Shape nodeShape = (Shape) node;
//                Polygon.intersect(playerShip, nodeShape);
            }
        }
    }
}
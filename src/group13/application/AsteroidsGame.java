package group13.application;

import group13.application.asteroid.Asteroid;
import group13.application.asteroid.LargeAsteroid;
import group13.application.ship.EnemyShip;
import group13.application.ship.PlayerShip;
import group13.application.ship.Ship;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

import static group13.application.common.Constants.*;

/**
 * Main program that starts the game.
 *
 * @author yulong
 */
public class AsteroidsGame extends Application {

    // Hashmap for keyboard inputs
    Map<KeyCode, Boolean> pressedKeys = new HashMap<>();

    // Game level starts from GAME_LEVEL_START, which is 1
    private int gameLevel = GAME_LEVEL_START;

    private Character playerShip;

    private int i = 0;

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
        // Asteroids should be able to move randomly, and split when crashes
        LargeAsteroid largeAsteroid = new LargeAsteroid();

        // enemy ship should be able to move randomly, and shoot towards player ship
        // TODO enemy ship should be add into scene after a period of time
        Ship enemyShip = new EnemyShip();

        // player ship should be able to move by keyboard, and can shoot bullets.
        playerShip = new PlayerShip(150, 100);

        // TODO the location of the player ship should be calculated based on the other objects in the scene
        // set the location of the player ship

        // Replaced StackPane with Pane
        Pane pane = new Pane(
//                // add more asteroids
//                largeAsteroid,
//                // add enemy ship
//                enemyShip,
//                // add player ship
//                playerShip
        );
        // Set size of Pane and add player ship object to Pane
        pane.setPrefSize(800, 600);
        pane.getChildren().add(playerShip.getCharacter());

        stage.show();
        stage.setTitle("Asteroids");
        // Remove SCENE_WIDTH and SCENE_WIDTH parameters as not available in file. Added scene variable for later use.
        Scene scene = new Scene(pane);
        stage.setScene(scene);

        // Create key press event to check if keys are pressed or not, stored in hashmap declared at top
        scene.setOnKeyPressed(event -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);
        });

        scene.setOnKeyReleased(event -> {
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });

        // Start the game Timeline, which is running forever
        // This is used to detect game events such as collision
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                i++;
//                System.out.println("The current frame number: " + i);
                // TODO detect collision
                boolean hasCollision = detectCollision(pane);
                if (hasCollision) {
                    // check
                }

                // Call movement commands from Character Class based on keyboard inputs
                if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                    playerShip.turnLeft();
                }
                if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                    playerShip.turnRight();
                }
                if (pressedKeys.getOrDefault(KeyCode.UP, false)) {
                    playerShip.accelerate(0.05);
                }
                playerShip.move();
            }
        };

        KeyFrame keyFrame = new KeyFrame(Duration.INDEFINITE);
        //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        timer.start();
    }

    /**
     * A collision cause the objects involved to be destroyed.
     * <p>
     * Types of collision:
     * 1. Ship vs asteroid
     * 2. Ship vs ship
     * 3. Bullet vs asteroid
     * 4. Bullet vs ship
     */

    // Replaced StackPane parameter with Pane as changed previously
    private boolean detectCollision(Pane pane) {
        ObservableList<Node> observableList = pane.getChildren();
        for (int i = 0; i < observableList.size(); i++) {
            Node node1 = observableList.get(i);
            if (node1 instanceof Destroyable) {
                for (int j = i + 1; j < observableList.size(); j++) {
                    Node node2 = observableList.get(j);
                    if (node2 instanceof Destroyable) {
                        // detect collision by checking the overlap between two objects
                        if (node1.intersects(node2.getLayoutBounds())) {
                            // 1. Ship vs asteroid
                            boolean isShipVSAsteroid = node1 instanceof Ship && node2 instanceof Asteroid;
                            boolean isAsteroidVSShip = node1 instanceof Asteroid && node2 instanceof Ship;
                            // 2. Ship vs ship
                            boolean isShipVSShip = node1 instanceof Ship && node2 instanceof Ship;
                            // 3. Bullet vs asteroid
                            boolean isBulletVSAsteroid = node1 instanceof Bullet && node2 instanceof Asteroid;
                            boolean isAsteroidVSBullet = node1 instanceof Asteroid && node2 instanceof Bullet;
                            // 4. Bullet vs ship
                            boolean isBulletVSShip = node1 instanceof Bullet && node2 instanceof Ship;
                            boolean isShipVSBullet = node1 instanceof Ship && node2 instanceof Bullet;

                            if (isShipVSAsteroid || isAsteroidVSShip
                                    || isShipVSShip
                                    || isBulletVSAsteroid || isAsteroidVSBullet
                                    || isBulletVSShip || isShipVSBullet) {
                                // TODO fire an event, include the objects destroyed
                                System.out.println("Collision detected");
                                // destroy both objects
                                Destroyable destroyable1 = (Destroyable) node1;
                                Destroyable destroyable2 = (Destroyable) node2;
                                destroyable1.destroy();
                                destroyable2.destroy();
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
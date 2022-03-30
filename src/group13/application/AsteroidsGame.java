package group13.application;

import group13.application.characters.Bullet;
import group13.application.characters.asteroid.Asteroid;
import group13.application.characters.asteroid.LargeAsteroid;
import group13.application.characters.ship.EnemyShip;
import group13.application.characters.ship.PlayerShip;
import group13.application.characters.ship.Ship;
import group13.application.game.eventhandlers.CollisionEventHandler;
import group13.application.game.eventhandlers.DummyGameEventHandler;
import group13.application.game.eventhandlers.PlayerKeyEventHandler;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import static group13.application.common.Constants.*;

/**
 * Main program that starts the game.
 *
 * @author yulong
 */
public class AsteroidsGame extends Application {
    // Game level starts from GAME_LEVEL_START, which is 1
    private int gameLevel = GAME_LEVEL_START;

    private PlayerShip playerShip;

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
        largeAsteroid.Translate(0, 0, 700, 500, 80);

        // enemy ship should be able to move randomly, and shoot towards player ship
        // TODO enemy ship should be add into scene after a period of time
        Ship enemyShip = new EnemyShip(20, 20);

        // player ship should be able to move by keyboard, and can shoot bullets.
        playerShip = new PlayerShip(150, 100);

        // TODO the location of the player ship should be calculated based on the other objects in the scene
        // set the location of the player ship

        // Replaced StackPane with Pane
        Pane pane = new Pane(
                // add more asteroids
                largeAsteroid,
                // add enemy ship
//                enemyShip,
                // add player ship
                playerShip
        );
        stage.show();
        stage.setTitle("Asteroids");
        Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT, Color.WHITE);
        stage.setScene(scene);

        // register events for the scene
        scene.addEventFilter(COLLISION, new CollisionEventHandler());
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new DummyGameEventHandler(pane));
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new PlayerKeyEventHandler(playerShip));

        // Start the game Timeline, which is running forever
        // This is used to detect game events such as collision
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                boolean hasCollision = detectCollision(pane);
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
            for (int j = i + 1; j < observableList.size(); j++) {
                Node node2 = observableList.get(j);
                // detect collision by checking the overlap between two objects
                if (node1.getBoundsInParent().intersects(node2.getBoundsInParent())) {
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
                    }
                }
            }
        }
        return false;
    }
}
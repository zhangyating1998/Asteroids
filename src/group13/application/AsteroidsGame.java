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
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
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
        Ship enemyShip = new EnemyShip();

        // player ship should be able to move by keyboard, and can shoot bullets.
        playerShip = new PlayerShip();

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
//                System.out.println("The current frame number: " + i);
                // TODO detect collision
                boolean hasCollision = detectCollision(pane);
                if (hasCollision) {
                    // check
                }
            }
        };
        KeyFrame keyFrame = new KeyFrame(Duration.INDEFINITE);
        //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        timer.start();
    }

    /**
     A collision cause the objects involved to be destroyed.

     Types of collision:
     1. Ship vs asteroid
     2. Ship vs ship
     3. Bullet vs asteroid
     4. Bullet vs ship
    */
    private boolean detectCollision(StackPane pane) {
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
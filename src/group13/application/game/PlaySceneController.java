package group13.application.game;

import group13.application.characters.Bullet;
import group13.application.characters.Character;
import group13.application.characters.asteroid.Asteroid;
import group13.application.characters.ship.EnemyShip;
import group13.application.characters.ship.Ship;
import group13.application.game.events.CollisionEvent;
import group13.application.game.scene.PlayScene;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

import java.util.*;

import static group13.application.common.Constants.COLLISION;

/**
 * This class is a timer that will be used by the stage.
 * It controls the logic of the game.
 *
 * @author longyu
 * @date 2022/3/28 22:52
 */
public class PlaySceneController extends AnimationTimer {
    public static PlayScene playScene;

    // Hashmaps for storing player keyboard inputs
    public static Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
    public static Map<KeyCode, Boolean> onePressKeys = new HashMap<>();

    public PlaySceneController(PlayScene playScene) {
        PlaySceneController.playScene = playScene;

        // Capture on key pressed events with checks added to handle fire rete limit for firing bullets nd update hashmap
        PlaySceneController.playScene.setOnKeyPressed(event -> {
            if (pressedKeys.get(KeyCode.SPACE) == Boolean.FALSE) {
                pressedKeys.put(event.getCode(), Boolean.TRUE);
                onePressKeys.put(event.getCode(), Boolean.TRUE);
            } else {
                pressedKeys.put(event.getCode(), Boolean.TRUE);
            }
        });
        // Capture key released event and update hashmap
        PlaySceneController.playScene.setOnKeyReleased(event -> pressedKeys.put(event.getCode(), Boolean.FALSE));
    }

    @Override
    public void handle(long timeInNanoseconds) {
        // detect the collision and remove node if find any
        detectCollision(playScene.getPane());

        playScene.addAlienShips(timeInNanoseconds);

        // Control inputs for player-ship
        // Rotate the player-ship left
        if (Boolean.TRUE.equals(pressedKeys.getOrDefault(KeyCode.LEFT, false))) {
            playScene.playerShip.turnLeft();
        }
        // Rotate the player-ship right
        if (Boolean.TRUE.equals(pressedKeys.getOrDefault(KeyCode.RIGHT, false))) {
            playScene.playerShip.turnRight();
        }
        // Increase the player-ship velocity
        if (Boolean.TRUE.equals(pressedKeys.getOrDefault(KeyCode.UP, false))) {
            playScene.playerShip.accelerate(0.2);
        }
        // Fire a bullet from the player-ship, only 7 bullets can be alive at the one time to prevent spamming
        if (Boolean.TRUE.equals(onePressKeys.getOrDefault(KeyCode.SPACE, false))  && PlayScene.bullets.size() < 7) {
           playScene.playerShip.fire();
        }

        // to remove the bullets, we should collect them in the loop then delete all at once,
        // otherwise there will be concurrentModification issue.
        List<Character> bulletsToRemove = new ArrayList<>();
        // Also increments the timer for and character with lime to live limit  and removes them if this is exceeded
        for (Node node :  playScene.getPane().getChildren()) {
            if (node instanceof Character) {
                Character character = (Character) node;
                if (character.getIsTimeOut()) {
                    character.counter += 0.01666;
                    if (character.checkTimeOut()) {
                        bulletsToRemove.add(character);
                    }
                }
            }
        }

        PlayScene.bullets.removeAll(bulletsToRemove);
        playScene.getPane().getChildren().removeAll(bulletsToRemove);

        // move the Characters
        for (Node node :  playScene.getPane().getChildren()) {
            if (node instanceof Character) {
                ((Character) node).move();
            }
        }

        // Clear the one pressed keys hashmap
        onePressKeys.clear();
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
    private void detectCollision(Pane pane) {
        ObservableList<Node> observableList = pane.getChildren();
        for (int i = 0; i < observableList.size(); i++) {
            Node node1 = observableList.get(i);
            for (int j = i + 1; j < observableList.size(); j++) {
                Node node2 = observableList.get(j);
                // detect collision by checking the overlap between two objects, this is based on the x and y bound
                // only detect Characters, labels will not count
                if (node1 instanceof Character && node2 instanceof Character) {
                    Path path = (Path) Shape.intersect((Shape) node1, (Shape) node2);
                    // to precisely check the overlap by the shape of the node, we should count the number of common
                    // elements between the two nodes, a positive number means they overlap in shape
                    // reference: https://gist.github.com/james-d/8149902
                    if (path.getElements().size() > 0) {
                        // 1. Ship vs asteroid
                        boolean isShipVSAsteroid = node1 instanceof Ship && node2 instanceof Asteroid;
                        boolean isAsteroidVSShip = node1 instanceof Asteroid && node2 instanceof Ship;
                        // 2. Ship vs ship
                        boolean isShipVSShip = node1 instanceof Ship && node2 instanceof Ship;
                        // 3. Bullet vs asteroid
                        boolean isBulletVSAsteroid = node1 instanceof Bullet && node2 instanceof Asteroid;
                        boolean isAsteroidVSBullet = node1 instanceof Asteroid && node2 instanceof Bullet;
                        // 4. Bullet vs EnemyShip
                        boolean isBulletVSShip = node1 instanceof Bullet && node2 instanceof EnemyShip;
                        boolean isShipVSBullet = node1 instanceof EnemyShip && node2 instanceof Bullet;

                        if (isShipVSAsteroid || isAsteroidVSShip
                                || isShipVSShip
                                || isBulletVSAsteroid || isAsteroidVSBullet
                                || isBulletVSShip || isShipVSBullet) {
                            System.out.println("Collision detected");
                            pane.fireEvent(new CollisionEvent(COLLISION, pane, node1, node2));
                        }
                    }
                }
            }
        }
    }
}
package group13.application.game;

import group13.application.characters.Bullet;
import group13.application.characters.Character;
import group13.application.characters.asteroid.Asteroid;
import group13.application.characters.ship.Ship;
import group13.application.game.events.CollisionEvent;
import group13.application.game.scene.PlayScene;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

import static group13.application.common.Constants.COLLISION;

/**
 * This class is a timer that will be used by the stage.
 * It controls the logic of the game.
 *
 * @author longyu
 * @date 2022/3/28 22:52
 */
public class PlaySceneController extends AnimationTimer {
    private PlayScene playScene;

    public PlaySceneController(PlayScene playScene) {
        this.playScene = playScene;
    }

    @Override
    public void handle(long timeInNanoseconds) {
        // detect the collision and remove node if find any
        detectCollision(playScene.getPane());

        playScene.addAlienShips(timeInNanoseconds);

        // move the Characters
        for (Node node : playScene.getPane().getChildren()) {
            if (node instanceof Character) {
                Character character = (Character) node;
                character.move();
//                System.out.format("Current position:, %s\n", character.getCurrentPosition());
//                System.out.format("Future position:, %s\n", character.getFuturePosition());
            }
        }
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
                // only detect Characters, labels will not count
                if (node1 instanceof Character && node2 instanceof Character) {
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
                        // detect collision by checking the overlap between two objects, this is based on the x and y bound
                        Path path = (Path) Shape.intersect((Shape) node1, (Shape) node2);
                        // to precisely check the overlap by the shape of the node, we should count the number of common
                        // elements between the two nodes, a positive number means they overlap in shape
                        // reference: https://gist.github.com/james-d/8149902
                        if (path.getElements().size() > 0) {
                            System.out.println("Collision detected");
                            pane.fireEvent(new CollisionEvent(COLLISION, pane, node1, node2));
                            break;
                        }
                    }
                }
            }
        }
    }
}

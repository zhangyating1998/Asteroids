package group13.application.game;

import group13.application.characters.Bullet;
import group13.application.characters.Destroyable;
import group13.application.characters.asteroid.Asteroid;
import group13.application.common.events.CollisionEvent;
import group13.application.characters.ship.Ship;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import static group13.application.common.Constants.COLLISION;

/**
 * @author longyu
 * @date 2022/3/28 22:52
 */
public class GameAnimationController extends AnimationTimer {
    private Pane pane;

    public GameAnimationController(Pane pane) {
        this.pane = pane;
    }

    @Override
    public void handle(long l) {
        // TODO detect collision
        boolean hasCollision = detectCollision(pane);
        if (hasCollision) {
            // check
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
                                System.out.println("Collision detected");
//
//                                // destroy both objects
//                                Destroyable destroyable1 = (Destroyable) node1;
//                                Destroyable destroyable2 = (Destroyable) node2;
//                                destroyable1.destroy();
//                                destroyable2.destroy();

                                // THIS MUST HAPPEN BEFORE REMOVING FROM THE PANE! OTHERWISE EVENTS WON'T BE PROPAGATED!
                                // fire events for the collision
                                node1.fireEvent(new CollisionEvent(COLLISION));
                                node2.fireEvent(new CollisionEvent(COLLISION));

                                // remove destroyed objects from pane
                                System.out.println("Remove " + node1);
                                pane.getChildren().remove(node1);
                                System.out.println("Remove " + node2);
                                pane.getChildren().remove(node2);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}

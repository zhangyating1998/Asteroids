package group13.application.characters.ship;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Polygon;

/**
 * @author longyu
 * @date 2022/3/26 15:21
 */

// Create a triangular player ship polygon based on the ship class
public class PlayerShip extends Ship {
    public PlayerShip(int x, int y) {
        super(x, y, -15, -15, 30, 0, -15, 15);
    }

    // method to change the player-ship angle by -5 degrees
    public void turnLeft() {
        setRotate(getRotate() - 5);
    }

    // method to change the player-ship angle by +5 degrees
    public void turnRight() {
        setRotate(getRotate() + 5);
    }

}

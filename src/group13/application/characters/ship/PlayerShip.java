package group13.application.characters.ship;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Polygon;

/**
 * @author longyu
 * @date 2022/3/26 15:21
 */
public class PlayerShip extends Ship {
    public PlayerShip(int x, int y) {
        super(x, y, -15, -15, 30, 0, -15, 15);
    }

}

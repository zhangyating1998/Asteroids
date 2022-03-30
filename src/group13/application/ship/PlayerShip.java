package group13.application.ship;

import javafx.scene.shape.Polygon;

public class PlayerShip extends group13.application.Character {

    public PlayerShip(int x, int y) {
        super(new Polygon(-15, -15, 30, 0, -15, 15), x, y);
    }
}

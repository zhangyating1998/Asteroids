package group13.application.game.events.handlers;

import group13.application.characters.ship.PlayerShip;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author longyu
 * @date 2022/3/30 09:59
 */
public class PlayerKeyEventHandler implements EventHandler<KeyEvent> {
    private PlayerShip playerShip;

    public PlayerKeyEventHandler(PlayerShip playerShip) {
        this.playerShip = playerShip;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.LEFT) {
            playerShip.turnLeft();
        }
        if (keyEvent.getCode() == KeyCode.RIGHT) {
            playerShip.turnRight();
        }
        if (keyEvent.getCode() == KeyCode.UP) {
            playerShip.accelerate(0.5);
        }
    }
}

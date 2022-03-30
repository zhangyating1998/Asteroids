package group13.application.game.eventhandlers;

import group13.application.common.Constants;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * @author longyu
 * @date 2022/3/28 23:24
 */
public class DummyGameEventHandler implements EventHandler<KeyEvent> {
    private Pane pane;
    public DummyGameEventHandler(Pane pane) {
        this.pane = pane;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case E:
                System.out.println("End the game!");
                break;
            case P:
                System.out.println("fire Player ship destroyed.");
                pane.fireEvent(new Event(Constants.PLAYER_SHIP_DESTROYED));
            default:
                // do nothing
                break;
        }
    }
}

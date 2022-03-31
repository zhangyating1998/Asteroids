package group13.application.game.events.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * @author longyu
 * @date 2022/3/28 23:37
 */
public class DummyGameMouseEventHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.println(mouseEvent);
    }
}

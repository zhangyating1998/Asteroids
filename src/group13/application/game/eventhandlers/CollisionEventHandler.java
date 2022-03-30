package group13.application.game.eventhandlers;

import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * @author longyu
 * @date 2022/3/28 23:08
 */
public class CollisionEventHandler implements EventHandler {
    @Override
    public void handle(Event event) {
        System.out.println("collisionEvent caught:" + event.getSource());
        System.out.println("collisionEvent caught:" + event.getTarget());
    }
}

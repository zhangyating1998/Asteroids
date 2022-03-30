package group13.application.game.eventhandlers;

import group13.application.game.events.CollisionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * @author longyu
 * @date 2022/3/28 23:08
 */
public class CollisionEventHandler implements EventHandler<CollisionEvent> {
    @Override
    public void handle(CollisionEvent event) {
        System.out.println("collisionEvent caught:" + event.getSource());
        System.out.println("collisionEvent caught:" + event.getTarget());
        System.out.format("Node 1: %s, Node 2: %s\n", event.getNode1(), event.getNode2());
    }
}

package group13.application.game.events;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class CollisionEvent extends BaseEvent{
    private Node node1;
    private Node node2;

    public CollisionEvent(EventType<? extends Event> eventType, Pane pane,
                          Node node1, Node node2) {
        super(eventType, pane);
        this.node1 = node1;
        this.node2 = node2;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }
}

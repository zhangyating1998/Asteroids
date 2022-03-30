package group13.application.game.events;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class BaseEvent extends Event {
    private Pane pane;

    public BaseEvent(EventType<? extends Event> eventType, Pane pane) {
        super(eventType);
        this.pane = pane;
    }
}

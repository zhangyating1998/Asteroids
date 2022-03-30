package group13.application.common.events;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * @author longyu
 * @date 2022/3/27 15:29
 */
public class CollisionEvent extends Event {
    public CollisionEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    public CollisionEvent(Object o, EventTarget eventTarget, EventType<? extends Event> eventType) {
        super(o, eventTarget, eventType);
    }
}

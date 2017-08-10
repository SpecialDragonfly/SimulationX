package Engine;

import Engine.Events.Event;

public interface QueueListener {
    void handle(Event event);
}
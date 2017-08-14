package Engine;

import Engine.Events.Event;

public interface QueueListener {
    void handle(Event event) throws java.io.IOException;
}

package Engine;

import Engine.Events.Event;

/**
 * Wraps a Service/Sink/Source for the purposes of communicating with the Engine.
 */
public class EnvironmentObject {
    public void handle(Event event) {
        // Subscribed event has been added to the queue, handle it.
    }
}

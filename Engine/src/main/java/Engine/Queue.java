package Engine;

import Engine.Events.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Queue {
    private ConcurrentLinkedQueue<Event> tasks = null;
    private HashMap<String, Engine> subscribedEngines = null;
    private HashMap<String, ArrayList<ServiceDTO>> subscribedEnvironmentObjects = null;
    private HashMap<String, EngineStrategy> subscribedStrategies = null;

    public Queue() {
        this.tasks = new ConcurrentLinkedQueue<>();
        this.subscribedEngines = new HashMap<>();
        this.subscribedEnvironmentObjects = new HashMap<>();
        this.subscribedStrategies = new HashMap<>();
    }

    public void push(Event event) {
        System.out.println("An event of type " + event.getClass().getSimpleName() + " has been added to the queue");
        this.tasks.add(event);
        this.subscribedEngines.forEach(
            (subEvent, engine) -> {
                if (event.getClass().getSimpleName().equals(subEvent)) {
                    engine.handle(event);
                }
            }
        );

        this.subscribedStrategies.forEach(
            (subEvent, strategy) -> {
                if (event.getClass().getSimpleName().equals(subEvent)) {
                    try {
                        strategy.handle(event);
                    } catch (java.io.IOException e) {
                        System.out.println("Exception");
                    }
                }
            }
        );
    }

    public void subscribe(String eventName, Engine engine) {
        this.subscribedEngines.put(eventName, engine);
    }

    public void subscribe(String eventName, EngineStrategy strategy) {
        this.subscribedStrategies.put(eventName, strategy);
    }
}
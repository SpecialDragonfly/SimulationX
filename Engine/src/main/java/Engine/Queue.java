package Engine;

import Engine.Events.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class Queue {
    private ConcurrentLinkedQueue<Event> tasks = null;
    private HashMap<String, XEngine> subscribedEngines = null;
    private HashMap<String, ArrayList<ServiceDTO>> subscribedEnvironmentObjects = null;
    private HashMap<String, EngineStrategy> subscribedStrategies = null;

    @Autowired
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
                    strategy.handle(event);
                }
            }
        );
    }

    public void subscribe(String eventName, XEngine engine) {
        this.subscribedEngines.put(eventName, engine);
    }

    public void subscribe(String eventName, EngineStrategy strategy) {
        this.subscribedStrategies.put(eventName, strategy);
    }
}
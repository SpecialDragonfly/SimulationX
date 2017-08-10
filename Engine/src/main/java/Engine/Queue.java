package Engine;

import Engine.Events.Event;
import Engine.Events.RegisterEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Queue {
    private ConcurrentLinkedQueue<Event> tasks = null;
    private HashMap<String, Engine> subscribedEngines = null;
    private HashMap<String, ArrayList<EnvironmentObject>> subscribedEnvironmentObjects = null;
    private HashMap<String, EngineStrategy> subscribedStrategies = null;

    public Queue() {
        this.tasks = new ConcurrentLinkedQueue<>();
        this.subscribedEngines = new HashMap<>();
        this.subscribedEnvironmentObjects = new HashMap<>();
        this.subscribedStrategies = new HashMap<>();
    }

    public void push(Event event) {
        this.tasks.add(event);
        this.subscribedEngines.forEach(
            (subEvent, engine) -> {
                if (event.getClass().getSimpleName().equals(subEvent)) {
                    engine.handle(event);
                }
            }
        );
        this.subscribedEnvironmentObjects.forEach(
            (subEvent, environmentObjects) -> {
                if (event.getClass().getSimpleName().equals(subEvent)) {
                    environmentObjects.forEach(o -> o.handle(event));
                }
            }
        );
    }

    public void subscribe(String eventName, Engine engine) {
        this.subscribedEngines.put(eventName, engine);
    }

    public void subscribe(String eventName, EnvironmentObject object) {
        if (this.subscribedEnvironmentObjects.containsKey(eventName)) {
            ArrayList<EnvironmentObject> objects = this.subscribedEnvironmentObjects.get(eventName);
            objects.add(object);
            this.subscribedEnvironmentObjects.put(eventName, objects);
        } else {
            ArrayList<EnvironmentObject> a = new ArrayList<EnvironmentObject>();
            a.add(object);
            this.subscribedEnvironmentObjects.put(eventName, a);
        }
    }

    public void subscribe(String eventName, EngineStrategy strategy) {
        this.subscribedStrategies.put(eventName, strategy);
    }
    
    public void add(RegisterEvent event) {

    }
}
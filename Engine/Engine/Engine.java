package Engine;

import Engine.Events.Event;

import static java.lang.Thread.sleep;

class Engine {

    private final Queue queue;
    private final SimpleEngineStrategy engineStrategy;
    private long tickLimit = 0;

    public static void main(String[] args) {
        Engine engine = new Engine(100);

        try {
            engine.start();
        } catch (InterruptedException ex) {
            // Do nothing
        }
    }

    public Engine(long tickLimit) {
        this.queue = new Queue();
        this.tickLimit = tickLimit;
        this.engineStrategy = new SimpleEngineStrategy();
    }

    public void start() throws InterruptedException {
        long count = 0;
        while (count < this.tickLimit) {
            // Do all Environment Objects still respond when poked?
            this.engineStrategy.verifyObjects();

            // Do we have any more environment objects that need to be instantiated (or removed)
            this.engineStrategy.update();

            // We've ticked, so tell anything that cares.
            this.queue.push(new Engine.Events.TickEvent());
            sleep(1);
            count++;
        }
    }

    public void handle(Event event) {
        // The subscribed event has been added to the queue - handle it.
    }
}
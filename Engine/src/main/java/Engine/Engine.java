package Engine;

import Engine.Events.Event;
import Engine.Servlets.GetDetailsServlet;
import org.eclipse.jetty.server.Server;

import static java.lang.Thread.sleep;

public class Engine implements Runnable {

    private final Queue queue;
    private final SimpleEngineStrategy engineStrategy;
    private long tickLimit = 0;

    public static void main(String[] args) {
        Engine engine = new Engine(100);
        engine.run();
    }

    public Engine(long tickLimit) {
        this.queue = new Queue();
        this.tickLimit = tickLimit;
        this.engineStrategy = new SimpleEngineStrategy();
    }

    public void handle(Event event) {
        // The subscribed event has been added to the queue - handle it.
        System.out.println("The engine ticked!");
    }

    public EngineStrategy getEngineStrategy() {
        return this.engineStrategy;
    }

    @Override
    public void run() {
        this.queue.subscribe("TickEvent", this);
        StatusServer statusServer = new StatusServer(new Server(8000));
        statusServer.addServlet(new GetDetailsServlet(this), "/status/*");
        statusServer.run();

        long count = 0;
        while (count < this.tickLimit) {
            // Do all Environment Objects still respond when poked?
            this.engineStrategy.verifyObjects();

            // Do we have any more environment objects that need to be instantiated (or removed)
            this.engineStrategy.update();

            // We've ticked, so tell anything that cares.
            this.queue.push(new Engine.Events.TickEvent());
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }
}
package Engine;

import Engine.Events.Event;
import Engine.Events.TickEvent;
import Engine.Mapping.Mapper;
import Engine.Servlets.GetDetailsServlet;
import Engine.Servlets.RegisterServlet;
import org.eclipse.jetty.server.Server;

import static java.lang.Thread.sleep;

public class Engine implements Runnable, QueueListener {

    private final Queue queue;
    private final Queue registrationQueue;
    private final EngineStrategy engineStrategy;
    private long tickLimit = 0;

    public static void main(String[] args) {
        Engine engine = new Engine(100);
        engine.run();
    }

    public Engine(long tickLimit) {
        this.queue = new Queue();
        this.registrationQueue = new Queue();
        this.tickLimit = tickLimit;
        this.engineStrategy = new SimpleEngineStrategy(new Mapper(3, 3, 0));
    }

    public void handle(Event event) {
        // The subscribed event has been added to the queue - handle it.
        System.out.println("The engine ticked!");
    }

    @Override
    public void run() {
        this.queue.subscribe("TickEvent", this);
        this.registrationQueue.subscribe("RegisterEvent", this.engineStrategy);

        StatusServer statusServer = new StatusServer(new Server(8005));
        statusServer.addServlet(new GetDetailsServlet(this.engineStrategy), "/status");
        statusServer.addServlet(new RegisterServlet(this.registrationQueue), "/register");
        statusServer.run();

        long count = 0;
        while (count < this.tickLimit) {
            // Do all PlanarEnvironment Objects still respond when poked?
            this.engineStrategy.verifyObjects();

            // We've ticked, so tell anything that cares.
            this.queue.push(new TickEvent());
            try {
                sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }
}
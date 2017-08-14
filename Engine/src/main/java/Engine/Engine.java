package Engine;

import Engine.Events.Event;
import Engine.Events.TickEvent;
import Engine.Mapping.ISource;
import Engine.Mapping.Mapper;
import Engine.Servlets.GetDetailsServlet;
import Engine.Servlets.GetStatusServlet;
import Engine.Servlets.RegisterServlet;
import org.eclipse.jetty.server.Server;

import java.util.ArrayList;

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
        ArrayList<ISource> sources = new ArrayList<>();
        sources.add(new SourceDTO("wood", 100));
        this.engineStrategy = new SimpleEngineStrategy(new Mapper(3, 3, 0), sources, this.queue);
    }

    public void handle(Event event) {
        // The subscribed event has been added to the queue - handle it.
        System.out.println("The engine ticked!");
    }

    @Override
    public void run() {
        this.queue.subscribe("TickEvent", this);
        this.queue.subscribe("TickEvent", this.engineStrategy);
        this.queue.subscribe("MoveEvent", this.engineStrategy);
        this.queue.subscribe("CollisionEvent", this.engineStrategy);
        this.queue.subscribe("UpdateActorEvent", this.engineStrategy);
        this.registrationQueue.subscribe("RegisterEvent", this.engineStrategy);

        StatusServer statusServer = new StatusServer(new Server(8005));
        statusServer.addServlet(new GetDetailsServlet(this.engineStrategy), "/status");
        statusServer.addServlet(new RegisterServlet(this.registrationQueue), "/register");
        statusServer.addServlet(new GetStatusServlet(this.engineStrategy), "/x/*");
        statusServer.run();

        long count = 0;
        while (count < this.tickLimit) {
            // We've ticked, so tell anything that cares.
            this.queue.push(new TickEvent());
            try {
                sleep(1000 * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }
}
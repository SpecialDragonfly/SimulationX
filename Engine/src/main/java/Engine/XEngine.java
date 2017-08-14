package Engine;

import Engine.Events.Event;
import Engine.Events.TickEvent;
import Engine.Mapping.Mapper;
import Engine.Servlets.GetDetailsServlet;
import Engine.Servlets.RegisterServlet;
import org.eclipse.jetty.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Thread.sleep;

@Service
public class XEngine implements Runnable, QueueListener {

    private  Queue queue;
    private  Queue registrationQueue;
    private final EngineStrategy engineStrategy;
    private long tickLimit = 0;

    @Autowired
    public XEngine(Queue queue, Queue registrationQueue) {
        this.queue = queue;
        this.registrationQueue = registrationQueue;
        this.tickLimit = 100;
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
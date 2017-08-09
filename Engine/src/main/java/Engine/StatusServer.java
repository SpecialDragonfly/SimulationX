package Engine;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;

public class StatusServer implements Runnable {

    private final ServletContextHandler servletContextHandler;
    private Server server;

    public StatusServer(Server server) {
        this.server = server;
        this.servletContextHandler = new ServletContextHandler(this.server, "/view");
    }

    public void addServlet(Servlet servlet, String pathSpec) {
        this.servletContextHandler.addServlet(new ServletHolder(servlet), pathSpec);
    }

    @Override
    public void run() {

        Thread t = new Thread(() -> {
            server.setHandler(servletContextHandler);
            try {
                server.start();
                server.dump();
                server.join();
            } catch (Exception ex) {
                // do nothing
            }
        });
        t.start();
    }
}

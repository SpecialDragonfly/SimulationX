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
        this.servletContextHandler = new ServletContextHandler(this.server, "/");
    }

    public void addServlet(Servlet servlet, String pathSpec) {
        this.servletContextHandler.addServlet(new ServletHolder(servlet), pathSpec);
    }

    public void addServlet(Class<? extends Servlet> servlet, String pathSpec) {
        this.servletContextHandler.addServlet(servlet, pathSpec);
    }

    @Override
    public void run() {

        Thread t = new Thread(() -> {
            server.setHandler(this.servletContextHandler);
            try {
                server.start();
                server.dumpStdErr();
                server.join();
            } catch (Exception ex) {
                // do nothing
            }
        });
        t.start();
    }
}

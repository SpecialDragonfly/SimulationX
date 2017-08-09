package Engine.Servlets;

import Engine.Engine;
import Engine.EngineStrategy;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetDetailsServlet extends HttpServlet {
    private Engine engine;

    public GetDetailsServlet(Engine engine) {
        this.engine = engine;
    }

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");

        resp.getWriter().print(this.getStatus());
    }

    private String getStatus() {
        EngineStrategy strategy = (EngineStrategy) this.engine.getEngineStrategy();
        ArrayList<ServiceDTO> serviceList= strategy.getServiceDTOArray();

        JSONObject json = new JSONObject();
        json.append("hello", "world");
        return json.toString();
    }
}

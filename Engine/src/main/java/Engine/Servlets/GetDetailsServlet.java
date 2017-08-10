package Engine.Servlets;

import Engine.EngineStrategy;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class GetDetailsServlet extends HttpServlet {
    private Engine.EngineStrategy strategy;

    public GetDetailsServlet(EngineStrategy strategy) {
        this.strategy = strategy;
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
        ArrayList serviceList = this.strategy.getServiceDTOArray();

        JSONObject json = new JSONObject();
        json.append("services", serviceList);
        return json.toString();
    }
}

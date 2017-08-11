package Engine.Servlets;

import Engine.EngineStrategy;
import Engine.Mapping.IMappedService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class GetStatusServlet extends HttpServlet {
    private EngineStrategy strategy;

    public GetStatusServlet(EngineStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.addHeader("Access-Control-Allow-Origin", "*");

        System.out.println(req.getPathInfo());

        String response;
        try {
            IMappedService service = this.strategy.getServiceByUUID(req.getPathInfo().substring(1));
            response = this.serviceStatusSuccess(service);
        } catch (Exception ex) {
            response = this.errorResponse(ex.getMessage());
        }

        resp.getWriter().print(response);
    }

    private String errorResponse(String error) {
        JSONObject status = new JSONObject();
        status.put("error", error);
        return status.toString();
    }

    private String serviceStatusSuccess(IMappedService service) {
        JSONObject status = new JSONObject();
        HashMap<HashMap<String, String>, Integer> resources = service.getResourceMap();
        resources.forEach((resource, exchangeRate) -> {
            JSONObject item = new JSONObject();
            resource.forEach((input, output) -> {
                item.put("input", input).put("output", output);
            });
            item.put("exchange_rate", exchangeRate);
            item.put("volume", 0);
            status.append("inventory", item);
        });

        return status.toString();
    }
}

package Engine.Servlets;

import Engine.EngineStrategy;
import Engine.Mapping.IMappedService;
import Engine.Mapping.IMappedSink;
import Engine.Mapping.IMappedSource;
import Engine.Mapping.Mapper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
        resp.addHeader("Access-Control-Allow-Origin", "*");

        resp.getWriter().print(this.getStatus());
    }

    private String getStatus() {
        Mapper map = (Mapper) this.strategy.getMapper();
        ArrayList<IMappedSource> sources = map.getSources();
        ArrayList<IMappedSink> sinks = map.getSinks();
        ArrayList<IMappedService> services = map.getServices();

        JSONObject mapDetails = new JSONObject();
        mapDetails.put("width", map.getWidth());
        mapDetails.put("depth", map.getDepth());
        mapDetails.put("height", map.getHeight());

        JSONArray serviceArray = new JSONArray();
        sources.forEach(s -> {
            HashMap<String, Integer> coordinates = s.getCoOrdinates();
            JSONObject serviceObject = new JSONObject();
            serviceObject.put("id", s.getUUID().toString());
            serviceObject.put("type", "source");
            serviceObject.put("x", coordinates.get("x"));
            serviceObject.put("y", coordinates.get("y"));
            serviceObject.put("z", coordinates.get("z"));
            serviceArray.put(serviceObject);
        });

        sinks.forEach(s -> {
            HashMap<String, Integer> coordinates = s.getCoOrdinates();
            JSONObject serviceObject = new JSONObject();
            serviceObject.put("id", s.getUUID().toString());
            serviceObject.put("type", "sink");
            serviceObject.put("x", coordinates.get("x"));
            serviceObject.put("y", coordinates.get("y"));
            serviceObject.put("z", coordinates.get("z"));
            serviceArray.put(serviceObject);
        });

        services.forEach(s -> {
            HashMap<String, Integer> coordinates = s.getCoOrdinates();
            JSONObject serviceObject = new JSONObject();
            serviceObject.put("id", s.getUUID().toString());
            serviceObject.put("type", "service");
            serviceObject.put("x", coordinates.get("x"));
            serviceObject.put("y", coordinates.get("y"));
            serviceObject.put("z", coordinates.get("z"));
            serviceArray.put(serviceObject);
        });

        // TODO: Actors
        /*
            actors: [
             { x, y, z },
             { ... }
            ],
            current_actor: {
              id: uuid,
              x, y, z
              bucket
            }
         */

        JSONObject json = new JSONObject();
        json.put("map", mapDetails);
        json.put("services", serviceArray);
        return json.toString();
    }
}

package Engine.Servlets;

import Engine.EngineStrategy;
import Engine.ExchangeItem;
import Engine.Mapping.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

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

        resp.getWriter().print(this.getStatus(req.getParameter("id")));
    }

    private String getStatus(String actorId) {
        Mapper map = (Mapper) this.strategy.getMapper();
        MappedActor actor = map.getActor(actorId);
        JSONObject json = new JSONObject();
        json.put("x", actor.getCoOrdinates().get("x"));
        json.put("y", actor.getCoOrdinates().get("y"));
        json.put("z", actor.getCoOrdinates().get("z"));

        JSONArray bucketArray = new JSONArray();
        for (HashMap.Entry<String, Integer> entry : actor.getBucket().entrySet()) {
            JSONObject bucketItem = new JSONObject();
            bucketItem.put("name", entry.getKey());
            bucketItem.put("amount", entry.getValue());
            bucketArray.put(bucketItem);
        }


        ArrayList<IMappedSource> sources = map.getSources();
        ArrayList<IMappedSink> sinks = map.getSinks();
        ArrayList<IMappedService> services = map.getServices();
        HashMap<String, MappedActor> actors = map.getActors();

        JSONObject mapDetails = new JSONObject();
        mapDetails.put("width", map.getWidth());
        mapDetails.put("depth", map.getDepth());
        mapDetails.put("height", map.getHeight());

        JSONArray sourceArray = new JSONArray();
        sources.forEach(s -> {
            HashMap<String, Integer> coordinates = s.getCoOrdinates();
            JSONObject sourceObject = new JSONObject();
            sourceObject.put("id", s.getUUID().toString());

            sourceObject.put("resource", s.getResourceType());
            sourceObject.put("amount", s.getamount());
            sourceObject.put("x", coordinates.get("x"));
            sourceObject.put("y", coordinates.get("y"));
            sourceObject.put("z", coordinates.get("z"));
            sourceArray.put(sourceObject);
        });

        // Sinks not in use and need work

//        JSONArray sinkArray = new JSONArray();
//        sinks.forEach(s -> {
//            HashMap<String, Integer> coordinates = s.getCoOrdinates();
//            JSONObject sinkObject = new JSONObject();
//            sinkObject.put("id", s.getUUID().toString());
//            sinkObject.put("type", "sink");
//            sinkObject.put("x", coordinates.get("x"));
//            sinkObject.put("y", coordinates.get("y"));
//            sinkObject.put("z", coordinates.get("z"));
//            sinkArray.put(sinkObject);
//        });

        JSONArray serviceArray = new JSONArray();
        services.forEach(s -> {
            HashMap<String, Integer> coordinates = s.getCoOrdinates();
            JSONObject serviceObject = new JSONObject();
            serviceObject.put("id", s.getUUID().toString());
            serviceObject.put("type", "service");
            serviceObject.put("x", coordinates.get("x"));
            serviceObject.put("y", coordinates.get("y"));
            serviceObject.put("z", coordinates.get("z"));

            ExchangeItem exchangeItem = s.getResourceMap().get(0);
            serviceObject.put("buy", exchangeItem.getInput());
            serviceObject.put("sell", exchangeItem.getOutput());
            serviceObject.put("amount", exchangeItem.getExchangeRate());
            serviceArray.put(serviceObject);
        });

        JSONArray actorArray = new JSONArray();

        for (HashMap.Entry<String, MappedActor> entry : actors.entrySet()) {
            if(entry.getKey().equals(actorId)) {
                continue;
            }

            MappedActor mappedActor = entry.getValue();
            HashMap<String, Integer> coordinates = mappedActor.getCoOrdinates();
            JSONObject actorObject = new JSONObject();
            actorObject.put("x", coordinates.get("x"));
            actorObject.put("y", coordinates.get("y"));
            actorObject.put("z", coordinates.get("z"));
            actorArray.put(actorObject);
        }

        json.put("map", mapDetails);
        json.put("services", serviceArray);
        json.put("actors", actorArray);
        json.put("sources", sourceArray);
        return json.toString();
    }
}

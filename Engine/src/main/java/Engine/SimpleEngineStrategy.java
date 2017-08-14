package Engine;

import Engine.Events.Event;
import Engine.Events.RegisterEvent;
import Engine.Events.TickEvent;
import Engine.Events.RegisterActorEvent;
import Engine.Mapping.IMappedService;
import Engine.Mapping.MappedActor;
import Engine.Mapping.IMapper;
import Engine.Mapping.IService;
import Engine.Mapping.ISource;
import org.json.JSONArray;
import org.json.JSONObject;
import Engine.Events.MoveEvent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;

/**
 * Creates a singular instance of any Sink/Source/Service that it knows about
 */
public class SimpleEngineStrategy implements EngineStrategy {

    private final IMapper mapper;
    private ArrayList<ServiceDTO> serviceDTOArray;

    public SimpleEngineStrategy(IMapper mapper, ArrayList<ISource> sources) {
        this.mapper = mapper;
        sources.forEach(s -> {
            this.mapper.addSource(s, 3);
        });
        serviceDTOArray = new ArrayList<ServiceDTO>();
    }

    public void verifyObjects() {
        // Verify that Sinks/Sources/Services still exist.
        ArrayList<ServiceDTO> aliveServices = new ArrayList<>();
        this.serviceDTOArray.forEach(x -> {
            boolean alive = false;
            try {
                alive = this.doHealthCheck("http://" + x.getHealthcheckUrl());
            } catch (IOException ex) {
                System.out.println("An exception happened.");
                System.out.println(ex.getMessage());
            }

            if (alive) {
                aliveServices.add(x);
            } else {
                this.mapper.removeService(x);
            }
        });
        this.serviceDTOArray = aliveServices;
    }

    public void update(IService serviceDTO) {
        // If any new Sinks/Source/Service have registered themselves then make a singular(?) instance of them.
        this.mapper.addService(serviceDTO, 1);
    }

    @Override
    public IMapper getMapper() {
        return this.mapper;
    }

    @Override
    public IMappedService getServiceByUUID(String uuid) throws Exception {
        return this.mapper.getServiceByUUID(uuid);
    }

    public void handle(Event event) throws java.io.IOException {
        if (event instanceof RegisterEvent) {
            String serviceJSON = event.getMessage();
            System.out.println(serviceJSON);
            JSONObject jsonObject = new JSONObject(serviceJSON);
            String actionUrl = jsonObject.getString("action_url");
            String statusUrl = jsonObject.getString("status_url");
            String healthcheckUrl = jsonObject.getString("healthcheck_url");

            ArrayList<ExchangeItem> resourceMap = new ArrayList<>();

            JSONArray resourceArray = jsonObject.getJSONArray("resources");
            for (int i = 0; i < resourceArray.length(); i++) {
                String input = resourceArray.getJSONObject(i).getString("input");
                String output = resourceArray.getJSONObject(i).getString("output");
                int exchangeRate = resourceArray.getJSONObject(i).getInt("exchange_rate");
                ExchangeItem exchange = new ExchangeItem(input, output, exchangeRate);
                resourceMap.add(exchange);
            }

            ServiceDTO service = new ServiceDTO(actionUrl, statusUrl, healthcheckUrl, resourceMap);
            this.serviceDTOArray.add(service);
            this.update(service);
        } else if (event instanceof TickEvent) {
            this.verifyObjects();
        } else if (event instanceof RegisterActorEvent) {
            RegisterActorEvent actorEvent = (RegisterActorEvent)event;
            String actorJSON = event.getMessage();
            HttpServletResponse actorResponse = actorEvent.getResponse();
            System.out.println(actorJSON);
            JSONObject jsonObject = new JSONObject(actorJSON);

            MappedActor actor = mapper.addActor(
                jsonObject.getString("name"), (Integer)jsonObject.getInt("bucket_capacity")
            );

            actorResponse.setStatus(HttpServletResponse.SC_OK);
            actorResponse.setContentType("application/json");
            actorResponse.addHeader("Access-Control-Allow-Origin", "*");
            JSONObject json = new JSONObject();
            HashMap<String, Integer> actorCoordinates = actor.getCoOrdinates();
            json.put("id", actor.getUUID());
            json.put("x", actorCoordinates.get("x"));
            json.put("y", actorCoordinates.get("y"));
            json.put("z", actorCoordinates.get("z"));
            actorResponse.getWriter().print(json.toString());
        } else if(event instanceof MoveEvent) {
            String actorJSON = event.getMessage();
            System.out.print("Actor moved");
            JSONObject jsonObject = new JSONObject(actorJSON);
            Event movedEvent = mapper.moveActor(
                jsonObject.getString("id"),
                jsonObject.getInt("x"),
                jsonObject.getInt("y"),
                jsonObject.getInt("z")
            );
        }
    }

    private boolean doHealthCheck(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        int responseCode = connection.getResponseCode();
        return responseCode == 200;
    }
}

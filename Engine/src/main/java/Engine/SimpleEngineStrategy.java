package Engine;

import Engine.Events.Event;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Creates a singular instance of any Sink/Source/Service that it knows about
 */
public class SimpleEngineStrategy implements EngineStrategy {

    private ArrayList<ServiceDTO> serviceDTOArray = new ArrayList<ServiceDTO>();
    private Environment environment = new PlanarEnvironment();

    public SimpleEngineStrategy(Environment environment) {
        this.environment = environment;
    }

    public void verifyObjects() {
        ArrayList<ServiceDTO> aliveServices = new ArrayList<>();
        this.serviceDTOArray.forEach(x -> {
            boolean alive = this.doHealthCheck(x.getHealthcheckUrl());
            if (alive) {
                aliveServices.add(x);
            }
        });
        this.serviceDTOArray = aliveServices;
    }

    public void update() {
        // If any new Sinks/Source/Service have registered themselves then make a singular instance of them.
        ServiceDTO service = new ServiceDTO("a", "b", "c", new HashMap<>());
        this.environment.add(service);
    }

    public ArrayList<ServiceDTO> getServiceDTOArray() {
        return this.serviceDTOArray;
    }

    public void handle(Event event) {
        String serviceJSON = event.getMessage();

        JSONObject jsonObject = new JSONObject(serviceJSON);
        String actionUrl = jsonObject.getString("action_url");
        String statusUrl = jsonObject.getString("status_url");
        String healthcheckUrl = jsonObject.getString("healthcheck_url");
        HashMap<String, String> resourceMap = new HashMap<>();
        JSONArray resourceArray = jsonObject.getJSONArray("resources");
        for (int i = 0; i < resourceArray.length(); i++) {
            String input = resourceArray.getJSONObject(i).getString("input");
            String output = resourceArray.getJSONObject(i).getString("output");
            resourceMap.put(input, output);
        }

        this.serviceDTOArray.add(new ServiceDTO(actionUrl, statusUrl, healthcheckUrl, resourceMap));
    }

    private boolean doHealthCheck(String url) {
        // Calls the url.

        return true;
    }
}

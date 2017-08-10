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

    public void verifyObjects() {
        // Verify that Sinks/Sources/Services still exist.
    }

    public void update() {
        // If any new Sinks/Source/Service have registered themselves then make a singular instance of them.
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
}

package Engine;

import Engine.Events.Event;
import Engine.Mapping.IMapper;
import Engine.DTO.IService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Creates a singular instance of any Sink/Source/Service that it knows about
 */
public class SimpleEngineStrategy implements EngineStrategy {

    private final IMapper mapper;
    private ArrayList<ServiceDTO> serviceDTOArray = new ArrayList<ServiceDTO>();
    private ArrayList<String> resourceTypes = new ArrayList<>();

    public SimpleEngineStrategy(IMapper mapper) {
        this.mapper = mapper;
    }

    public void verifyObjects() {
        // Verify that Sinks/Sources/Services still exist.
        ArrayList<ServiceDTO> aliveServices = new ArrayList<>();
        this.serviceDTOArray.forEach(x -> {
            boolean alive = this.doHealthCheck(x.getHealthcheckUrl());
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

    public ArrayList<ServiceDTO> getServiceDTOArray() {
        return this.serviceDTOArray;
    }

    public void handle(Event event) {
        String serviceJSON = event.getMessage();

        JSONObject jsonObject = new JSONObject(serviceJSON);
        String actionUrl = jsonObject.getString("action_url");
        String statusUrl = jsonObject.getString("status_url");
        String healthcheckUrl = jsonObject.getString("healthcheck_url");
        HashMap<String, String> resourceMap = this.getServiceExchangeMap(jsonObject);
        JSONArray resourceArray = jsonObject.getJSONArray("resources");
        for (int i = 0; i < resourceArray.length(); i++) {
            String input = resourceArray.getJSONObject(i).getString("input");
            String output = resourceArray.getJSONObject(i).getString("output");
            resourceMap.put(input, output);
        }

        ServiceDTO service = new ServiceDTO(actionUrl, statusUrl, healthcheckUrl, resourceMap);
        this.serviceDTOArray.add(service);
        this.update(service);


    }

    private HashMap<String, String> getServiceExchangeMap(JSONObject service) {
        HashMap<String, String> exchangeMap = new HashMap<>();
        JSONArray exchangeData = service.getJSONArray("exchanges");
        for (int i = 0; i < exchangeData.length(); i++) {
            JSONObject exchange = exchangeData.getJSONObject(i);
            JSONObject input = exchange.getJSONObject("in");
            JSONObject output = exchange.getJSONObject("out");
            String inputType = input.get("type").toString();
            String outputType = output.get("type").toString();
            if (!this.resourceTypes.contains(inputType)) {
                continue;
            }
            if (!this.resourceTypes.contains(outputType)) {
                this.resourceTypes.add(outputType);
            }
            exchangeMap.put(inputType,outputType);
        }

        return exchangeMap;
    }

    private boolean doHealthCheck(String url) {
        // Calls the url.

        return true;
    }
}

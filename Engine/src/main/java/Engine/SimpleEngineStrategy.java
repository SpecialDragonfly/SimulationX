package Engine;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Creates a singular instance of any Sink/Source/Service that it knows about
 */
public class SimpleEngineStrategy implements EngineStrategy {

    private ArrayList<ServiceDTO> serviceDTOArray = new ArrayList<ServiceDTO>();
    private ArrayList<String> resourceTypes = new ArrayList<>();

    public SimpleEngineStrategy(ArrayList<String> resourceTypes) {
        this.resourceTypes = resourceTypes;
    }

    public void verifyObjects() {
        // Verify that Sinks/Sources/Services still exist.
    }

    public void update(IService serviceDTO) {
        // If any new Sinks/Source/Service have registered themselves then make a singular(?) instance of them.
    }

    public ArrayList<ServiceDTO> getServiceDTOArray() {
        return this.serviceDTOArray;
    }

    public void handleServiceRegistration(String serviceRequest) {
        JSONObject service = new JSONObject(serviceRequest);
        ServiceDTO serviceDTO = new ServiceDTO(
                service.get("actionUrl").toString(),
                service.get("statusUrl").toString(),
                service.get("healthUrl").toString(),
                getServiceExchangeMap(service)
        );
        this.serviceDTOArray.add(
            serviceDTO
        );
        this.update(serviceDTO);
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
}

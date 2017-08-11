package Engine;

import Engine.Events.Event;
import Engine.Mapping.IMapper;
import Engine.Mapping.IService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Creates a singular instance of any Sink/Source/Service that it knows about
 */
public class SimpleEngineStrategy implements EngineStrategy {

    private final IMapper mapper;
    private ArrayList<ServiceDTO> serviceDTOArray;

    public SimpleEngineStrategy(IMapper mapper) {
        this.mapper = mapper;
        serviceDTOArray = new ArrayList<ServiceDTO>();
    }

    public void verifyObjects() {
        // Verify that Sinks/Sources/Services still exist.
        ArrayList<ServiceDTO> aliveServices = new ArrayList<>();
        this.serviceDTOArray.forEach(x -> {
            boolean alive = false;
            try {
                alive = this.doHealthCheck(x.getHealthcheckUrl());
            } catch (IOException ex) {
                // Do nothing
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

    public void handle(Event event) {
        System.out.println("The Simple Engine strategy has taken an item off the queue");
        String serviceJSON = event.getMessage();
        System.out.println(serviceJSON);
        JSONObject jsonObject = new JSONObject(serviceJSON);
        String actionUrl = jsonObject.getString("action_url");
        String statusUrl = jsonObject.getString("status_url");
        String healthcheckUrl = jsonObject.getString("healthcheck_url");

        HashMap<String, String> resourceMap = new HashMap<>(); //this.getServiceExchangeMap(jsonObject);
        HashMap<HashMap<String, String>, Integer> serviceExchangeRates = new HashMap<>();

        JSONArray resourceArray = jsonObject.getJSONArray("resources");
        for (int i = 0; i < resourceArray.length(); i++) {
            String input = resourceArray.getJSONObject(i).getString("input");
            String output = resourceArray.getJSONObject(i).getString("output");
            int exchangeRate = resourceArray.getJSONObject(i).getInt("exchange_rate");
            resourceMap.put(input, output);
            serviceExchangeRates.put(resourceMap, exchangeRate);
        }

        ServiceDTO service = new ServiceDTO(actionUrl, statusUrl, healthcheckUrl, serviceExchangeRates);
        this.serviceDTOArray.add(service);
        this.update(service);
    }

    private boolean doHealthCheck(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        return connection.getResponseCode() == 200;
    }
}

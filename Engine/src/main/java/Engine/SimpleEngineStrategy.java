package Engine;

import Engine.Events.*;
import Engine.Mapping.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Creates a singular instance of any Sink/Source/Service that it knows about
 */
public class SimpleEngineStrategy implements EngineStrategy {

    private final IMapper mapper;
    private final ArrayList<ISource> sources;
    private final Queue queue;
    private ArrayList<ServiceDTO> serviceDTOArray;

    public SimpleEngineStrategy(IMapper mapper, ArrayList<ISource> sources, Queue queue) {
        this.mapper = mapper;
        this.sources = sources;
        this.queue = queue;
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

    public void handle(Event event) {
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
        } else if(event instanceof MoveEvent) {
            String serviceJSON = event.getMessage();
            System.out.println(serviceJSON);
            JSONObject jsonObject = new JSONObject(serviceJSON);
            String actorId = jsonObject.getString("id");
            Integer x = jsonObject.getInt("x");
            Integer y = jsonObject.getInt("y");
            Integer z = jsonObject.getInt("z");
            this.queue.push(
                    this.mapper.moveActor(actorId, x, y, z)
            );
        } else if (event instanceof CollisionEvent) {
            String serviceJSON = event.getMessage();
            System.out.println(serviceJSON);
            JSONObject jsonObject = new JSONObject(serviceJSON);
            JSONObject hit = jsonObject.getJSONObject("hit");
            if(hit.getString("type").equals("service")) {
                IMappedService service = this.mapper.getServiceByUUID(jsonObject.getString("id"));
                try {
                    this.doServiceBucketPost(service.getActionUrl(), event);
                } catch (IOException exception) {

                }

            }

        } else if(event instanceof UpdateActorEvent) {
            String serviceJSON = event.getMessage();
            System.out.println(serviceJSON);
            JSONObject jsonObject = new JSONObject(serviceJSON);
            String actorId = jsonObject.getString("id");
            JSONArray bucketArray = jsonObject.getJSONArray("bucket");
            HashMap<String, Integer> bucket = new HashMap<>();
            for (int i = 0; i < bucketArray.length(); i++) {
                String resource = bucketArray.getJSONObject(i).getString("type");
                Integer amount = bucketArray.getJSONObject(i).getInt("amount");
                bucket.put(resource, amount);
            }

            MappedActor actor = this.mapper.getActor(actorId);
            actor.setBucket(bucket);
        }
    }

    private boolean doHealthCheck(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        int responseCode = connection.getResponseCode();
        return responseCode == 200;
    }


    private Boolean doServiceBucketPost(String url, Event event) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        String type = "application/json";
        String encodedData = URLEncoder.encode( event.getMessage());
        connection.setRequestProperty( "Content-Type", type );
        connection.setRequestProperty( "Content-Length", String.valueOf(encodedData.length()));
        OutputStream os = connection.getOutputStream();
        os.write(encodedData.getBytes());

        int responseCode = connection.getResponseCode();
        this.queue.push(new UpdateActorEvent(connection.getResponseMessage()));
        return responseCode == 200;
    }
}

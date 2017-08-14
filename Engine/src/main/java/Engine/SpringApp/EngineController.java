package Engine.SpringApp;

import Engine.Mapping.IMappedService;
import Engine.Mapping.IMappedSink;
import Engine.Mapping.IMappedSource;
import Engine.Mapping.Mapper;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import Engine.SimpleEngineStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class EngineController {
    private static Logger logger = Logger.getLogger(EngineController.class);
    private Registration registration;
    private SimpleEngineStrategy strategy;
    private ServiceRegistrationHandler serviceRegistrationHandler;

    @Autowired
    public EngineController(Registration registration, SimpleEngineStrategy strategy, ServiceRegistrationHandler serviceRegistrationHandler) {
        this.registration = registration;
        this.strategy = strategy;
        this.serviceRegistrationHandler = serviceRegistrationHandler;
    }

    @RequestMapping("/status")
    @ResponseBody
    public Map<String, Object> getStatus() {
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
            serviceObject.put("id", s.getClass().toGenericString());
            serviceObject.put("type", "something");
            serviceObject.put("x", coordinates.get("x"));
            serviceObject.put("y", coordinates.get("y"));
            serviceObject.put("z", coordinates.get("z"));
            serviceArray.put(serviceObject);
        });

        sinks.forEach(s -> {
            HashMap<String, Integer> coordinates = s.getCoOrdinates();
            JSONObject serviceObject = new JSONObject();
            serviceObject.put("id", s.getClass().toGenericString());
            serviceObject.put("type", "something");
            serviceObject.put("x", coordinates.get("x"));
            serviceObject.put("y", coordinates.get("y"));
            serviceObject.put("z", coordinates.get("z"));
            serviceArray.put(serviceObject);
        });

        services.forEach(s -> {
            HashMap<String, Integer> coordinates = s.getCoOrdinates();
            JSONObject serviceObject = new JSONObject();
            serviceObject.put("id", s.getClass().toGenericString());
            serviceObject.put("type", "something");
            serviceObject.put("x", coordinates.get("x"));
            serviceObject.put("y", coordinates.get("y"));
            serviceObject.put("z", coordinates.get("z"));
            serviceArray.put(serviceObject);
        });

        JSONObject json = new JSONObject();
        json.put("map", mapDetails);
        json.put("services", serviceArray);

        return json.toMap();
    }

    @RequestMapping(value="/services", method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity register(@RequestBody ServiceRegistrationRequestObject reqObject) {
        UUID uuid = this.serviceRegistrationHandler.handleRegistrationRequest(reqObject);
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uuid.toString());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
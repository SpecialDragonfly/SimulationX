package Engine.Mapping;

import java.util.*;

public class MappedService implements IMappedService {
    private final UUID uniqueId;
    private HashMap<HashMap<String, String>, Integer> resourceMap;
    private HashMap<String,Integer> coOrdinates;
    private IService service;

    public MappedService(Integer x, Integer y, Integer z, IService service) {
        this.coOrdinates = new HashMap<>();
        this.coOrdinates.put("x", x);
        this.coOrdinates.put("y", y);
        this.coOrdinates.put("z", z);
        this.service = service;
        this.resourceMap = service.getResourceMap();
        this.uniqueId = UUID.randomUUID();
    }

    public HashMap<String, Integer> getCoOrdinates() {
        return this.coOrdinates;
    }

    @Override
    public Set<String> getInputs() {
        Set<String> inputs = new HashSet<>();
        Set<HashMap<String, String>> resources = this.resourceMap.keySet();
        resources.forEach(resource -> resource.keySet().forEach(inputs::add));

        return inputs;
    }

    @Override
    public UUID getUUID() {
        return this.uniqueId;
    }

    @Override
    public Set<String> getOutputs() {
        Set<String> outputs = new HashSet<>();
        Set<HashMap<String, String>> resources = this.resourceMap.keySet();
        resources.forEach(resource -> resource.forEach((x,y) -> outputs.add(y)));

        return outputs;
    }
}

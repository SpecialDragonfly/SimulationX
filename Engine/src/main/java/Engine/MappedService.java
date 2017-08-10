package Engine;

import java.util.*;

public class MappedService implements IMappedService {
    private HashMap<String,Integer> coOrdinates;
    private IService service;
    private final HashSet<String> inputs;
    private final HashSet<String> outputs;

    public MappedService(Integer x, Integer y, Integer z, IService service) {
        this.coOrdinates = new HashMap<>();
        this.coOrdinates.put("x", x);
        this.coOrdinates.put("y", y);
        this.coOrdinates.put("z", z);
        this.service = service;
        this.outputs = new HashSet<>(service.getResourceMap().values());
        this.inputs = new HashSet<>(service.getResourceMap().keySet());
    }

    public HashMap<String, Integer> getCoOrdinates() {
        return this.coOrdinates;
    }

    public HashMap<String, String> getExchangeMap() {
        return this.service.getResourceMap();
    }

    @Override
    public Set<String> getInputs() {
        return this.inputs;
    }

    public Set<String> getInputs(String output) {
        Set<String> inputs = new HashSet<>();
        for(Map.Entry<String, String> entry : this.getExchangeMap().entrySet()) {
            if(output.equals(entry.getValue())) {
                inputs.add(entry.getKey());
            }
        }

        return inputs;
    }

    @Override
    public Set<String> getOutputs() {
        return this.outputs;
    }

    public Set<String> getOutputs(String input) {
        Set<String> outputs = new HashSet<>();
        for(Map.Entry<String, String> entry : this.getExchangeMap().entrySet()) {
            if(input.equals(entry.getKey())) {
                outputs.add(entry.getValue());
            }
        }

        return outputs;
    }
}

package Engine.Mapping;

import Engine.ExchangeItem;

import java.util.*;

public class MappedService implements IMappedService {
    private final UUID uniqueId;
    private ArrayList<ExchangeItem> resourceMap;
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

    public ArrayList<ExchangeItem> getResourceMap() {
        return resourceMap;
    }

    public HashMap<String, Integer> getCoOrdinates() {
        return this.coOrdinates;
    }

    public String getActionUrl() {
        return this.service.getActionUrl();
    }

    @Override
    public Set<String> getInputs() {
        Set<String> inputs = new HashSet<>();
        for (ExchangeItem exchange: this.resourceMap) {
            inputs.add(exchange.getInput());
        }

        return inputs;
    }

    @Override
    public Integer getamount() {
        return null;
    }

    @Override
    public String getResourceType() {
        return null;
    }

    @Override
    public UUID getUUID() {
        return this.uniqueId;
    }

    @Override
    public Set<String> getOutputs() {
        Set<String> outputs = new HashSet<>();
        for (ExchangeItem exchange: this.resourceMap) {
            outputs.add(exchange.getOutput());
        }

        return outputs;
    }
}

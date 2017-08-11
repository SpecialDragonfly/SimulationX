package Engine.DTO;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Service implements IService, ISource, ISink {

    private final String actionUrl;
    private final String statusUrl;
    private final String healthUrl;
    private final HashSet<String> outputs;
    private HashMap<String, String> exchangeMap;

    public Service(String actionUrl, String statusUrl, String healthUrl, HashMap<String,String> exchangeMap) {

        this.actionUrl = actionUrl;
        this.statusUrl = statusUrl;
        this.healthUrl = healthUrl;
        this.exchangeMap = exchangeMap;
        this.outputs = new HashSet<String>();
        for(String value : this.exchangeMap.values()) {
            if(this.outputs.contains(value)) {
                continue;
            }
            outputs.add(value);
        }
    }

    @Override
    public HashMap<String, String> getExchangeMap() {
        return this.exchangeMap;
    }

    @Override
    public Set<String> getInputs() {
        return this.exchangeMap.keySet();
    }

    @Override
    public Set<String> getOutputs() {
        return this.outputs;
    }
}

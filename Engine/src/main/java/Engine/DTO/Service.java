package Engine.DTO;

import java.util.HashMap;

public class Service extends DTO implements IService {

    private final String actionUrl;
    private final String statusUrl;
    private final String healthUrl;
    private HashMap<String, String> exchangeMap;

    public Service(String id, String actionUrl, String statusUrl, String healthUrl, HashMap<String,String> exchangeMap) {

        super(id);
        this.actionUrl = actionUrl;
        this.statusUrl = statusUrl;
        this.healthUrl = healthUrl;
        this.exchangeMap = exchangeMap;
    }

    @Override
    public HashMap<String, String> getExchangeMap() {
        return this.exchangeMap;
    }
}

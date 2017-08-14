package Engine;

import Engine.Mapping.IService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Jeremy Frenay <jeremy.frenay@worldfirst.com>
 */
public class ServiceDTO implements IService {
    private final String actionUrl;
    private final String statusUrl;
    private final String healthcheckUrl;
    private ArrayList<ExchangeItem> resourceMap;

    public ServiceDTO(String actionUrl, String statusUrl, String healthcheckUrl, ArrayList<ExchangeItem> resourceMap) {

        this.actionUrl = actionUrl; // Interact with service
        this.statusUrl = statusUrl; // What is the status of the service
        this.healthcheckUrl = healthcheckUrl; // Is the service still there
        this.resourceMap = resourceMap; // What resources are exchanged by the service
    }

    public String getActionUrl() {
        return this.actionUrl;
    }

    public String getStatusUrl() {
        return this.statusUrl;
    }

    public String getHealthcheckUrl() {
        return this.healthcheckUrl;
    }

    public ArrayList<ExchangeItem> getResourceMap() {
        return this.resourceMap;
    }
}

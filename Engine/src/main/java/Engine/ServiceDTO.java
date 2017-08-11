package Engine;

import Engine.Mapping.IService;

import java.util.HashMap;

/**
 * @author Jeremy Frenay <jeremy.frenay@worldfirst.com>
 */
public class ServiceDTO implements IService {
    private final String actionUrl;
    private final String statusUrl;
    private final String healthcheckUrl;
    private HashMap<HashMap<String,String>, Integer> resourceMap;

    public ServiceDTO(String actionUrl, String statusUrl, String healthcheckUrl, HashMap<HashMap<String,String>, Integer> resourceMap) {

        this.actionUrl = actionUrl; // Interact with service
        this.statusUrl = statusUrl; // What is the status of the service
        this.healthcheckUrl = healthcheckUrl; // Is the service still there
        this.resourceMap = resourceMap; // What resources are exchanged by the service
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public String getStatusUrl() {
        return statusUrl;
    }

    public String getHealthcheckUrl() {
        return healthcheckUrl;
    }

    public HashMap<HashMap<String,String>, Integer> getResourceMap() {
        return resourceMap;
    }
}

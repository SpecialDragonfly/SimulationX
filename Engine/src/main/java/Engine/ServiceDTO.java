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
    private HashMap<String, String> resourceMap;

    public ServiceDTO(String actionUrl, String statusUrl, String healthcheckUrl, HashMap<String,String> resourceMap) {

        this.actionUrl = actionUrl;
        this.statusUrl = statusUrl;
        this.healthcheckUrl = healthcheckUrl;
        this.resourceMap = resourceMap;
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

    public HashMap<String, String> getResourceMap() {
        return resourceMap;
    }
}

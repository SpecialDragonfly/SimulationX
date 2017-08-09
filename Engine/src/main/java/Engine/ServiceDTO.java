package Engine;

import java.util.HashMap;

/**
 * @author Jeremy Frenay <jeremy.frenay@worldfirst.com>
 */
public class ServiceDTO {

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
}

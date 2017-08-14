package Engine.SpringApp;

import java.util.List;

/**
 * @author Jeremy Frenay <jeremy.frenay@worldfirst.com>
 */
public class ServiceRegistrationRequestObject {

    private String action_url;
    private String status_url;
    private String healthcheck_url;
    private List<ResourceDTO> resources;

    public String getAction_url() {
        return action_url;
    }

    public void setAction_url(String action_url) {
        this.action_url = action_url;
    }

    public String getStatus_url() {
        return status_url;
    }

    public void setStatus_url(String status_url) {
        this.status_url = status_url;
    }

    public String getHealthcheck_url() {
        return healthcheck_url;
    }

    public void setHealthcheck_url(String healthcheck_url) {
        this.healthcheck_url = healthcheck_url;
    }

    public List<ResourceDTO> getResources() {
        return resources;
    }

    public void setResources(List<ResourceDTO> resources) {
        this.resources = resources;
    }
}

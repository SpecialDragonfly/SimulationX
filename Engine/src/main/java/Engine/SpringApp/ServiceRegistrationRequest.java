package Engine.SpringApp;

import org.json.JSONObject;

import java.util.UUID;

/**
 * @author Jeremy Frenay <jeremy.frenay@worldfirst.com>
 */
public class ServiceRegistrationRequest implements RegistrationRequest {

    private static final long serialVersionUID = 1L;

    /** the order number used for tracking */
    private UUID uuid;
    private ServiceRegistrationRequestObject requestObject;
    private String type;

    // Default constructor required by Jackson Java JSON-processor
    public ServiceRegistrationRequest() {}

    public ServiceRegistrationRequest(UUID uuid, ServiceRegistrationRequestObject requestObject) {
        this.uuid = uuid;
        this.requestObject = requestObject;
        this.type = "Service";
    }

    public boolean isSource()
    {
        return false;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getType() {
        return type;
    }

    public String getBody(){
        return this.requestObject.toString();
    }
}

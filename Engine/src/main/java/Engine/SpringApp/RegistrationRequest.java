package Engine.SpringApp;

import java.util.Map;
import java.util.UUID;

/**
 * @author Jeremy Frenay <jeremy.frenay@worldfirst.com>
 */
public interface RegistrationRequest {
    public UUID getUuid();

    boolean isSource();

    public String getBody();
}

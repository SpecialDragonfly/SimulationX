package Engine.SpringApp;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Jeremy Frenay <jeremy.frenay@worldfirst.com>
 */
public class Service implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID uuid;

    public Service() {}

    public Service(UUID uuid) {

        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return " Service number " + this.getUuid().toString();
    }
}

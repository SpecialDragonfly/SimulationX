package Engine.SpringApp;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Jeremy Frenay <jeremy.frenay@worldfirst.com>
 */
public class Source implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID uuid;

    public Source() {}

    public Source(UUID uuid) {

        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return " Source number " + this.getUuid().toString();
    }
}

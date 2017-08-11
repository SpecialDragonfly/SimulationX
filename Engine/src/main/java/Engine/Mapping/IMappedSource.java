package Engine.Mapping;

import java.util.Set;
import java.util.UUID;

public interface IMappedSource extends IMapped {
    Set<String> getOutputs();
    UUID getUUID();
}

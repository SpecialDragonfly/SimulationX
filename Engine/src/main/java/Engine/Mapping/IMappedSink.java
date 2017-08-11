package Engine.Mapping;

import java.util.Set;
import java.util.UUID;

public interface IMappedSink extends IMapped{
    Set<String> getInputs();
    UUID getUUID();
}

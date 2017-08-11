package Engine.Mapping;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public interface IMappedService extends IMappedSink, IMappedSource{
    Set<String> getOutputs();
    Set<String> getInputs();
    UUID getUUID();
    HashMap<HashMap<String, String>, Integer> getResourceMap();
}

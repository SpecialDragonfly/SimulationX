package Engine.Mapping;

import Engine.ExchangeItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public interface IMappedService extends IMappedSink, IMappedSource{
    Set<String> getOutputs();
    Set<String> getInputs();
    UUID getUUID();
    ArrayList<ExchangeItem> getResourceMap();
    String getActionUrl();
}

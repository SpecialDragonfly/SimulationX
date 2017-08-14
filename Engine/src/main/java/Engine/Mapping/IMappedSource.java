package Engine.Mapping;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public interface IMappedSource extends IMapped {
    Integer getamount();
    String getResourceType();
    UUID getUUID();
    Integer removeAmount(Integer amount);
}

package Engine.Mapping;

import java.util.HashMap;

public interface ISource {
    Integer getamount();
    String getResourceType();
    Integer removeAmount(Integer amount);
}

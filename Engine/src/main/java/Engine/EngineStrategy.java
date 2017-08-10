package Engine;

import Engine.Mapping.IService;
import java.util.ArrayList;

public interface EngineStrategy extends QueueListener {
    void verifyObjects();
    void update(IService service);
    ArrayList<ServiceDTO> getServiceDTOArray();
}

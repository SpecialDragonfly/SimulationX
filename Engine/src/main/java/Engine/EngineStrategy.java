package Engine;

import java.util.ArrayList;

public interface EngineStrategy {
    void verifyObjects();
    void update(IService service);
    ArrayList<ServiceDTO> getServiceDTOArray();
}

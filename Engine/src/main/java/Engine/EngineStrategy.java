package Engine;

import java.util.ArrayList;

public interface EngineStrategy {
    void verifyObjects();
    void update();
    ArrayList<ServiceDTO> getServiceDTOArray();
}

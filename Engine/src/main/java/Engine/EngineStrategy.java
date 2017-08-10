package Engine;

import java.util.ArrayList;

public interface EngineStrategy extends QueueListener {
    void verifyObjects();
    void update();
    ArrayList<ServiceDTO> getServiceDTOArray();
}

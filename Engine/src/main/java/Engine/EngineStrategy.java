package Engine;

import Engine.Mapping.IMapper;
import Engine.Mapping.IService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface EngineStrategy extends QueueListener {
    void verifyObjects();
    void update(IService service);
    IMapper getMapper();
}

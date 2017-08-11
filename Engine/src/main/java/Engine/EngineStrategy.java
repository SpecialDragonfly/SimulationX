package Engine;

import Engine.Mapping.IMappedService;
import Engine.Mapping.IMapper;
import Engine.Mapping.IService;

public interface EngineStrategy extends QueueListener {
    void verifyObjects();
    void update(IService service);
    IMapper getMapper();
    IMappedService getServiceByUUID(String pathInfo) throws Exception;
}

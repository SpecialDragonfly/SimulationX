package Engine.Mapping;

import Engine.Events.Event;
import Engine.ServiceDTO;

public interface IMapper {

    void addService(IService service, int instances);
    void addSource(ISource source, int instances);

    void removeService(ServiceDTO x);

    IMappedService getServiceByUUID(String uuid);

    Event moveActor(String uuid, Integer x, Integer y, Integer z);

    MappedActor getActor(String uuid);
}

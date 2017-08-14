package Engine.Mapping;

import Engine.ServiceDTO;
import Engine.Events.Event;

public interface IMapper {

    void addService(IService service, int instances);
    void addSource(ISource source, int instances);

    void removeService(ServiceDTO x);

    IMappedService getServiceByUUID(String uuid) throws Exception;

    MappedActor addActor(String name, Integer bucketCapacity);

    public Event moveActor(String uuid, Integer x, Integer y, Integer z);
}

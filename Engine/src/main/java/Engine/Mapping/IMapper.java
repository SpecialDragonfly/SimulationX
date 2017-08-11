package Engine.Mapping;

import Engine.ServiceDTO;

public interface IMapper {

    void addService(IService service, int instances);

    void removeService(ServiceDTO x);

    IMappedService getServiceByUUID(String uuid) throws Exception;
}

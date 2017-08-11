package Engine.Mapping;

import Engine.DTO.IService;
import Engine.ServiceDTO;

public interface IMapper {

    void addService(IService service, int instances);

    void removeService(ServiceDTO x);
}

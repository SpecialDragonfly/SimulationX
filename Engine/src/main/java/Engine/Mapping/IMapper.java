package Engine.Mapping;

import Engine.DTO.IService;
import Engine.ServiceDTO;

import java.util.ArrayList;

public interface IMapper {

    int getWidth();

    int getHeight();

    int getDepth();

    void addService(IService service, int instances);

    void removeService(IService x);

    ArrayList<IMappedService> getServices();

    ArrayList<IMappedSource> getSources();

    ArrayList<IMappedSink> getSinks();
}

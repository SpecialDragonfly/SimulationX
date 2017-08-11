package Engine.DTO;

import java.util.List;

public interface IActor {

    List<IResource> getBucket();
    void setBucket(List<IResource> bucket);
}

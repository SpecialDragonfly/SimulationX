package Engine.DTO;

import java.util.HashMap;

public interface IService extends ISink, ISource{

    public HashMap<String, String> getExchangeMap();
}

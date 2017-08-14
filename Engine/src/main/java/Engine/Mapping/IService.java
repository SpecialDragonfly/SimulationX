package Engine.Mapping;

import Engine.ExchangeItem;

import java.util.ArrayList;
import java.util.HashMap;

public interface IService {
    ArrayList<ExchangeItem> getResourceMap();
    String getActionUrl();
}

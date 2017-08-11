package Engine.Mapping;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class MapData {

    JSONObject getMapData(IMapper mapper) {
        JSONObject mapData = new JSONObject();

        JSONObject map = new JSONObject();
        map.append("width", mapper.getWidth());
        map.append("height", mapper.getHeight());
        map.append("depth", mapper.getDepth());

        JSONArray services = new JSONArray();
        for (IMappedService mappedService: mapper.getServices()) {
            JSONObject service = new JSONObject();
            service.append("id", mappedService.getId());
            service.append("x", mappedService.getCoOrdinates().get("x"));
            service.append("y", mappedService.getCoOrdinates().get("y"));
            service.append("z", mappedService.getCoOrdinates().get("z"));

            JSONArray exchanges = new JSONArray();

            for (HashMap.Entry<String, String> entry : mappedService.getExchangeMap().entrySet()) {
                JSONObject exchange = new JSONObject();
                exchange.append("input", entry.getKey());
                exchange.append("output", entry.getValue());
                exchange.append("rate", entry.getValue());
                exchanges.put(exchange);
            }

            services.put(service);
        }

//        JSONArray sources = new JSONArray();
//        for (IMappedService mappedService: mapper.getServices()) {
//            JSONObject service = new JSONObject();
//            service.append("id", mappedService.getId());
//            service.append("x", mappedService.getCoOrdinates().get("x"));
//            service.append("y", mappedService.getCoOrdinates().get("y"));
//            service.append("z", mappedService.getCoOrdinates().get("z"));
//
//            JSONArray exchanges = new JSONArray();
//
//            for (HashMap.Entry<String, String> entry : mappedService.getExchangeMap().entrySet()) {
//                JSONObject exchange = new JSONObject();
//                exchange.append("input", entry.getKey());
//                exchange.append("output", entry.getValue());
//                exchange.append("rate", entry.getValue());
//                exchanges.put(exchange);
//            }
//
//            services.put(service);
//        }

        map.append("services", services);
        mapData.append("map", map);

        return mapData;
    }
}

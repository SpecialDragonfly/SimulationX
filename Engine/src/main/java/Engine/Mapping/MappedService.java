package Engine.Mapping;

import Engine.DTO.IService;

import java.util.*;

public class MappedService implements IMappedService {
    private HashMap<String,Integer> coOrdinates;
    private IService service;

    public MappedService(Integer x, Integer y, Integer z, IService service) {
        this.coOrdinates = new HashMap<>();
        this.coOrdinates.put("x", x);
        this.coOrdinates.put("y", y);
        this.coOrdinates.put("z", z);
        this.service = service;
    }

    public HashMap<String, Integer> getCoOrdinates() {
        return this.coOrdinates;
    }

    public HashMap<String, String> getExchangeMap() {
        return this.service.getExchangeMap();
    }

    public String getId() {
        return service.getId();
    }
}

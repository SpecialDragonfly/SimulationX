package Engine.Mapping;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class MappedSource implements IMappedSource {

    private final UUID uniqueId;
    private HashMap<String,Integer> coOrdinates;
    private ISource source;

    public MappedSource(Integer x, Integer y, Integer z, ISource source) {
        this.coOrdinates = new HashMap<>();
        this.coOrdinates.put("x", x);
        this.coOrdinates.put("y", y);
        this.coOrdinates.put("z", z);
        this.source = source;
        this.uniqueId = UUID.randomUUID();
    }

    @Override
    public Integer getamount() {
        return this.source.getamount();
    }

    public String getResourceType() {
        return this.source.getResourceType();
    }

    @Override
    public UUID getUUID() {
        return this.uniqueId;
    }

    @Override
    public HashMap<String, Integer> getCoOrdinates() {
        return this.coOrdinates;
    }

    @Override
    public Integer removeAmount(Integer amount) {
        return this.source.removeAmount(amount);
    }
}

package Engine.Mapping;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class MappedSink implements IMappedSink {
    private final UUID uniqueId;
    private HashMap<String,Integer> coOrdinates;
    private ISink sink;

    public MappedSink(Integer x, Integer y, Integer z, ISink sink) {
        this.coOrdinates = new HashMap<>();
        this.coOrdinates.put("x", x);
        this.coOrdinates.put("y", y);
        this.coOrdinates.put("z", z);
        this.sink = sink;
        this.uniqueId = UUID.randomUUID();
    }

    @Override
    public Set<String> getInputs() {
        return this.sink.getInputs();
    }

    @Override
    public UUID getUUID() {
        return this.uniqueId;
    }

    @Override
    public HashMap<String, Integer> getCoOrdinates() {
        return this.coOrdinates;
    }
}

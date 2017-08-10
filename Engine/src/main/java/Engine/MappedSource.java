package Engine;

import java.util.HashMap;
import java.util.Set;

public class MappedSource implements IMappedSource {

    private HashMap<String,Integer> coOrdinates;
    private ISource source;

    public MappedSource(Integer x, Integer y, Integer z, ISource source) {
        this.coOrdinates = new HashMap<>();
        this.coOrdinates.put("x", x);
        this.coOrdinates.put("y", y);
        this.coOrdinates.put("z", z);
        this.source = source;
    }

    @Override
    public Set<String> getOutputs() {
        return this.source.getOutputs();
    }

    @Override
    public HashMap<String, Integer> getCoOrdinates() {
        return this.coOrdinates;
    }
}

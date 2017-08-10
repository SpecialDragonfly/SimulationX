package Engine;

import java.util.ArrayList;
import java.util.HashMap;

public class MappedSink implements IMappedSink {
    private HashMap<String,Integer> coOrdinates;
    private ISink sink;

    public MappedSink(Integer x, Integer y, Integer z, ISink sink) {
        this.coOrdinates = new HashMap<>();
        this.coOrdinates.put("x", x);
        this.coOrdinates.put("y", y);
        this.coOrdinates.put("z", z);
        this.sink = sink;
    }

    @Override
    public ArrayList<String> getInputs() {
        return this.sink.getInputs();
    }

    @Override
    public HashMap<String, Integer> getCoOrdinates() {
        return this.coOrdinates;
    }
}

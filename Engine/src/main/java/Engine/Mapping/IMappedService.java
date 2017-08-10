package Engine.Mapping;

import java.util.Set;

public interface IMappedService extends IMappedSink, IMappedSource{
    public Set<String> getOutputs(String input);
    public Set<String> getInputs(String output);
}

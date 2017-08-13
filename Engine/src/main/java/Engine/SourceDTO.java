package Engine;

import Engine.Mapping.ISource;

public class SourceDTO implements ISource {

    private final String resourceType;
    private final Integer amount;

    public SourceDTO(String resourceType, Integer amount) {

        this.resourceType = resourceType;
        this.amount = amount;
    }

    @Override
    public Integer getamount() {
        return this.amount;
    }

    @Override
    public String getResourceType() {
        return this.resourceType;
    }
}

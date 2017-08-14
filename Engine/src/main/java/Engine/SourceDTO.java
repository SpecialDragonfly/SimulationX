package Engine;

import Engine.Mapping.ISource;

public class SourceDTO implements ISource {

    private final String resourceType;
    private Integer amount;

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

    @Override
    public Integer removeAmount(Integer amount) {
        this.amount -= amount;
        this.amount = (this.amount < 0)? 0 : this.amount;
        return this.amount;
    }
}

package Engine.DTO;

public class DTO implements IDTO {

    private final String id;

    public DTO(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }
}

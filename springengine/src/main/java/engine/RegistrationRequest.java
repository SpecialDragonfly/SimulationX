package engine;

/**
 * @author Jeremy Frenay <jeremy.frenay@worldfirst.com>
 */
public class RegistrationRequest {

    private static final long serialVersionUID = 1L;

    /** the order number used for tracking */
    private int number;
    private String type;

    // Default constructor required by Jackson Java JSON-processor
    public RegistrationRequest() {}

    public RegistrationRequest(int number, String type) {
        this.number = number;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSource()
    {
        return (this.type == "Source");
    }
}

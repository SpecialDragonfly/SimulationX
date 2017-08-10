package Engine.Events;

public class RegisterEvent implements Event
{
    private String message;

    public RegisterEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
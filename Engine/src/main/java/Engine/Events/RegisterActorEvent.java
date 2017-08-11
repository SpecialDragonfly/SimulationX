package Engine.Events;

public class RegisterActorEvent implements Event {
    private String message;

    public RegisterActorEvent(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

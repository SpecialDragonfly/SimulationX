package Engine.Events;

public class MovedEvent implements Event {
    private String message;

    public MovedEvent(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

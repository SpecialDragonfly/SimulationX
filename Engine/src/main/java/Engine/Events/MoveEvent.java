package Engine.Events;

public class MoveEvent implements Event {
    private String message;

    public MoveEvent(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        /*
            { id: actor_uuid, x: int, y: int, z: int }
         */
        return this.message;
    }
}

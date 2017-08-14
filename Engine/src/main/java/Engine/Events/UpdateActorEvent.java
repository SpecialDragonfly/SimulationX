package Engine.Events;

public class UpdateActorEvent implements Event {
    private String message;

    public UpdateActorEvent(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        /*
            {   id: actor_uuid,
                bucket: [
                {
                    type: resource type,
                    amount: amount of resource
                },
            ]}
         */
        return this.message;
    }
}

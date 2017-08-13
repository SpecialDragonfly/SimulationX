package Engine.Events;

public class CollisionEvent implements Event {

    private final String message;

    public CollisionEvent(String message) {
         /*
            { actor: {
                    id: actor_uuid,
                    bucket: [
                        {
                            type: resource_type
                            amount: amount_of_resource
                        }
                    ]
                },
               hit: {
               type: source/service
               id: source/service uuid
             }
         */
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

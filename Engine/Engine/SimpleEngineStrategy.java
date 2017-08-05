package Engine;

/**
 * Creates a singular instance of any Sink/Source/Service that it knows about
 */
public class SimpleEngineStrategy {
    public void verifyObjects() {
        // Verify that Sinks/Sources/Services still exist.
    }

    public void update() {
        // If any new Sinks/Source/Service have registered themselves then make a singular instance of them.
    }
}

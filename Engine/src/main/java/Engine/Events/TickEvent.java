package Engine.Events;

public class TickEvent implements Event {
    private int tick = 0;

    public TickEvent() {}
    public TickEvent(int tick) { this.tick = tick; }

    public String getMessage() {
        return "{\"tickCount\": " + this.tick + "}";
    }
}
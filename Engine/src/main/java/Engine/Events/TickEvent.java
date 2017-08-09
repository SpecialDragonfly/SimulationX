package Engine.Events;

public class TickEvent implements Event {
    private int tick;

    public TickEvent() {}
    public TickEvent(int tick) { this.tick = tick; }
}
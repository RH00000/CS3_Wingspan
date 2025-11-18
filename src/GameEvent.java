public class GameEvent {
    private final EventType type;
    private final Player source;
    private final Object data;

    public GameEvent(EventType type, Player source, Object data) {
        this.type = type;
        this.source = source;
        this.data = data;
    }

    public EventType getType() {
        return type;
    }

    public Player getSource() {
        return source;
    }

    public Object getData() {
        return data;
    }
}

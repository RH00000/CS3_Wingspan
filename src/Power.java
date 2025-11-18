
public abstract class Power {
    protected final PowerTiming timing;
    protected final String description;
    protected boolean usedThisTurn;

    public Power(PowerTiming timing, String description) {
        this.timing = timing;
        this.description = description;
        this.usedThisTurn = false;
    }

    public PowerTiming getTiming() {
        return timing;
    }

    public String getDescription() {
        return description;
    }

    public boolean isUsedThisTurn() {
        return usedThisTurn;
    }

    public void refreshEachTurn() {
        usedThisTurn = false;
    }

    public abstract void execute(GameContext ctx, Bird bird, Player owner);

    public void onEvent(GameContext ctx, GameEvent e) {
        // default no-op
    }
}

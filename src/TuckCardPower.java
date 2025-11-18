public class TuckCardPower extends Power {
    public TuckCardPower(PowerTiming timing, String description) {
        super(timing, description);
    }

    @Override
    public void execute(GameContext ctx, Bird bird, Player owner) {
        if (usedThisTurn) {
            return;
        }
        boolean tucked = owner.tuckCard(bird);
        if (tucked && ctx != null) {
            ctx.broadcast(new GameEvent(EventType.PLAYER_ACTIVATE, owner, bird));
        }
        usedThisTurn = true;
    }
}

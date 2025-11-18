public class CacheFoodPower extends Power {
    private final int amount;
    private final FoodType feederFoodType;

    public CacheFoodPower(PowerTiming timing, int amount, String description) {
        this(timing, amount, description, null);
    }

    public CacheFoodPower(PowerTiming timing, int amount, String description, FoodType feederFoodType) {
        super(timing, description);
        this.amount = Math.max(1, amount);
        this.feederFoodType = feederFoodType;
    }

    @Override
    public void execute(GameContext ctx, Bird bird, Player owner) {
        if (usedThisTurn) {
            return;
        }
        if (feederFoodType != null) {
            if (ctx == null || ctx.getFeeder() == null) {
                return;
            }
            boolean taken = ctx.getFeeder().take(feederFoodType);
            if (!taken) {
                return;
            }
        }
        bird.addCachedFood(amount);
        usedThisTurn = true;
        if (ctx != null) {
            ctx.broadcast(new GameEvent(EventType.PLAYER_ACTIVATE, owner, bird));
        }
    }
}

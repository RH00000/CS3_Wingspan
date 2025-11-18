public class GainFoodPower extends Power {
    private final FoodType foodType;
    private final int amount;

    public GainFoodPower(PowerTiming timing, FoodType foodType, int amount, String description) {
        super(timing, description);
        this.foodType = foodType;
        this.amount = amount;
    }

    @Override
    public void execute(GameContext ctx, Bird bird, Player owner) {
        if (usedThisTurn) {
            return;
        }
        owner.addFood(foodType, amount);
        usedThisTurn = true;
        if (ctx != null) {
            ctx.broadcast(new GameEvent(EventType.PLAYER_GAIN_FOOD, owner, foodType));
        }
    }
}

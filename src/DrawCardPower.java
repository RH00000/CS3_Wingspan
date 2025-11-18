public class DrawCardPower extends Power {
    private final int cards;

    public DrawCardPower(PowerTiming timing, int cards, String description) {
        super(timing, description);
        this.cards = Math.max(1, cards);
    }

    @Override
    public void execute(GameContext ctx, Bird bird, Player owner) {
        if (usedThisTurn || ctx == null) {
            return;
        }
        for (int i = 0; i < cards; i++) {
            Bird drawn = ctx.getBirdDeck().draw();
            if (drawn == null) {
                break;
            }
            owner.addBirdToHand(drawn);
            ctx.broadcast(new GameEvent(EventType.PLAYER_DRAW_CARD, owner, drawn));
        }
        usedThisTurn = true;
    }
}

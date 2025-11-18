public class NestTypeBonusCard extends BonusCard {
    private final NestType targetNestType;

    public NestTypeBonusCard(NestType targetNestType) {
        super("Specialist Builder", "Gain 2 points per bird with the chosen nest type.");
        this.targetNestType = targetNestType;
    }

    @Override
    public int calculatePoints(Player player) {
        int count = player.getBoard().countBirdsWithNest(targetNestType);
        return count * 2;
    }
}

import java.util.Map;

public class FoodDiversityBonusCard extends BonusCard {
    public FoodDiversityBonusCard() {
        super("Flexible Forager", "Score points for holding diverse food types.");
    }

    @Override
    public int calculatePoints(Player player) {
        Map<FoodType, Integer> food = player.getFood();
        long unique = food.values().stream().filter(value -> value != null && value > 0).count();
        if (unique >= 5) {
            return 6;
        }
        if (unique >= 3) {
            return 3;
        }
        return 0;
    }
}

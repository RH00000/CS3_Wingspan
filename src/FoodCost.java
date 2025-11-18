import java.util.EnumMap;
import java.util.Map;

public class FoodCost {
    private final EnumMap<FoodType, Integer> required;
    private final int wildsAllowed;

    public FoodCost(EnumMap<FoodType, Integer> required, int wildsAllowed) {
        this.required = new EnumMap<>(FoodType.class);
        if (required != null) {
            for (Map.Entry<FoodType, Integer> entry : required.entrySet()) {
                this.required.put(entry.getKey(), Math.max(0, entry.getValue()));
            }
        }
        this.wildsAllowed = Math.max(0, wildsAllowed);
    }

    public EnumMap<FoodType, Integer> getRequired() {
        return new EnumMap<>(required);
    }

    public int getWildsAllowed() {
        return wildsAllowed;
    }
}

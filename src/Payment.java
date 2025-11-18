import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class Payment {
    private final EnumMap<FoodType, Integer> paidFood;
    private final int eggsPaid;

    public Payment(EnumMap<FoodType, Integer> paidFood, int eggsPaid) {
        this.paidFood = new EnumMap<>(FoodType.class);
        if (paidFood != null) {
            this.paidFood.putAll(paidFood);
        }
        this.eggsPaid = Math.max(0, eggsPaid);
    }

    public Map<FoodType, Integer> getPaidFood() {
        return Collections.unmodifiableMap(paidFood);
    }

    public int getEggsPaid() {
        return eggsPaid;
    }
}

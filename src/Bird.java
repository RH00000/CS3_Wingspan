import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

public class Bird {
    private final String name;
    private final int victoryPoints;
    private final EnumMap<FoodType, Integer> foodCost;
    private final int wildFoodSlots;
    private final EnumSet<HabitatType> habitats;
    private HabitatType currentHabitat;
    private final NestType nestType;
    private final int eggLimit;
    private final int wingspan;
    private final Power power;

    private int eggs;
    private int tuckedCards;
    private int cachedFood;

    private final String colorName;
    private final String bodyPartName;
    private final String geographyName;
    private final String personName;

    public Bird(String name,
                int victoryPoints,
                EnumMap<FoodType, Integer> foodCost,
                int wildFoodSlots,
                EnumSet<HabitatType> habitats,
                NestType nestType,
                int eggLimit,
                int wingspan,
                Power power,
                String colorName,
                String bodyPartName,
                String geographyName,
                String personName) {
        this.name = Objects.requireNonNull(name, "name");
        this.victoryPoints = victoryPoints;
        this.foodCost = new EnumMap<>(FoodType.class);
        if (foodCost != null) {
            this.foodCost.putAll(foodCost);
        }
        this.wildFoodSlots = Math.max(0, wildFoodSlots);
        if (habitats == null || habitats.isEmpty()) {
            this.habitats = EnumSet.noneOf(HabitatType.class);
        } else {
            this.habitats = EnumSet.copyOf(habitats);
        }
        this.nestType = Objects.requireNonNull(nestType, "nestType");
        this.eggLimit = Math.max(0, eggLimit);
        this.wingspan = wingspan;
        this.power = power;
        this.colorName = colorName;
        this.bodyPartName = bodyPartName;
        this.geographyName = geographyName;
        this.personName = personName;
    }

    public String getName() {
        return name;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public EnumMap<FoodType, Integer> getFoodCost() {
        return new EnumMap<>(foodCost);
    }

    public int getWildFoodSlots() {
        return wildFoodSlots;
    }

    public EnumSet<HabitatType> getHabitats() {
        return habitats.isEmpty() ? EnumSet.noneOf(HabitatType.class) : EnumSet.copyOf(habitats);
    }

    public HabitatType getCurrentHabitat() {
        return currentHabitat;
    }

    void setCurrentHabitat(HabitatType currentHabitat) {
        this.currentHabitat = currentHabitat;
    }

    public NestType getNestType() {
        return nestType;
    }

    public int getEggLimit() {
        return eggLimit;
    }

    public int getWingspan() {
        return wingspan;
    }

    public Power getPower() {
        return power;
    }

    public int getEggs() {
        return eggs;
    }

    public int getTuckedCards() {
        return tuckedCards;
    }

    public int getCachedFood() {
        return cachedFood;
    }

    public void addEggs(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot add negative eggs");
        }
        if (!canAddEggs(amount)) {
            throw new IllegalStateException("Egg limit exceeded for " + name);
        }
        eggs += amount;
    }

    public boolean canAddEggs(int amount) {
        return amount >= 0 && eggs + amount <= eggLimit;
    }

    public void removeEggs(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot remove negative eggs");
        }
        if (amount > eggs) {
            throw new IllegalStateException("Not enough eggs on " + name);
        }
        eggs -= amount;
    }

    public void addTuckedCards(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot tuck negative cards");
        }
        tuckedCards += amount;
    }

    public void addCachedFood(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot cache negative food");
        }
        cachedFood += amount;
    }

    public List<Integer> score(boolean includeBonus) {
        List<Integer> breakdown = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0));
        breakdown.set(0, victoryPoints);
        breakdown.set(3, eggs);
        breakdown.set(6, victoryPoints + eggs);
        return Collections.unmodifiableList(breakdown);
    }

    public String getColorName() {
        return colorName;
    }

    public String getBodyPartName() {
        return bodyPartName;
    }

    public String getGeographyName() {
        return geographyName;
    }

    public String getPersonName() {
        return personName;
    }
}

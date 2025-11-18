public abstract class BonusCard {
    private final String name;
    private final String description;

    protected BonusCard(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract int calculatePoints(Player player);
}

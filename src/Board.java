import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

public class Board {
    private final EnumMap<HabitatType, List<Bird>> habitats;
    private final int[] eggCostByColumn;

    public Board(int[] eggCostByColumn) {
        if (eggCostByColumn == null || eggCostByColumn.length == 0) {
            throw new IllegalArgumentException("eggCostByColumn must contain at least one entry");
        }
        this.eggCostByColumn = Arrays.copyOf(eggCostByColumn, eggCostByColumn.length);
        habitats = new EnumMap<>(HabitatType.class);
        for (HabitatType habitat : HabitatType.values()) {
            List<Bird> row = new ArrayList<>(eggCostByColumn.length);
            for (int i = 0; i < eggCostByColumn.length; i++) {
                row.add(null);
            }
            habitats.put(habitat, row);
        }
    }

    public boolean canPlaceBird(Bird bird, HabitatType habitat, int column, Player player) {
        Objects.requireNonNull(bird, "bird");
        Objects.requireNonNull(habitat, "habitat");
        Objects.requireNonNull(player, "player");
        if (!bird.getHabitats().contains(habitat)) {
            return false;
        }
        if (column < 0 || column >= eggCostByColumn.length) {
            return false;
        }
        if (habitats.get(habitat).get(column) != null) {
            return false;
        }
        int eggsRequired = eggCostByColumn[column];
        if (player.getBoard() != this) {
            return false;
        }
        if (player.getBoard().totalEggsOnBoard() < eggsRequired) {
            return false;
        }
        FoodCost cost = new FoodCost(bird.getFoodCost(), bird.getWildFoodSlots());
        return player.canPay(cost);
    }

    public void addBird(Bird bird, HabitatType habitat, int column) {
        if (!bird.getHabitats().contains(habitat)) {
            throw new IllegalArgumentException("Bird cannot live in " + habitat);
        }
        if (column < 0 || column >= eggCostByColumn.length) {
            throw new IllegalArgumentException("Invalid column " + column);
        }
        List<Bird> row = habitats.get(habitat);
        if (row.get(column) != null) {
            throw new IllegalStateException("Column already occupied");
        }
        row.set(column, bird);
        bird.setCurrentHabitat(habitat);
    }

    public Iterable<Bird> leftToRight(HabitatType habitat) {
        List<Bird> ordered = new ArrayList<>();
        for (Bird bird : habitats.get(habitat)) {
            if (bird != null) {
                ordered.add(bird);
            }
        }
        return Collections.unmodifiableList(ordered);
    }

    public List<Bird> getBirdsInHabitat(HabitatType habitat) {
        return Collections.unmodifiableList(habitats.get(habitat));
    }

    public List<Bird> getAllBirds() {
        List<Bird> birds = new ArrayList<>();
        for (HabitatType habitat : HabitatType.values()) {
            for (Bird bird : habitats.get(habitat)) {
                if (bird != null) {
                    birds.add(bird);
                }
            }
        }
        return Collections.unmodifiableList(birds);
    }

    public int totalEggsOnBoard() {
        int total = 0;
        for (Bird bird : getAllBirds()) {
            total += bird.getEggs();
        }
        return total;
    }

    public int eggCapacityRemaining() {
        int capacity = 0;
        for (Bird bird : getAllBirds()) {
            capacity += bird.getEggLimit() - bird.getEggs();
        }
        return capacity;
    }

    public void spendEggs(int eggs) {
        if (eggs < 0) {
            throw new IllegalArgumentException("Cannot spend negative eggs");
        }
        int remaining = eggs;
        for (HabitatType habitat : HabitatType.values()) {
            for (Bird bird : habitats.get(habitat)) {
                if (bird == null) {
                    continue;
                }
                int available = bird.getEggs();
                if (available > 0) {
                    int toRemove = Math.min(available, remaining);
                    bird.removeEggs(toRemove);
                    remaining -= toRemove;
                    if (remaining == 0) {
                        return;
                    }
                }
            }
        }
        throw new IllegalStateException("Not enough eggs on board");
    }

    public int getEggCostForColumn(int column) {
        if (column < 0 || column >= eggCostByColumn.length) {
            throw new IllegalArgumentException("Invalid column " + column);
        }
        return eggCostByColumn[column];
    }

    public int getColumnCount() {
        return eggCostByColumn.length;
    }

    public int countBirdsWithNest(NestType nestType) {
        int count = 0;
        for (Bird bird : getAllBirds()) {
            if (bird.getNestType() == nestType) {
                count++;
            }
        }
        return count;
    }
}

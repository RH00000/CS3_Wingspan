import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Player {
    private final String name;
    private final Board board;
    private final EnumMap<FoodType, Integer> food;
    private final List<Bird> hand;
    private final List<BonusCard> bonusCards;

    public Player(String name, Board board) {
        this.name = Objects.requireNonNull(name, "name");
        this.board = Objects.requireNonNull(board, "board");
        this.food = new EnumMap<>(FoodType.class);
        for (FoodType type : FoodType.values()) {
            food.put(type, 0);
        }
        this.hand = new ArrayList<>();
        this.bonusCards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public List<Bird> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public EnumMap<FoodType, Integer> getFood() {
        return new EnumMap<>(food);
    }

    public List<BonusCard> getBonusCards() {
        return Collections.unmodifiableList(bonusCards);
    }

    public void addFood(FoodType type, int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot add negative food");
        }
        food.put(type, food.getOrDefault(type, 0) + amount);
    }

    public boolean hasEggs(int eggs) {
        return board.totalEggsOnBoard() >= eggs;
    }

    public void spendEggs(int eggs) {
        board.spendEggs(eggs);
    }

    public void addBirdToHand(Bird bird) {
        hand.add(Objects.requireNonNull(bird, "bird"));
    }

    public boolean removeBirdFromHand(Bird bird) {
        return hand.remove(bird);
    }

    public void addBonusCard(BonusCard card) {
        bonusCards.add(Objects.requireNonNull(card, "card"));
    }

    public boolean canPay(FoodCost cost) {
        return calculatePayment(cost) != null;
    }

    public Payment pay(FoodCost cost) {
        EnumMap<FoodType, Integer> payment = calculatePayment(cost);
        if (payment == null) {
            throw new IllegalStateException("Player cannot afford the cost");
        }
        for (Map.Entry<FoodType, Integer> entry : payment.entrySet()) {
            FoodType type = entry.getKey();
            int spent = entry.getValue();
            food.put(type, food.getOrDefault(type, 0) - spent);
        }
        return new Payment(payment, 0);
    }

    private EnumMap<FoodType, Integer> calculatePayment(FoodCost cost) {
        EnumMap<FoodType, Integer> available = new EnumMap<>(FoodType.class);
        for (FoodType type : FoodType.values()) {
            available.put(type, food.getOrDefault(type, 0));
        }
        EnumMap<FoodType, Integer> used = new EnumMap<>(FoodType.class);
        for (Map.Entry<FoodType, Integer> requirement : cost.getRequired().entrySet()) {
            FoodType type = requirement.getKey();
            int needed = requirement.getValue();
            boolean success;
            if (type == FoodType.WILD) {
                success = deductFlexibleFood(available, used, needed, FoodType.WORM, FoodType.SEED, FoodType.WILD);
            } else {
                success = deductFlexibleFood(available, used, needed, type);
            }
            if (!success) {
                return null;
            }
        }
        if (cost.getWildsAllowed() > 0) {
            boolean success = deductFlexibleFood(available, used, cost.getWildsAllowed(), FoodType.values());
            if (!success) {
                return null;
            }
        }
        return used;
    }

    private boolean deductFlexibleFood(EnumMap<FoodType, Integer> available,
                                       EnumMap<FoodType, Integer> used,
                                       int needed,
                                       FoodType... options) {
        if (needed <= 0) {
            return true;
        }
        int remaining = needed;
        for (FoodType type : options) {
            if (remaining <= 0) {
                break;
            }
            int have = available.getOrDefault(type, 0);
            if (have <= 0) {
                continue;
            }
            int spend = Math.min(have, remaining);
            available.put(type, have - spend);
            used.put(type, used.getOrDefault(type, 0) + spend);
            remaining -= spend;
        }
        return remaining == 0;
    }

    public void activateHabitat(HabitatType habitat, GameContext ctx) {
        List<Bird> birds = new ArrayList<>();
        for (Bird bird : board.leftToRight(habitat)) {
            birds.add(bird);
        }
        for (int i = birds.size() - 1; i >= 0; i--) {
            Bird bird = birds.get(i);
            Power power = bird.getPower();
            if (power == null || power.getTiming() != PowerTiming.WHEN_ACTIVATED) {
                continue;
            }
            if (!power.isUsedThisTurn()) {
                power.execute(ctx, bird, this);
            }
        }
    }

    public boolean tuckCard(Bird hostBird) {
        if (hand.isEmpty()) {
            return false;
        }
        Bird tucked = hand.remove(0);
        hostBird.addTuckedCards(1);
        return tucked != null;
    }

    public List<Integer> score(boolean includeBonus) {
        int birdPoints = 0;
        for (Bird bird : board.getAllBirds()) {
            birdPoints += bird.getVictoryPoints();
        }
        int eggPoints = board.totalEggsOnBoard();
        int bonusPoints = 0;
        if (includeBonus) {
            for (BonusCard card : bonusCards) {
                bonusPoints += card.calculatePoints(this);
            }
        }
        List<Integer> breakdown = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0));
        breakdown.set(0, birdPoints);
        breakdown.set(1, includeBonus ? bonusPoints : 0);
        breakdown.set(3, eggPoints);
        breakdown.set(6, birdPoints + bonusPoints + eggPoints);
        return Collections.unmodifiableList(breakdown);
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Game {
    private final List<Player> players;
    private final Deck<Bird> birdDeck;
    private final Deck<BonusCard> bonusDeck;
    private final BirdFeeder feeder;
    private final Tray tray;
    private final GoalBoard goalBoard;
    private final GameContext context;
    private final Map<Player, Integer> actionsRemaining;

    private int roundNumber;
    private int activePlayerIndex;

    public Game(List<Player> players,
                Deck<Bird> birdDeck,
                Deck<BonusCard> bonusDeck,
                GoalBoard goalBoard) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("Game requires at least one player");
        }
        this.players = new ArrayList<>(players);
        this.birdDeck = Objects.requireNonNull(birdDeck, "birdDeck");
        this.bonusDeck = Objects.requireNonNull(bonusDeck, "bonusDeck");
        this.goalBoard = Objects.requireNonNull(goalBoard, "goalBoard");
        this.feeder = new BirdFeeder();
        this.tray = new Tray();
        this.context = new GameContext(this.players, birdDeck, bonusDeck, feeder, tray);
        this.actionsRemaining = new HashMap<>();
        this.roundNumber = 0;
        this.activePlayerIndex = 0;
    }

    public Player getCurrentPlayer() {
        return players.get(activePlayerIndex);
    }

    public void startGame() {
        roundNumber = 1;
        activePlayerIndex = 0;
        context.setCurrentPlayer(getCurrentPlayer());
        setupRound();
        context.broadcast(new GameEvent(EventType.TURN_START, getCurrentPlayer(), null));
    }

    private void setupRound() {
        int baseActions = Math.max(5, 9 - roundNumber);
        for (Player player : players) {
            actionsRemaining.put(player, baseActions);
        }
        feeder.rollAll();
        tray.refillAll(birdDeck);
        context.refreshTurn();
    }

    public void nextTurn() {
        Player current = getCurrentPlayer();
        context.broadcast(new GameEvent(EventType.TURN_END, current, null));
        int actionsLeft = actionsRemaining.getOrDefault(current, 0);
        if (actionsLeft > 0) {
            actionsRemaining.put(current, actionsLeft - 1);
        }
        if (!hasTurnsRemainingInRound()) {
            endRound();
            return;
        }
        do {
            activePlayerIndex = (activePlayerIndex + 1) % players.size();
        } while (actionsRemaining.getOrDefault(getCurrentPlayer(), 0) == 0);
        context.setCurrentPlayer(getCurrentPlayer());
        context.refreshTurn();
        context.broadcast(new GameEvent(EventType.TURN_START, getCurrentPlayer(), null));
    }

    private boolean hasTurnsRemainingInRound() {
        for (int remaining : actionsRemaining.values()) {
            if (remaining > 0) {
                return true;
            }
        }
        return false;
    }

    private void endRound() {
        Map<Player, Integer> goalScores = goalBoard.scoreRound(roundNumber, players);
        context.broadcast(new GameEvent(EventType.ROUND_END, null, goalScores));
        roundNumber++;
        if (roundNumber > 4) {
            context.broadcast(new GameEvent(EventType.TURN_END, null, "Game Over"));
            return;
        }
        setupRound();
        context.setCurrentPlayer(getCurrentPlayer());
        context.broadcast(new GameEvent(EventType.TURN_START, getCurrentPlayer(), null));
    }

    public boolean isGameOver() {
        return roundNumber > 4;
    }

    public Map<Player, List<Integer>> scoreGame() {
        Map<Player, List<Integer>> results = new LinkedHashMap<>();
        for (Player player : players) {
            results.put(player, player.score(true));
        }
        return results;
    }

    public void playBird(Player player, Bird bird, HabitatType habitat, int column) {
        validateCurrentPlayer(player);
        if (!player.getHand().contains(bird)) {
            throw new IllegalArgumentException("Bird not in player's hand");
        }
        Board board = player.getBoard();
        if (!board.canPlaceBird(bird, habitat, column, player)) {
            throw new IllegalStateException("Cannot place bird in the requested spot");
        }
        FoodCost cost = new FoodCost(bird.getFoodCost(), bird.getWildFoodSlots());
        player.pay(cost);
        int eggCost = board.getEggCostForColumn(column);
        if (eggCost > 0) {
            if (!player.hasEggs(eggCost)) {
                throw new IllegalStateException("Not enough eggs to pay column cost");
            }
            player.spendEggs(eggCost);
        }
        board.addBird(bird, habitat, column);
        player.removeBirdFromHand(bird);
        context.broadcast(new GameEvent(EventType.PLAYER_PLAY_BIRD, player, bird));
        Power power = bird.getPower();
        if (power != null && power.getTiming() == PowerTiming.WHEN_PLAYED) {
            power.execute(context, bird, player);
        }
    }

    public void gainFood(Player player, FoodType type) {
        validateCurrentPlayer(player);
        if (!feeder.take(type)) {
            throw new IllegalStateException("No die showing " + type);
        }
        player.addFood(type, 1);
        context.broadcast(new GameEvent(EventType.PLAYER_GAIN_FOOD, player, type));
    }

    public void layEggs(Player player, Bird bird, int amount) {
        validateCurrentPlayer(player);
        if (!player.getBoard().getAllBirds().contains(bird)) {
            throw new IllegalArgumentException("Bird not on player's board");
        }
        if (!bird.canAddEggs(amount)) {
            throw new IllegalStateException("Bird cannot hold that many eggs");
        }
        bird.addEggs(amount);
        context.broadcast(new GameEvent(EventType.PLAYER_GAIN_EGG, player, bird));
    }

    public void drawBirdCard(Player player, boolean fromTray, int trayIndex) {
        validateCurrentPlayer(player);
        Bird drawn = null;
        if (fromTray) {
            List<Bird> visible = tray.getVisibleCards();
            if (trayIndex < 0 || trayIndex >= visible.size()) {
                throw new IllegalArgumentException("Invalid tray index");
            }
            Bird selected = visible.get(trayIndex);
            if (selected == null) {
                throw new IllegalStateException("Tray slot is empty");
            }
            tray.removeCard(selected);
            drawn = selected;
            tray.refillSlot(trayIndex, birdDeck);
        } else {
            drawn = birdDeck.draw();
        }
        if (drawn != null) {
            player.addBirdToHand(drawn);
            context.broadcast(new GameEvent(EventType.PLAYER_DRAW_CARD, player, drawn));
        }
    }

    public void activateHabitat(Player player, HabitatType habitat) {
        validateCurrentPlayer(player);
        player.activateHabitat(habitat, context);
        context.broadcast(new GameEvent(EventType.PLAYER_ACTIVATE, player, habitat));
    }

    public BirdFeeder getFeeder() {
        return feeder;
    }

    public Tray getTray() {
        return tray;
    }

    public GameContext getContext() {
        return context;
    }

    public GoalBoard getGoalBoard() {
        return goalBoard;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void validateCurrentPlayer(Player player) {
        if (player != getCurrentPlayer()) {
            throw new IllegalStateException("It is not " + player.getName() + "'s turn");
        }
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GameContext {
    private final List<GameEventListener> listeners = new ArrayList<>();
    private final List<Player> players;
    private final Deck<Bird> birdDeck;
    private final Deck<BonusCard> bonusDeck;
    private final BirdFeeder feeder;
    private final Tray tray;
    private Player currentPlayer;

    public GameContext(List<Player> players,
                       Deck<Bird> birdDeck,
                       Deck<BonusCard> bonusDeck,
                       BirdFeeder feeder,
                       Tray tray) {
        this.players = Collections.unmodifiableList(new ArrayList<>(players));
        this.birdDeck = Objects.requireNonNull(birdDeck, "birdDeck");
        this.bonusDeck = Objects.requireNonNull(bonusDeck, "bonusDeck");
        this.feeder = Objects.requireNonNull(feeder, "feeder");
        this.tray = Objects.requireNonNull(tray, "tray");
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Deck<Bird> getBirdDeck() {
        return birdDeck;
    }

    public Deck<BonusCard> getBonusDeck() {
        return bonusDeck;
    }

    public BirdFeeder getFeeder() {
        return feeder;
    }

    public Tray getTray() {
        return tray;
    }

    public void addListener(GameEventListener listener) {
        listeners.add(listener);
    }

    public void broadcast(GameEvent event) {
        for (GameEventListener listener : listeners) {
            listener.onEvent(this, event);
        }
    }

    public void refreshTurn() {
        if (currentPlayer == null) {
            return;
        }
        for (Bird bird : currentPlayer.getBoard().getAllBirds()) {
            Power power = bird.getPower();
            if (power != null) {
                power.refreshEachTurn();
            }
        }
    }

    public Player getPlayerOwningPower(Power power) {
        if (power == null) {
            return null;
        }
        for (Player player : players) {
            for (Bird bird : player.getBoard().getAllBirds()) {
                if (bird.getPower() == power) {
                    return player;
                }
            }
        }
        return null;
    }
}

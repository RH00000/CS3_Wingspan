import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Tray {
    private final List<Bird> trayCards;

    public Tray() {
        trayCards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            trayCards.add(null);
        }
    }

    public boolean removeCard(Bird bird) {
        for (int i = 0; i < trayCards.size(); i++) {
            Bird current = trayCards.get(i);
            if (current == bird) {
                trayCards.set(i, null);
                return true;
            }
        }
        return false;
    }

    public List<Bird> getVisibleCards() {
        return Collections.unmodifiableList(trayCards);
    }

    public void refillSlot(int index, Deck<Bird> deck) {
        Objects.requireNonNull(deck, "deck");
        if (index < 0 || index >= trayCards.size()) {
            throw new IllegalArgumentException("Invalid tray slot " + index);
        }
        if (trayCards.get(index) == null && !deck.isEmpty()) {
            trayCards.set(index, deck.draw());
        }
    }

    public void refillAll(Deck<Bird> deck) {
        for (int i = 0; i < trayCards.size(); i++) {
            refillSlot(i, deck);
        }
    }
}

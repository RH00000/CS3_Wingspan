import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck<T> {
    private final Deque<T> cards;

    public Deck(List<T> initialCards, boolean shuffle) {
        List<T> working = new ArrayList<>(initialCards);
        if (shuffle) {
            Collections.shuffle(working);
        }
        this.cards = new ArrayDeque<>(working);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public T draw() {
        return cards.pollFirst();
    }

    public void discard(T card) {
        if (card != null) {
            cards.offerLast(card);
        }
    }
}

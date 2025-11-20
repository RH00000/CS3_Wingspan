import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BirdFeeder {
    private static final FoodType[] FACES = {
            FoodType.WORM,
            FoodType.SEED,
            FoodType.FISH,
            FoodType.RODENT,
            FoodType.FRUIT,
            FoodType.WILD
    };

    private final List<Die> dice;
    private final List<Die> diceOutside;

    public BirdFeeder() {
        dice = new ArrayList<>();
        diceOutside = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Die die = new Die();
            die.roll();
            dice.add(die);
        }
    }

    public void rollAll() {
        dice.addAll(diceOutside);
        diceOutside.clear();
        for (Die die : dice) {
            die.roll();
        }
    }

    public List<FoodType> faces() {
        List<FoodType> faces = new ArrayList<>();
        for (Die die : dice) {
            faces.add(die.getFace());
        }
        return Collections.unmodifiableList(faces);
    }

    public List<FoodType> facesOutside() {
        List<FoodType> faces = new ArrayList<>();
        for (Die die : diceOutside) {
            faces.add(die.getFace());
        }
        return Collections.unmodifiableList(faces);
    }

    public boolean allFacesSame() {
        if (dice.isEmpty()) {
            return false;
        }
        FoodType first = dice.get(0).getFace();
        for (Die die : dice) {
            if (die.getFace() != first) {
                return false;
            }
        }
        return true;
    }

    public void rerollIfAllSame() {
        if (allFacesSame()) {
            rollAll();
        }
    }

    public boolean take(FoodType type) {
        for (int i = 0; i < dice.size(); i++) {
            Die die = dice.get(i);
            if (die.getFace() == type) {
                diceOutside.add(die);
                dice.remove(i);
                if (dice.isEmpty()) {
                    rollAll();
                } else {
                    rerollIfAllSame();
                }
                return true;
            }
        }
        return false;
    }
    
    public List<Die> getDice() {
    	return dice;
    }
    
    public List<Die> getDiceOutside() {
    	return diceOutside;
    }

    public static class Die {
        private static final Random RANDOM = new Random();
        private FoodType face;

        public void roll() {
            face = FACES[RANDOM.nextInt(FACES.length)];
        }

        public FoodType getFace() {
            return face;
        }
    }
}

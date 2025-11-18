import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WingspanDemo {
    public static void main(String[] args) throws IOException {
        List<Bird> birds = BirdCsvLoader.loadFromResource("data/birds.csv");
        if (birds.isEmpty()) {
            throw new IllegalStateException("Bird CSV did not load any cards");
        }
        int upperBound = Math.min(25, birds.size());
        List<Bird> sampleBirds = new ArrayList<>(birds.subList(0, upperBound));

        Board board1 = new Board(new int[]{0, 0, 1, 1, 2});
        Board board2 = new Board(new int[]{0, 0, 1, 1, 2});
        Player alice = new Player("Alice", board1);
        Player bob = new Player("Bob", board2);

        Deck<Bird> birdDeck = new Deck<>(sampleBirds, true);
        Deck<BonusCard> bonusDeck = new Deck<>(Arrays.asList(
                new FoodDiversityBonusCard(),
                new NestTypeBonusCard(NestType.BOWL)), false);
        GoalBoard goalBoard = new GoalBoard(Arrays.asList(
                new RoundGoal("Goal 1", "Placeholder", 1, 0),
                new RoundGoal("Goal 2", "Placeholder", 2, 0),
                new RoundGoal("Goal 3", "Placeholder", 3, 0),
                new RoundGoal("Goal 4", "Placeholder", 4, 0)
        ));

        Game game = new Game(Arrays.asList(alice, bob), birdDeck, bonusDeck, goalBoard);
        game.startGame();

        // Turn 1: Alice draws a bird
        game.drawBirdCard(alice, false, 0);
        game.nextTurn();

        // Turn 2: Bob draws a bird
        game.drawBirdCard(bob, false, 0);
        game.nextTurn();

        // Turn 3: Alice plays her bird (grant needed food first for demo)
        if (!alice.getHand().isEmpty()) {
            Bird aliceBird = alice.getHand().get(0);
            aliceBird.getFoodCost().forEach(alice::addFood);
            HabitatType habitat = aliceBird.getHabitats().isEmpty()
                    ? HabitatType.FOREST
                    : aliceBird.getHabitats().iterator().next();
            game.playBird(alice, aliceBird, habitat, 0);
        }
        game.nextTurn();

        // Turn 4: Bob plays his bird
        if (!bob.getHand().isEmpty()) {
            Bird bobBird = bob.getHand().get(0);
            bobBird.getFoodCost().forEach(bob::addFood);
            HabitatType habitat = bobBird.getHabitats().isEmpty()
                    ? HabitatType.FOREST
                    : bobBird.getHabitats().iterator().next();
            game.playBird(bob, bobBird, habitat, 0);
        }
        game.nextTurn();

        // Turn 5: Alice lays eggs on her bird
        if (!alice.getBoard().getAllBirds().isEmpty()) {
            Bird firstBird = alice.getBoard().getAllBirds().get(0);
            game.layEggs(alice, firstBird, 2);
        }


        System.out.println("=== Final State ===");
        printPlayerState(alice);
        printPlayerState(bob);
    }

    private static void printPlayerState(Player p) {
    System.out.println("Player: " + p.getName());
    System.out.println("  Food: " + p.getFood());
    System.out.println("  Birds on board:");
    p.getBoard().getAllBirds().forEach(b -> {
        System.out.println("    - " + b.getName()
                + " (VP=" + b.getVictoryPoints()
                + ", eggs=" + b.getEggs() + ")");
    });
    System.out.println("  Hand:");
    p.getHand().forEach(b -> {
        System.out.println("    - " + b.getName());
    });
    System.out.println("  Score breakdown: " + p.score(true));
    System.out.println();
}
}


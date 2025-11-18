import java.util.HashMap;
import java.util.Map;

public class RoundGoal {
    private final String name;
    private final String description;
    private final int roundNumber;
    private final int goalType;
    private final Map<Player, Integer> playerScores = new HashMap<>();

    public RoundGoal(String name, String description, int roundNumber, int goalType) {
        this.name = name;
        this.description = description;
        this.roundNumber = roundNumber;
        this.goalType = goalType;
    }

    public String getName() {
        return name;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getGoalType() {
        return goalType;
    }

    public int calculate(Player player) {
        return 0;
    }

    public void recordScore(Player player) {
        playerScores.put(player, calculate(player));
    }

    public int getPlayerScore(Player player) {
        return playerScores.getOrDefault(player, 0);
    }
}

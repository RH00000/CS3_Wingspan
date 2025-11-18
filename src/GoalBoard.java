import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoalBoard {
    private final List<RoundGoal> goals;

    public GoalBoard(List<RoundGoal> goals) {
        if (goals == null || goals.size() < 4) {
            throw new IllegalArgumentException("Goal board requires four round goals");
        }
        this.goals = goals;
    }

    public Map<Player, Integer> scoreRound(int round, List<Player> players) {
        Map<Player, Integer> scores = new HashMap<>();
        if (round <= 0 || round > goals.size()) {
            return scores;
        }
        RoundGoal goal = goals.get(round - 1);
        for (Player player : players) {
            goal.recordScore(player);
            scores.put(player, goal.getPlayerScore(player));
        }
        return scores;
    }
}

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PowerFactory {

    private PowerFactory() {
    }

    public static Power fromCsv(String powerType,
                                String powerCategory,
                                String powerDetails) {
        String details = powerDetails == null ? "" : powerDetails.trim();
        PowerTiming timing = parseTiming(powerType);
        String category = powerCategory == null ? "" : powerCategory.trim().toLowerCase(Locale.US);

        if (category.contains("caching")) {
            int amount = parseFirstInteger(details, 1);
            FoodType targetFood = detectFoodType(details);
            return new CacheFoodPower(timing, amount, details, targetFood);
        }
        if (category.contains("draw") || details.toLowerCase(Locale.US).contains("draw")) {
            int amount = parseFirstInteger(details, 1);
            return new DrawCardPower(timing, amount, details);
        }

        if (details.isEmpty()) {
            return null;
        }
        return new NoOpPower(timing, details);
    }

    private static PowerTiming parseTiming(String powerType) {
        if (powerType == null) {
            return PowerTiming.WHEN_ACTIVATED;
        }
        String normalized = powerType.trim().toLowerCase(Locale.US);
        switch (normalized) {
            case "whenplayed":
            case "when played":
                return PowerTiming.WHEN_PLAYED;
            case "between":
            case "between turns":
            case "between turn":
                return PowerTiming.BETWEEN_TURNS;
            case "activated":
            default:
                return PowerTiming.WHEN_ACTIVATED;
        }
    }

    private static int parseFirstInteger(String text, int defaultValue) {
        Matcher matcher = Pattern.compile("(\\d+)").matcher(text);
        if (matcher.find()) {
            try {
                return Integer.parseInt(matcher.group(1));
            } catch (NumberFormatException ignored) {
            }
        }
        return defaultValue;
    }

    private static FoodType detectFoodType(String details) {
        String lower = details.toLowerCase(Locale.US);
        if (lower.contains("seed")) {
            return FoodType.SEED;
        }
        if (lower.contains("worm") || lower.contains("invertebrate")) {
            return FoodType.WORM;
        }
        if (lower.contains("fruit") || lower.contains("berry")) {
            return FoodType.FRUIT;
        }
        if (lower.contains("fish")) {
            return FoodType.FISH;
        }
        if (lower.contains("rodent") || lower.contains("mouse")) {
            return FoodType.RODENT;
        }
        if (lower.contains("wild")) {
            return FoodType.WILD;
        }
        return null;
    }

    private static final class NoOpPower extends Power {
        private NoOpPower(PowerTiming timing, String description) {
            super(timing, description);
        }

        @Override
        public void execute(GameContext ctx, Bird bird, Player owner) {
            usedThisTurn = true;
        }
    }
}

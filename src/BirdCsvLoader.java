import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

public final class BirdCsvLoader {

    private BirdCsvLoader() {
    }

    public static List<Bird> loadFromCsv(Path csvPath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(csvPath, StandardCharsets.UTF_8)) {
            return loadFromReader(reader);
        }
    }

    public static List<Bird> loadFromResource(String resourcePath) throws IOException {
        String normalized = resourcePath.startsWith("/") ? resourcePath.substring(1) : resourcePath;
        ClassLoader loader = BirdCsvLoader.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream(normalized)) {
            if (in == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                return loadFromReader(reader);
            }
        }
    }

    private static List<Bird> loadFromReader(BufferedReader reader) throws IOException {
        List<Bird> birds = new ArrayList<>();
        String line;
        boolean headerSkipped = false;
        while ((line = reader.readLine()) != null) {
            if (!headerSkipped) {
                headerSkipped = true;
                continue;
            }
            if (line.trim().isEmpty()) {
                continue;
            }
            List<String> columns = parseCsvLine(line);
            if (columns.size() < 22) {
                continue;
            }
            Bird bird = parseBird(columns);
            if (bird != null) {
                birds.add(bird);
            }
        }
        return birds;
    }

    private static Bird parseBird(List<String> columns) {
        String englishName = columns.get(2).trim();
        String powerType = columns.get(3);
        String powerCategory = columns.get(4);
        String powerDetails = columns.get(5);

        int victoryPoints = parseInt(columns.get(8));
        NestType nestType = parseNestType(columns.get(9));
        int eggLimit = parseInt(columns.get(10));
        int wingspan = parseInt(columns.get(11));

        EnumSet<HabitatType> habitats = EnumSet.noneOf(HabitatType.class);
        if (isYes(columns.get(12))) {
            habitats.add(HabitatType.FOREST);
        }
        if (isYes(columns.get(13))) {
            habitats.add(HabitatType.GRASSLAND);
        }
        if (isYes(columns.get(14))) {
            habitats.add(HabitatType.WETLAND);
        }

        EnumMap<FoodType, Integer> foodCost = new EnumMap<>(FoodType.class);
        putFood(foodCost, FoodType.WILD, columns.get(16));
        putFood(foodCost, FoodType.WORM, columns.get(17));
        putFood(foodCost, FoodType.SEED, columns.get(18));
        putFood(foodCost, FoodType.FRUIT, columns.get(19));
        putFood(foodCost, FoodType.FISH, columns.get(20));
        putFood(foodCost, FoodType.RODENT, columns.get(21));

        Power power = PowerFactory.fromCsv(powerType, powerCategory, powerDetails);

        return new Bird(
                englishName,
                victoryPoints,
                foodCost,
                0,
                habitats,
                nestType,
                eggLimit,
                wingspan,
                power,
                null,
                null,
                null,
                null
        );
    }

    private static void putFood(EnumMap<FoodType, Integer> map, FoodType type, String raw) {
        int amount = parseInt(raw);
        if (amount > 0) {
            map.put(type, amount);
        }
    }

    private static NestType parseNestType(String raw) {
        if (raw == null) {
            return NestType.BOWL;
        }
        String normalized = raw.trim().toUpperCase();
        switch (normalized) {
            case "CAVITY":
                return NestType.CAVITY;
            case "PLATFORM":
                return NestType.PLATFORM;
            case "STAR":
                return NestType.STAR;
            case "GROUND":
            case "BOWL":
            default:
                return NestType.BOWL;
        }
    }

    private static int parseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private static boolean isYes(String value) {
        return value != null && value.trim().equalsIgnoreCase("y");
    }

    private static List<String> parseCsvLine(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                tokens.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        tokens.add(current.toString());
        return tokens;
    }
}

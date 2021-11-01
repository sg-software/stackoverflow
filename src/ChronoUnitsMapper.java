import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Showcases a possibility how chrono units can be extracted from a String
 * with the help of regular expressions registered in a map and a function.
 */
public class ChronoUnitsMapper {

    private static final Map<ChronoUnit,String> durationRegexMap = Map.ofEntries(
            Map.entry(ChronoUnit.YEARS,"\\d+ (years|year)"),
            Map.entry(ChronoUnit.MONTHS,"\\d+ (months|month)"),
            Map.entry(ChronoUnit.DAYS, "\\d+ (days|day)")
    );

    private Map<ChronoUnit, Integer> parseDuration(String durationString) {
        return new MapStringToChronoUnitsFunction(durationRegexMap)
                .apply(durationString);
    }

    static class MapStringToChronoUnitsFunction implements Function<String, Map<ChronoUnit, Integer>> {

        Map<ChronoUnit,String> durationRegexMap = new HashMap<>();

        public MapStringToChronoUnitsFunction(Map<ChronoUnit,String> durationByRegex) {
            durationRegexMap.putAll(durationByRegex);
        }

        @Override
        public Map<ChronoUnit, Integer> apply(String textWithDurations) {
            String[] splittedTextWithDurations = textWithDurations.split(",");

            return this.durationRegexMap.entrySet().stream()
                    .flatMap(regex -> Arrays.stream(splittedTextWithDurations)
                            .map(String::trim)
                            .filter(trimmedDurationString -> trimmedDurationString.matches(regex.getValue()))
                            .map(matchingTrimmedDurationString -> matchingTrimmedDurationString.replaceAll("\\w+[a-zA-Z]", " "))
                            .map(String::trim)
                            .map(t -> Map.entry(regex.getKey(),Integer.valueOf(t))))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
    }

    public static void main (String args[]) {
        ChronoUnitsMapper chronoUnitsMapper = new ChronoUnitsMapper();

        Map<ChronoUnit, Integer> durationList = chronoUnitsMapper.parseDuration("1 years, 2 months, 22 days");

        Map<ChronoUnit, Integer> durationList2 = chronoUnitsMapper.parseDuration("2 months, 22 days");

        System.out.println(durationList);
        System.out.println(durationList2);
    }
}

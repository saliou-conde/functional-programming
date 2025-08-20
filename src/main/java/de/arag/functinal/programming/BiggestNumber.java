package de.arag.functinal.programming;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class BiggestNumber {

    private final Path path;

    public BiggestNumber(Path filePath) {
        this.path = filePath;
    }

    private String readFile(Path path) throws IOException {
        log.info("Reading file from: {}", path);
        return Files.readString(path);
    }

    /**
     * Convert a String-Content in a List of Long-values.
     * Empty or invalid rows will be ignored.
     */
    public List<Long> parseNumbers(String content) {
        return Arrays.stream(content.split("\n"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Long::parseLong)
                .toList();
    }

    /**
     * Returns the frequencies of the numbers in the file, sorted by descending frequency.
     */
    public Map<Long, Long> countFrequenciesSortedByOccurrence(int limit) throws IOException {
        String content = readFile(path);

        Map<Long, Long> frequencyMap = parseNumbers(content).stream()
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

        return frequencyMap.entrySet().stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed())
                .limit(limit)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,  // merge function (here not relevant)
                        LinkedHashMap::new
                ));
    }
}

package de.arag.functinal.programming.assessment;

import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor
public class TextAnalyzer {
    private final Map<String, Integer> wordFrequency = new HashMap<>();

    public TextAnalyzer(Path filePath) throws IOException {
        String content = Files.readString(filePath);
        processText(content);
    }

    public TextAnalyzer(String text) {
        processText(text);
    }

    private void processText(String text) {
        wordFrequency.clear();
        String [] words = splitText(text);

        for (String word : words) {
            if (!word.isBlank()) {
                wordFrequency.merge(word, 1, Integer::sum);
            }
        }
    }

    public int getTotalWordCount() {
        return wordFrequency.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int getWordCount(String word) {
        return wordFrequency.getOrDefault(word.toLowerCase(), 0);
    }

    public List<Map.Entry<String, Integer>> getTopWords(int limit) {
        return wordFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Map<String, Long> wortOccurrences(Path filePath) throws IOException {
        List<String> words = Arrays.asList(splitText(Files.readString(filePath)));

        Map<String, Long> linkedHashMap = words.stream()
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

        return linkedHashMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 9)
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a,b)-> a,
                        LinkedHashMap::new
                ));
    }

    private String[] splitText(String text) {
        return text
                .toLowerCase()
                .replaceAll("[^a-z0-9äöüß]+", " ")
                .trim()
                .split("\\s+");
    }
}

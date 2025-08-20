package de.arag.functinal.programming.assessment;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class TextAnalyzerTest {

    private final TextAnalyzer textAnalyzer = new TextAnalyzer();

    private final String sampleText = """
            c
            """;

    @Test
    void testTotalWordCountContainingInString() {
        TextAnalyzer analyzer = new TextAnalyzer(sampleText);
        assertThat(16).isEqualTo(analyzer.getTotalWordCount());
    }

    @Test
    void testWordFrequency() {
        TextAnalyzer analyzer = new TextAnalyzer(sampleText);
        assertThat(3).isEqualTo(analyzer.getWordCount("java"));
        assertThat(1).isEqualTo(analyzer.getWordCount("backend"));
        assertThat(0).isEqualTo(analyzer.getWordCount("python"));
    }

    @Test
    void testTopWords() {
        TextAnalyzer analyzer = new TextAnalyzer(sampleText);
        List<Map.Entry<String, Integer>> topWords = analyzer.getTopWords(3);

        assertThat("java").isEqualTo(topWords.getFirst().getKey());
        assertThat(3).isEqualTo(topWords.getFirst().getValue());
    }

    @Test
    void wortOccurrences() throws IOException, URISyntaxException {
        //Given
        Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                        .getResource("assessment/words1.txt"))
                .toURI());

        //When
        var response = textAnalyzer.wortOccurrences(path);

        //Then
        assertThat(response).isNotNull().hasSize(3);
    }

    @Test
    void testTotalWordCountContainingInFile() throws URISyntaxException, IOException {
        //Given
        Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                        .getResource("assessment/words.txt"))
                .toURI());

        //When
        TextAnalyzer analyzer = new TextAnalyzer(path);
        assertThat(19).isEqualTo(analyzer.getTotalWordCount());
    }

}
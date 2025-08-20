package de.arag.functinal.programming;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class BiggestNumberTest {

    @Test
    void topFrequentNumbers() throws URISyntaxException, IOException {
        //Given
        long startTime = System.nanoTime();
        Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                        .getResource("assessment/integer_list_1m.txt"))
                .toURI());
        BiggestNumber biggestNumber = new BiggestNumber(path);

        Long expectedKey = 47L;
        Long expectedValue = 10259L;
        int limit = 100;

        //When
        var response = biggestNumber.countFrequenciesSortedByOccurrence(limit);

        //Then
        assertThat(response).isNotNull().hasSize(limit);
        assertThat(response.get(expectedKey)).isEqualTo(expectedValue);
        assertThat(response.entrySet().iterator().next().getValue()).isEqualTo(expectedValue);
        long endTime = System.nanoTime();
        long durationInNano = endTime - startTime;
        long durationInMillis = durationInNano / 1_000_000;

        System.out.println("Execution-Time : " + durationInMillis + " ms");
    }
}
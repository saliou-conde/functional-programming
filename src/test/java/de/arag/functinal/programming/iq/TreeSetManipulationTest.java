package de.arag.functinal.programming.iq;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.TreeSet;

class TreeSetManipulationTest {

    @Test
    void should_not_print_null_elements() {
        //Given
        var expectedSize = 3;
        TreeSet<String> set = new TreeSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        //set.add(null);

        //When
        set.stream().toList().forEach(System.out::println);

        //Then
        Assertions.assertThat(set).hasSize(expectedSize);
    }
}

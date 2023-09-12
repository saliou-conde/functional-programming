package de.arag.functinal.programming.utility;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ListWithoutDuplicateTest {


    @Test
    void listWithoutDuplicateTest() {
        //Given
        ListWithoutDuplicate<String> withoutDuplicate = new ListWithoutDuplicate<>();

        //When
        withoutDuplicate.add("Hallo");
        withoutDuplicate.add("Hallo");
        withoutDuplicate.add("Saliou");
        withoutDuplicate.add("this just a test.");
        withoutDuplicate.add("this just a test.");

        //Then
        assertThat(withoutDuplicate.size()).isEqualTo(3);
    }
}
package de.arag.functinal.programming.iq;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DisplaySubStringTest {

    private final DisplaySubString displaySubString = DisplaySubString.getDisplaySubString();

    @Test
    void displaySubString() {
        //Given for example
        String txt ="ab cd ef ij kl";
        String[] expectedResult = {"abc", "def", "ijk", "l"};

        //When
        var response = displaySubString.displaySubString(txt, 3);

        //Then
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(expectedResult);
    }
}
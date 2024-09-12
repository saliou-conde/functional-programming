package de.arag.functinal.programming.iq;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class InterviewQuestion4YearsExperienceTest {

    private final InterviewQuestion4YearsExperience interviewQuestion = new InterviewQuestion4YearsExperience();

    @Test
    void sumOfEvenNumbers() {
        //Given
        List<Integer> integers = List.of(1,2,3,4,5,6,7,8,9,10);
        var expectedSum = 30;

        //When
        var sumOfEvenNumbers = interviewQuestion.sumOfEvenNumbers(integers);
        var sum = integers.stream().filter(i -> i%2==0).reduce(0, Integer::sum);

        //Then
        assertThat(sumOfEvenNumbers).isEqualTo(expectedSum);
        Assertions.assertThat(sum.intValue()).isSameAs((int)sumOfEvenNumbers);
    }
}
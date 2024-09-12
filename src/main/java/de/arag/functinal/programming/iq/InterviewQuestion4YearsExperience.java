package de.arag.functinal.programming.iq;

import java.util.List;

import static java.util.stream.Collectors.summarizingInt;

public class InterviewQuestion4YearsExperience {

    public long sumOfEvenNumbers(List<Integer> numbers) {
        return numbers
                .stream().filter(el -> el%2==0).toList() //Find all even numbers
                .stream().collect(summarizingInt(Integer::intValue)).getSum(); // Calculate the sum 
    }
}

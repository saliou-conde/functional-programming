package de.arag.functinal.programming.iq;

import de.arag.functinal.programming.model.dto.EmployeeRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class InterviewQuestionTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterviewQuestionTest.class);
    private final InterviewQuestion interviewQuestion = InterviewQuestion.getInterviewQuestion();

    @Test
    void countElements() {
        //Given
        List<String> list = List.of("Zebra", "Tiger", "Mango", "Mango", "Ananas", "Ananas", "Apple", "Apple", "Banana", "Banana", "Citron", "Banana", "Banana");

        //When
        var result = interviewQuestion.countElements(list);

        //Then
        assertThat(result).isNotNull();
    }

    @Test
    void distinctList() {
        //Given
        List<String> list = List.of("Mango", "Mango", "Ananas", "Ananas", "Apple", "Apple", "Banana", "Banana", "Microservices", "Macro services");

        //When
        var stringList = interviewQuestion.distinctList(list);
        var result = interviewQuestion.sortList(stringList);

        //Then
        boolean contains = result.contains("Mango");
        assertThat(contains).isTrue();
    }

    @Test
    void highestElement() {
        //Given
        List<String> list = List.of("Mango", "Mango", "Ananas", "Ananas", "Apple", "Apple", "Banana", "Banana", "Microservices", "Macro services");
        var expected = "Macro services";

        //When
        var actual = interviewQuestion.highestElement(list);
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void duplicateElements() {
        //Given
        List<String> list = List.of("Mango", "Mango", "Ananas", "Ananas", "Apple", "Apple", "Banana", "Banana", "Citron", "Banana", "Banana");

        //When
        var result = interviewQuestion.duplicateElements(list);

        //Then
        assertThat(result).isNotNull();
    }

    @Test
    void flatMapList() {
        //Given
        List<String> list1 = List.of("Ananas", "Banana", "Citron", "Destroy");
        List<String> list2 = List.of("Employee", "Finder", "Guys", "Hello");
        List<List<String>> lists = List.of(list1, list2);

        //When
        var result = interviewQuestion.flatMapList(lists);

        //Then
        boolean contains = result.contains("Hello");
        assertThat(contains).isTrue();
    }

    @Test
    void findNthHighestSalary() {
        //Given
        Map<String, Integer> map = Map.of("Saliou", 1500, "Aliou", 1600, "Tom", 1600, "Ankit", 1200, "Daniel", 1700, "James", 1700);
        Integer expected = 1600;

        //When
        var result = interviewQuestion.findNthHighestSalary(map, 2);

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getKey()).isEqualTo(expected);
    }

    @Test
    void firstOccurrenceElement() {
        //Given
        List<String> list = List.of("Zebra", "Zebra", "Tiger", "Mango", "Mango", "Ananas", "Ananas", "Apple", "Apple", "Banana", "Banana", "Citron", "Banana", "Banana");

        //When
        var result = interviewQuestion.firstOccurrenceElement(list);

        //Then
        assertThat(result).isNotNull();
    }

    @Test
    void joining() {
        //Given
        var expected = "[Mango, Ananas, Apple, Banana]";
        List<String> list = List.of("Mango", "Ananas", "Apple", "Banana");

        //When
        var result = interviewQuestion.joining(list);

        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void groupingByField() {
        //Given
        var expectedSize = 3;

        EmployeeRequest employeeRequest1 = EmployeeRequest.builder()
                .email("saliou-conde@gmx.de")
                .lastName("Conde")
                .firstName("Saliou").build();

        EmployeeRequest employeeRequest2 = EmployeeRequest.builder()
                .email("aliou-conde@gmx.de")
                .lastName("Conde")
                .firstName("Aliou").build();

        EmployeeRequest employeeRequest3 = EmployeeRequest.builder()
                .email("khadija-conde@gmx.de")
                .lastName("Conde")
                .firstName("Khadija").build();

        EmployeeRequest employeeRequest4 = EmployeeRequest.builder()
                .email("ibrahim-diallo@gmx.de")
                .lastName("Diallo")
                .firstName("Ibrahim").build();

        EmployeeRequest employeeRequest5 = EmployeeRequest.builder()
                .email("aliou-balde@gmx.de")
                .lastName("Balde")
                .firstName("Aliou").build();

        List<EmployeeRequest> employeeRequests = List.of(employeeRequest1, employeeRequest2, employeeRequest3, employeeRequest4, employeeRequest5);

        //When
        var result = interviewQuestion.groupingByField(employeeRequests, EmployeeRequest::getLastName);

        //Then
        assertThat(result).isNotNull();
        List<EmployeeRequest> listMap = result.get("Conde");
        assertThat(listMap).isNotNull();
        var actualSize = listMap.size();
        assertThat(actualSize).isEqualTo(expectedSize);
        LOGGER.info(listMap.toString());
    }

    @Test
    void intSummaryStatistics() {
        //Given
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        var average = 3.5;

        //When
        var intSummaryStatistics = interviewQuestion.intSummaryStatistics(list);

        //Then
        assertThat(intSummaryStatistics.getCount()).isEqualTo(6);
        assertThat(intSummaryStatistics.getMax()).isEqualTo(6);
        assertThat(intSummaryStatistics.getMin()).isEqualTo(1);
        assertThat(intSummaryStatistics.getAverage()).isEqualTo(average);
    }

    @Test
    void mapList() {
        //Given
        List<String> list = List.of("Mango", "Ananas", "Apple", "Banana");

        //When
        var result = interviewQuestion.mapList(list);

        //Then
        boolean contains = result.contains("MANGO");
        assertThat(contains).isTrue();
    }

    @Test
    void partitioningByEvenAndOdd() {
        //Given
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);

        //When
        var result = interviewQuestion.partitioningByEvenAndOdd(list);

        //Then
        assertThat(result).isNotNull();
    }

    @Test
    void reduce() {
        //Given
        List<Integer> list = List.of(1, 2, 3, 4, 5);

        //When
        var result = interviewQuestion.multipleElements(list);

        //Then
        assertThat(result).isEqualTo(120);
    }

    @Test
    void secondHighestElement() {
        //Given
        List<Integer> list = List.of(120, 120, 1, 2, 3, 2, 3, 4, 5, 1, 7, 70, 6, 120);
        int expected = 70;

        //When
        var result = interviewQuestion.secondHighestElement(list);

        //Then
        assertThat(result).isNotNull().isEqualTo(expected);
    }

    @Test
    void summarizeSameElement() {
        //Given
        List<Integer> list = List.of(1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0);
        List<Integer> expected = List.of(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1);

        //When
        var result = list.stream().sorted().toList();
        LOGGER.info(result.toString());

        assertThat(result).isNotNull().isEqualTo(expected);
    }

    @Test
    void uniqueElements() {
        //Given
        List<String> list = List.of("Zebra", "Tiger", "Mango", "Mango", "Ananas", "Ananas", "Apple", "Apple", "Banana", "Banana", "Citron", "Banana", "Banana");

        //When
        var result = interviewQuestion.uniqueElements(list);

        //Then
        assertThat(result).isNotNull()
                .hasSize(3)
                .containsEntry("Zebra", 1L)
                .containsEntry("Tiger", 1L)
                .containsEntry("Citron", 1L);
    }
}


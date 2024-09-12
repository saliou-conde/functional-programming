package de.arag.functinal.programming.model.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StudentRequestTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRequestTest.class);

    private List<StudentRequest> studentRequests;

    @BeforeEach
    void setUp() {
        studentRequests = List.of(
                StudentRequest.builder().id(1).firstName("Rohit").age(30).gender("Male").dept("Mechanical Engineering").city("Mumbai").rank(122).contacts(List.of("+912632632782", "+1673434729929")).lastName("").build(),
                StudentRequest.builder().id(2).firstName("Pulkit").age(56).gender("Male").dept("Computer Engineering").city("Delhi").rank(67).contacts(List.of("+912632632762", "+1673434729929")).lastName("").build(),
                StudentRequest.builder().id(3).firstName("Ankit").age(25).gender("Female").dept("Mechanical Engineering").city("Kerala").rank(164).contacts(List.of("+912632632882", "+1673434729929")).lastName("").build(),
                StudentRequest.builder().id(4).firstName("Statish Ray").age(30).gender("Male").dept("Mechanical Engineering").city("Kerala").rank(26).contacts(List.of("+912632832782", "+1673434729929")).lastName("").build(),
                StudentRequest.builder().id(5).firstName("Roshan").age(23).gender("Male").dept("Bitoec Engineering").city("Mumbai").rank(12).contacts(List.of("+912632532782")).lastName("").build(),
                StudentRequest.builder().id(6).firstName("Chetan").age(24).gender("Male").dept("Mechanical Engineering").city("Karnataka").rank(90).contacts(List.of("+912632632782", "+1673434729929")).lastName("").build(),
                StudentRequest.builder().id(7).firstName("Arun").age(26).gender("Male").dept("Electronics Engineering").city("Karnataka").rank(324).contacts(List.of("+912632632782", "+1673434729929")).lastName("").build(),
                StudentRequest.builder().id(8).firstName("Nam").age(31).gender("Male").dept("Computer Engineering").city("Karnataka").rank(433).contacts(List.of("+912632632782", "+1673434729929")).lastName("").build(),
                StudentRequest.builder().id(9).firstName("Sonu").age(27).gender("Female").dept("Computer Engineering").city("Karnataka").rank(7).contacts(List.of("+912632632782", "+1673434729929")).lastName("").build(),
                StudentRequest.builder().id(10).firstName("Shubham").age(26).gender("Male").dept("Instrumentation Engineering").city("Mumbai").rank(98).contacts(List.of("+912632632782", "+1673434729929")).lastName("").build()
        );
    }

    @Test
    void should_return_students_by_ranking_between_50_and_100() {
        //Given
        int expected = 3;

        //When
        List<StudentRequest> result = studentRequests.stream().filter(request -> request.getRank() > 50 && request.getRank() < 100).toList();
        result.forEach(r -> LOGGER.info("{}", r.toString()));
        int actual = result.size();

        //Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_return_students_by_city_and_sort_by_names() {
        //Given
        int expected = 4;

        //When
        var result = studentRequests.stream()
                .filter(request -> request.getCity().equals("Karnataka"))
                .sorted(Comparator.comparing(StudentRequest::getFirstName, Comparator.reverseOrder())).toList();
        result.forEach(r -> LOGGER.info("{}", r.toString()));
        int actual = result.size();

        //Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_return_students_with_all_contacts_as_set() {
        //Given
        int expected = 6;

        //When
        var result = studentRequests.stream().flatMap(studentRequest -> studentRequest.getContacts().stream()).distinct().toList();
        result.forEach(r -> LOGGER.info("{}", r));
        int actual = result.size();

        //Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_return_students_by_department_name() {
        //Given
        int expected = 5;

        //When
        var result = studentRequests.stream().collect(Collectors.groupingBy(StudentRequest::getDept, Collectors.counting()));
        result.entrySet().forEach(r -> LOGGER.info("{}", r));
        LOGGER.info("###################################################");
        result.entrySet().stream().max(Map.Entry.comparingByValue()).ifPresent(r -> LOGGER.info("Max is: {}", r));
        LOGGER.info("###################################################");
        int actual = result.size();

        //Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_return_students_with_average_age() {
        //Given
        double expected = 29.8;

        //When
        var result = studentRequests.stream().map(StudentRequest::getAge).toList();
        result.forEach(r -> LOGGER.info("{}", r.toString()));
        result.stream().reduce(Integer::sum).ifPresent(integer -> {
            double avg = (double) integer / (long) result.size();
            assertThat(avg).isEqualTo(expected);
        });
        double actual = studentRequests.stream().mapToDouble(StudentRequest::getAge).sum()/studentRequests.size();

        //Then
        assertThat(result).isNotNull();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_return_students_by_gender_with_average_age() {
        //When
        var listMap = studentRequests.stream().collect(Collectors.groupingBy(StudentRequest::getGender, Collectors.averagingInt(StudentRequest::getAge)));
        listMap.entrySet().forEach(r -> LOGGER.info("{}", r.toString()));

        //Then
        assertThat(listMap).isNotNull();
    }

    @Test
    void should_return_students_best_ranking_grouping_by_department() {
        //When
        var listMap = studentRequests.stream().collect(Collectors.groupingBy(StudentRequest::getDept, Collectors.minBy(Comparator.comparing(StudentRequest::getRank))));
        listMap.entrySet().forEach(r -> LOGGER.info("{}", r.toString()));

        //Then
        assertThat(listMap).isNotNull();
    }

    @Test
    void should_return_student_with_second_best_ranking() {
        //When
        var listMap = studentRequests.stream().sorted(Comparator.comparing(StudentRequest::getRank)).skip(1).limit(1);
        listMap.forEach(r -> LOGGER.info("{}", r.toString()));

        //Then
        assertThat(listMap).isNotNull();
    }
}
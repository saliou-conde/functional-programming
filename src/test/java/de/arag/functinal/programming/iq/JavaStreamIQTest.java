package de.arag.functinal.programming.iq;

import de.arag.functinal.programming.model.dto.AddressRequest;
import de.arag.functinal.programming.model.dto.AkrosEmployee;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;


class JavaStreamIQTest {

    private static final Logger log = LoggerFactory.getLogger(JavaStreamIQTest.class);

    @Test
    void should_return_list_by_dedicated_stream() {
        //Given
        var addressRequest1 = AddressRequest.builder()
                .street("street1")
                .city("city1")
                .state("state1")
                .zip("zip1")
                .build();

        var addressRequest2 = AddressRequest.builder()
                .street("street2")
                .city("city2")
                .state("state2")
                .zip("zip2")
                .build();

        var addressRequest3 = AddressRequest.builder()
                .street("street3")
                .city("city3")
                .state("state3")
                .zip("zip3")
                .build();

        var addressRequests = List.of(addressRequest1, addressRequest2, addressRequest3);

        //When
        var cities = addressRequests.stream().map(AddressRequest::getCity).collect(Collectors.toSet());
        cities.forEach(log::info);

        //Then
        assertThat(cities).containsExactly("city1", "city2", "city3").hasSize(3);
    }

    @Test
    void should_return_employee_grouped_by_department_with_salary_higher_or_equals_4000() {
        //Given
        var employees = List.of(
                new AkrosEmployee("John", "HR", 4000),
                new AkrosEmployee("Alice", "IT", 4500),
                new AkrosEmployee("Bob", "IT", 4000),
                new AkrosEmployee("Eve", "HR", 4000),
                new AkrosEmployee("Charlie", "Finance", 3000)
        );

        //When
        Map<String, List<AkrosEmployee>> collectedByDepartment = employees.stream()
                .filter(employee -> employee.getSalary() >= 4000)
                .collect(groupingBy(AkrosEmployee::getDepartment));
        log.info(collectedByDepartment.toString());

        //Then
        assertThat(collectedByDepartment).isNotEmpty();
        assertThat(collectedByDepartment.get("IT")).hasSize(2);
        assertThat(collectedByDepartment.get("HR")).hasSize(2);
        assertThat(collectedByDepartment.get("Finance")).isNull(); // Finance department should have no employees with salary >= 4000
    }

    @Test
    void should_return_string_grouped_by_length() {
        //Given
        List<String> stringList = List.of("apple", "bat", "cat", "banana", "dog");

        //When
        var collectedStringByLength = stringList.stream()
                .collect(groupingBy(String::length, counting()));

        //Then
        assertThat(collectedStringByLength).hasSize(3);

    }

    @Test
    void should_return_top_three_highest_salary_of_employee() {
        //Given
        var employees = List.of(
                new AkrosEmployee("John", "HR", 4500),
                new AkrosEmployee("Alice", "IT", 5500),
                new AkrosEmployee("Bob", "IT", 5000),
                new AkrosEmployee("Eve", "HR", 4000),
                new AkrosEmployee("Charlie", "Finance", 3000),
                new AkrosEmployee("Frank", "Finance", 3500)
        );

        //When
        var topThreeHighestSalary = employees.stream()
                .sorted(comparingInt(AkrosEmployee::getSalary).reversed())
                .map(AkrosEmployee::getSalary)
                .limit(3)
                .toList();

        //Then
        assertThat(topThreeHighestSalary)
                .hasSize(3)
                .containsExactly(5500, 5000, 4500);
    }

    @Test
    void should_return_peeked_elements() {
        //Given
        String[] expectedResult = {"THREE", "FOUR"};

        //When
        var result = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> log.info("Filtered value: {}", e))
                .map(String::toUpperCase)
                .peek(e -> log.info("Mapped value: {}", e))
                .toList();

        //Then
        assertThat(result)
                .isNotNull()
                .hasSize(2)
                .containsExactly(expectedResult);
    }

}

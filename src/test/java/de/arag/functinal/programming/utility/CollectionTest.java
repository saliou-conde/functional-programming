package de.arag.functinal.programming.utility;

import de.arag.functinal.programming.model.dto.EmployeeRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CollectionTest {

    private List<EmployeeRequest> employeeRequests;

    @BeforeAll
    void setUp() {
        EmployeeRequest employeeRequest1 = EmployeeRequest
                .builder()
                .avatar("/v1/avatar1")
                .email("saliou-conde@gmx.de")
                .firstName("Saliou")
                .lastName("Condé")
                .isActive(Boolean.TRUE)
                .jobTitle("Container")
                .build();

        EmployeeRequest employeeRequest2 = EmployeeRequest
                .builder()
                .avatar("/v1/avatar1")
                .email("babak-ghavidel@gmx.de")
                .firstName("Babak")
                .lastName("Ghavidel")
                .isActive(Boolean.TRUE)
                .jobTitle("DEV/OPS")
                .build();

        EmployeeRequest employeeRequest3 = EmployeeRequest
                .builder()
                .avatar("/v1/avatar1")
                .email("marcus-moll@gmx.de")
                .firstName("Marcus")
                .lastName("Moll")
                .isActive(Boolean.TRUE)
                .jobTitle("Development")
                .build();

        EmployeeRequest employeeRequest4 = EmployeeRequest
                .builder()
                .avatar("/v1/avatar1")
                .email("abrahma-schmidt@gmx.de")
                .firstName("Abraham")
                .lastName("Schmidt")
                .isActive(Boolean.FALSE)
                .jobTitle("DEV/OPS")
                .build();

        employeeRequests = List.of(employeeRequest1, employeeRequest2, employeeRequest3, employeeRequest4);
    }

    @Test
    void employeeTest() {
        //When
        Map<String, List<EmployeeRequest>> collectByJobTitle = employeeRequests
                .stream()
                .collect(Collectors.groupingBy(employeeRequest -> employeeRequest.getJobTitle()));

        BiConsumer<String, List<EmployeeRequest>> consumer = new MyBiConsumer<>();
        collectByJobTitle.forEach(consumer);
    }

    @Test
    void collectionTest() {
        //Given
        int [] array = {1,0,0,0,1,1,0,1,0,1,1,0};
        List<Integer> arrayToList = Arrays.stream(array).boxed().toList();

        //When
        Map<Boolean, List<Integer>> collect = arrayToList.stream().collect(Collectors.partitioningBy(e -> e == 0));
        BiConsumer<Boolean, List<Integer>> action = new MyBiConsumer();
        collect.forEach(action);
        List<Integer> list = collect.get(true);

        //Then
        assertThat(collect).isNotNull();

        return;
    }

    @Test
    void setTest() {
        //Given
        Set<String> strings = new HashSet<>();

        //When
        strings.add("Hallo");
        strings.add("Hallo");
        strings.add("Saliou");
        strings.add("this just a test.");
        strings.add("this just a test.");

        //Then
        assertThat(strings.size()).isEqualTo(3);
    }

    @Test
    void hashMapTest() {
        //Given
        HashMap<String, String> map = new HashMap<>();

        //When
        map.put("str1", "value1");
        map.put("str1", "value1");
        map.put("str2", "value2");
        map.put("str2", "value1");
        map.put("str2", "value1");
        map.put("str3", "value3");
        map.put(null, null);
        map.put(null, "hello");
        int h;
        int index1 = (15 & (h = "str1".hashCode()) ^ (h >>> 16))%15;
        int index2 = (15 & (h = "str2".hashCode()) ^ (h >>> 16))%15;
        int index3 = (15 & (h = "str3".hashCode()) ^ (h >>> 16))%15;
        int index4 = (15 & (h = 0) ^ (h >>> 16))%15;
        int DEFAULT_INITIAL_CAPACITY = 1 << 4;


        BiConsumer<String, String> action = new MyBiConsumer();
        map.forEach(action);

        //Then
        assertThat(map.size()).isEqualTo(4);
    }

    @Test
    void flapMapTest() {
        List<String> list1 = List.of("01234567", "098765");
        List<String> list2 = List.of("89776767", "121212");
        List<String> list3 = List.of("012345671212", "098765212");
        List<List<String>> lists = List.of(list1, list2, list3);
        lists.forEach(strings -> System.out.println(strings));
        System.out.println();
        List<String> stringList = lists.stream().flatMap(List::stream).collect(Collectors.toList());
        stringList.forEach(s -> System.out.print(s+" "));
    }

    @Test
    void hashCodeObjectTest() {
        //Given
        EmployeeRequest employeeRequest1 = EmployeeRequest
                .builder()
                .avatar("/v1/avatar1")
                .email("saliou-conde@gmx.de")
                .firstName("Saliou")
                .lastName("Condé")
                .isActive(Boolean.TRUE)
                .jobTitle("Container")
                .build();

        EmployeeRequest employeeRequest2 = EmployeeRequest
                .builder()
                .avatar("/v1/avatar1")
                .email("saliou-conde@gmx.de")
                .firstName("Saliou")
                .lastName("Condé")
                .isActive(Boolean.TRUE)
                .jobTitle("Container")
                .build();

        //When
        int hashCode1 = employeeRequest1.hashCode();
        int hashCode2 = employeeRequest2.hashCode();
        boolean equal = employeeRequest1.equals(employeeRequest2);
        boolean b1 = employeeRequest1 == employeeRequest1;
        boolean b2 = employeeRequest1 == employeeRequest2;
        Object o;
        CopyOnWriteArrayList<String> copy;

        //Then
        assertThat(hashCode1).isEqualTo(hashCode2);
        assertThat(equal).isTrue();
        assertThat(b1).isTrue();
        assertThat(b2).isFalse();
    }
}

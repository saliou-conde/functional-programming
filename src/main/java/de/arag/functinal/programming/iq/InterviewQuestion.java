package de.arag.functinal.programming.iq;

import de.arag.functinal.programming.model.dto.EmployeeRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Slf4j
public class InterviewQuestion {

    private InterviewQuestion() {
    }

    private static InterviewQuestion interviewQuestion;

    public static InterviewQuestion getInterviewQuestion() {
        return interviewQuestion == null ? new InterviewQuestion() : interviewQuestion;
    }

    public Map<String, Long> countElements(List<String> list) {
        var map = getElementOccurrence(list);
        log.info(map.toString());
        return map;
    }

    public List<String> distinctList(List<String> list) {
        return list.stream().distinct().toList();
    }

    public Map<String, Long> duplicateElements(List<String> list) {
        var map = getElementOccurrence(list);
        var result = new HashMap<String, Long>();
        map.keySet().stream().toList().forEach(key -> {
            if (map.get(key) > 1) {
                result.put(key, map.get(key));
            }
        });
        log.info(result.toString());
        return result;
    }

    public List<String> flatMapList(List<List<String>> list) {
        List<String> stringList = list.stream().flatMap(List::stream).toList();
        stringList.forEach(log::info);
        return stringList;
    }

    public Map.Entry<Integer, List<String>> findNthHighestSalary(Map<String, Integer> map, int num) {
        Map<Integer, List<String>> salaryGroupedByLohn = map.entrySet().stream().collect(groupingBy(Map.Entry::getValue,
                Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
        var result = salaryGroupedByLohn.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).toList();
        log.info(result.toString());
        return result.get(num - 1);
    }

    public String firstOccurrenceElement(List<String> list) {
        var map = uniqueElements(list);
        String key = map.entrySet().stream().filter(x -> x.getValue() == 1).findFirst().orElseThrow(() -> new RuntimeException("No value present")).getKey();
        log.info(key);
        return key;
    }

    public String highestElement(List<String> input) {
        return input.stream().reduce((x, y) -> x.length() > y.length() ? x : y).orElseThrow();
    }

    /**
     * public Map<Object, List<EmployeeRequest>> groupingByReference(List<EmployeeRequest> list, Function<EmployeeRequest, Object> functionReference) {
        return list.stream().collect(groupingBy(functionReference));
    }*/

    public Map<?, List<EmployeeRequest>> groupingByField(List<EmployeeRequest> list, Function<EmployeeRequest, ?> functionReference) {
        return list.stream().collect(groupingBy(functionReference));
    }

    public IntSummaryStatistics intSummaryStatistics(List<Integer> list) {
        IntSummaryStatistics intSummaryStatistics = list.stream().collect(summarizingInt(Integer::intValue));
        log.info(intSummaryStatistics.toString());
        return intSummaryStatistics;
    }

    public String joining(List<String> list) {
        return list.stream().collect(Collectors.joining(", ", "[", "]"));
    }

    public List<String> mapList(List<String> list) {
        List<String> stringList = list.stream()
                .filter(Objects::nonNull)
                .map(String::toUpperCase).toList();
        stringList.forEach(log::info);
        return stringList;
    }

    public Integer multipleElements(List<Integer> input) {
        return input.stream().reduce((x, y) -> x * y).orElse(0);
    }

    public Map<Boolean, List<Integer>> partitioningByEvenAndOdd(List<Integer> list) {
        Map<Boolean, List<Integer>> collect = list.stream().collect(partitioningBy(n -> n % 2 == 0));
        log.info(collect.toString());
        return collect;
    }

    public Integer secondHighestElement(List<Integer> input) {
        Set<Integer> collected = input.stream().sorted(Comparator.reverseOrder()).collect(toCollection(LinkedHashSet::new));
        log.info(collected.toString());
        return collected.stream().skip(1).findFirst().orElseThrow();
    }

    public List<String> sortList(List<String> list) {
        List<String> stringList = list.stream().sorted(Comparator.naturalOrder()).toList();
        log.info(stringList.toString());
        return stringList;
    }

    public Map<String, Long> uniqueElements(List<String> list) {
        var map = getElementOccurrence(list);

        // Use entrySet stream to filter and collect elements with occurrence 1
        var result = map.entrySet().stream()
                .filter( x -> x.getValue() == 1)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
        log.info(result.toString());

        return result;
    }

    private static Map<String, Long> getElementOccurrence(List<String> list) {
        return list.stream().collect(groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
    }
}

package de.arag.functinal.programming.utility;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Slf4j
public class StringCounter {

    private static StringCounter instance = null;

    private StringCounter() {
    }

    public static StringCounter getGetInstance() {
        if(instance == null) instance = new StringCounter();
        return instance;
    }

    public Map<String, Long> countString(String input, String pattern) {

        log.info("Input: {}", input);
        Map<String, Long> map = Arrays
                .stream(input.trim().split(pattern))
                .collect(groupingBy(Function.identity(), counting()));
        log.info("MAP is {}", map);


        AtomicLong finalMax = new AtomicLong();
        AtomicReference<String> finalWord = new AtomicReference<>();
        map.keySet().stream().forEach(
                key -> {
                    long value = map.get(key).longValue();
                    if(finalMax.get() < value) {
                        finalMax.set(value);
                        finalWord.set(key);
                    }
                }
        );

        Set<String> strings = new HashSet<>();
        Queue<String> queue1 = new PriorityQueue<>();
        Queue<String> queue2 = new LinkedList<>();


        log.info("######################################################");
        log.info("Max= {} Word= {}", finalMax.get(), finalWord.get());
        log.info("######################################################");
        return map;
    }
}

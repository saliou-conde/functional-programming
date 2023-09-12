package de.arag.functinal.programming.utility;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BiConsumer;

@Slf4j
public class MyBiConsumer<T, U> implements BiConsumer<T, U> {

    @Override
    public void accept(T t, U u) {
        log.info("Key = {}", t);
        log.info("Value = {}", u);
    }
}

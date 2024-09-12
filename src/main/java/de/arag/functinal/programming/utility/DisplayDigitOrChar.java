package de.arag.functinal.programming.utility;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

@Slf4j
public class DisplayDigitOrChar {

    public static void main(String[] args) throws InterruptedException {

        List<String> list = List.of("1","A","B","C","3","E","4","5");

        Runnable digit = () -> {
            List<String> digits = list
                    .stream()
                    .filter(e -> Pattern.matches("[a-zA-Z]+", e))
                    .toList();
            digits.forEach(el -> {
                log.info(el);
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    log.warn(e.getMessage());
                    Thread.currentThread().interrupt();
                }
            });
        };

        Runnable character = () -> {
            List<String> letters = list
                    .stream()
                    .filter(e -> !Pattern.matches("[a-zA-Z]+", e))
                    .toList();
            letters.forEach(el -> {
                log.info(el);
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    log.warn(e.getMessage());
                    Thread.currentThread().interrupt();
                }

            });
        };

        Thread thread1 = new Thread(digit);
        sleep(10);
        Thread thread2 = new Thread(character);

        thread1.start();
        thread2.start();

    }
}

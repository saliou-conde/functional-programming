package de.arag.functinal.programming.utility;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

@Slf4j
public class EvenOrOddCompletableFuture {

    private static Object object = new Object();

    private static IntPredicate even = number -> number%2 == 0;
    private static IntPredicate odd = number -> number%2 != 0;

    public static void run() {
        CompletableFuture.runAsync(() -> EvenOrOddCompletableFuture.printEvenOrOdd(even));
        CompletableFuture.runAsync(() -> EvenOrOddCompletableFuture.printEvenOrOdd(odd));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }

    public static void printEvenOrOdd(IntPredicate condition) {
        IntStream.rangeClosed(1, 10).filter(condition).forEach(EvenOrOddCompletableFuture::execute);
    }

    public static void execute(int number) {
        synchronized (object) {
            log.info(Thread.currentThread().getName()+" : "+number);
            object.notify();
            try {
                object.wait();
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }
}

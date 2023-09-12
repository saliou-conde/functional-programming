package de.arag.functinal.programming.utility;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class DisplayDigitOrChar {

    public static void main(String[] args) throws InterruptedException {

        List<String> list = List.of("1","A","B","C","3","E","4","5");

        Runnable digit = () -> {
            List<String> digits = list
                    .stream()
                    .filter(e -> Pattern.matches("[a-zA-Z]+", e) == true)
                    .collect(Collectors.toList());
            //digits.forEach(el -> System.out.println(el));

            for (String str:digits
                 ) {
                System.out.println(str);
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable character = () -> {
            List<String> letters = list
                    .stream()
                    .filter(e -> Pattern.matches("[a-zA-Z]+", e) == false)
                    .collect(Collectors.toList());
            //letters.forEach(el -> System.out.println(el));

            for (String str:letters
            ) {
                System.out.println(str);
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread1 = new Thread(digit);
        sleep(10);
        Thread thread2 = new Thread(character);

        thread1.start();
        thread2.start();

    }
}

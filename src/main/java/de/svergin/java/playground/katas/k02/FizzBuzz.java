package de.svergin.java.playground.katas.k02;

import java.util.stream.IntStream;

/**
 * Kata nach https://ccd-school.de/coding-dojo/function-katas/fizzbuzz/
 */
public class FizzBuzz {
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";

    public static void main(String[] args) {
        IntStream.rangeClosed(1,100).mapToObj(zahl -> {
            if (isTeilbarOhneRest(zahl, 3)) {
                return (FIZZ + (isTeilbarOhneRest(zahl, 5) ? BUZZ : ""));
            } else if (isTeilbarOhneRest(zahl, 5)) {
                return BUZZ;
            } else {
                return zahl;
            }
        }).forEach(s -> System.out.println(s));

        for (int i = 1; i < 101; i++) {

            StringBuilder sb = new StringBuilder();
            if (isTeilbarOhneRest(i, 3)) {
                sb.append(FIZZ + (isTeilbarOhneRest(i, 5) ? BUZZ : ""));
            } else if (isTeilbarOhneRest(i, 5)) {
                sb.append(BUZZ);
            } else {
                sb.append(i);
            }

            System.out.println(sb);
        }
    }

    private static boolean isTeilbarOhneRest(int zahl, int i) {
        return zahl % i == 0;
    }

}

package de.svergin.java.playground.katas.k04;

import java.util.stream.IntStream;

public class Tannenbaum {

    public static void main(String[] args) {
        zeichneFunktional(5, true);
    }

    private static void zeichne(int anzahlEbenen, boolean mitStern) {
        String leading = " ".repeat(anzahlEbenen - 1);
        if (mitStern) {
            System.out.println(leading + "*");
        }
        for (int i = 1; i <= anzahlEbenen; i++) {
            System.out.println(" ".repeat(anzahlEbenen - i) + "X".repeat((i + i) - 1));
        }
        System.out.println(leading + "|");
    }

    private static void zeichneFunktional(int anzahlEbenen, boolean mitStern) {
        String leading = " ".repeat(anzahlEbenen - 1);
        if (mitStern) {
            System.out.println(leading + "*");
        }
        IntStream.rangeClosed(1, anzahlEbenen)
                .forEach(value -> System.out.println(" ".repeat(anzahlEbenen - value) + "X".repeat((value + value) - 1)));
        System.out.println(leading + "|");
    }
}

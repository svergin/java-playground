package de.svergin.java.playground.katas.k05;

import java.util.*;

public class CharCounter {

    public static void main(String[] args) {
        Map<String, Integer> result = countAndGroup("Das ist mein Text in dem die Zeichen gezählt werden.");
        result.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
    }

    private static Map<String, Integer> countAndGroup(String text) {
        HashMap<String, Integer> result = new HashMap<>();
        Arrays.stream(text.split("")).forEach(zeichen -> result.compute(zeichen.toLowerCase(Locale.ROOT), (k, v) -> (v == null) ? 1 : v + 1));
        return result;
    }
}

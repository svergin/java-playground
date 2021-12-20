package de.svergin.java.playground.katas.k03;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class FromRomanNumerals {
    private static Map<String, Integer> romanToDec;

    static {
        romanToDec = new HashMap<>();
        romanToDec.put("I", 1);
        romanToDec.put("V", 5);
        romanToDec.put("X", 10);
        romanToDec.put("L", 50);
        romanToDec.put("C", 100);
        romanToDec.put("D", 500);
        romanToDec.put("M", 1000);
    }

    public static void main(String[] args) {

        int decNumeral = new RomanConverter().calc("X");
    }

    static class RomanConverter {

        public int calc(String romanNumeral) {
            Integer result = Arrays.stream(romanNumeral.split(""))
                    .map(value -> romanToDec.get(value))
                    .reduce(0, Integer::sum);
            return result;
        }
    }
}

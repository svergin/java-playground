package de.svergin.java.playground.katas.k01;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamTabellierer implements Tabellierbar {

    public static final String BINDESTRICH = "-";

    @Override
    public List<String> formatiere(List<String> csvZeilen) {
        List<String[]> zeilen = csvZeilen.stream().map(s -> s.split("\\;+")).collect(Collectors.toList());
        int anzahlSpalten = zeilen.get(0).length;
        int[] maxBreiteProSpalte = new int[anzahlSpalten];
        String[] trennzeile = new String[anzahlSpalten];
        for (int i = 0; i < maxBreiteProSpalte.length; i++) {
            int counter = i;
            maxBreiteProSpalte[i] = zeilen.stream()
                    .map(strings -> strings[counter].length())
                    .max(Comparator.naturalOrder())
                    .orElse(Integer.valueOf(0));
            trennzeile[i] = BINDESTRICH;
        }
        zeilen.add(1, trennzeile);
        ArrayList<String> result = new ArrayList<>();
        for (String[] eineZeile : zeilen) {
            AtomicInteger index = new AtomicInteger();
            String formartierteZeile = Arrays.stream(eineZeile)
                    .map(s -> new AbstractMap.SimpleEntry<>(maxBreiteProSpalte[index.getAndIncrement()], s))
                    .collect(ZeilenCollector.erstelleZeile());
            result.add(formartierteZeile);
        }


        return result;
    }

    private static class ZeilenCollector implements Collector<AbstractMap.SimpleEntry<Integer, String>, StringBuilder, String> {

        public static ZeilenCollector erstelleZeile() {
            return new ZeilenCollector();
        }

        @Override
        public Supplier<StringBuilder> supplier() {
            return StringBuilder::new;
        }

        @Override
        public BiConsumer<StringBuilder, AbstractMap.SimpleEntry<Integer, String>> accumulator() {

            return ((stringBuilder, s) -> {
                stringBuilder.append(s.getValue());
                if (s.getValue().length() < s.getKey()) {
                    for (int i = 0; i < (s.getKey() - s.getValue().length()); i++) {
                        stringBuilder.append(holeZeichen(s.getValue(), true));
                    }
                }
                stringBuilder.append(holeZeichen(s.getValue(), false));
            });
        }

        private CharSequence holeZeichen(String s, boolean isAuffueller) {
            if (isAuffueller) {
                return s.contains(BINDESTRICH) ? BINDESTRICH : " ";
            } else {
                return s.contains(BINDESTRICH) ? "+" : "|";
            }

        }

        @Override
        public BinaryOperator<StringBuilder> combiner() {
            return (sb1, sb2) -> {
                sb1.append(sb2);
                return sb1;
            };
        }

        @Override
        public Function<StringBuilder, String> finisher() {
            return StringBuilder::toString;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.emptySet();
        }
    }
}

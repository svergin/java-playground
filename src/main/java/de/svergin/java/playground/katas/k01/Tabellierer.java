package de.svergin.java.playground.katas.k01;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tabellierer implements Tabellierbar {
    private static String pad(String ueberschrift, int maxBreite, char fuellzeichen) {
        StringBuilder sb = new StringBuilder(ueberschrift);
        if (maxBreite > ueberschrift.length()) {
            for (int i = 0; i < (maxBreite - ueberschrift.length()); i++) {
                sb.append(fuellzeichen);
            }
        }
        return sb.toString();
    }

    @Override
    public List<String> formatiere(List<String> csvZeilen) {
        List<String[]> zeilenUndSpalten = csvZeilen.stream().map(s -> s.split("\\;+")).collect(Collectors.toList());
        String[] ueberschriften = zeilenUndSpalten.get(0);
        List<Spalte> spalten = new ArrayList<>();
        for (int i = 0; i < ueberschriften.length; i++) {
            Spalte spalte = new Spalte();
            spalte.ueberschrift = ueberschriften[i];
            spalte.aktualisiereMaxBreite(ueberschriften[i].length());
            for (int j = 1; j < zeilenUndSpalten.size(); j++) {
                spalte.addZelle(zeilenUndSpalten.get(j)[i]);
            }
            spalten.add(spalte);
        }
        List<String> result = new ArrayList<>(csvZeilen.size() + 1);
        int zeilenCounter = 0;
        int zellenCounter = 0;
        for (int i = 0; i < csvZeilen.size() + 1; i++) {
            StringBuilder zeile = new StringBuilder();

            if (zeilenCounter == 0) {

                StringBuilder trennzeile = new StringBuilder();
                for (Spalte spalte : spalten) {
                    //Überschriften und Trennzeile
                    zeile.append(pad(spalte.ueberschrift, spalte.maxBreite, ' ')).append('|');
                    trennzeile.append(pad("", spalte.maxBreite, '-')).append('+');
                }
                zeilenCounter++;
                result.add(zeile.toString());
                result.add(trennzeile.toString());
                continue;
            }
            for (Spalte spalte : spalten) {
                if (zellenCounter < spalte.zellen.size()) {
                    zeile.append(pad(spalte.zellen.get(zellenCounter), spalte.maxBreite, ' ')).append('|');
                }
            }
            zellenCounter++;
            zeilenCounter++;
            result.add(zeile.toString());
        }
        return result;
    }

    private static class Spalte {
        int maxBreite = 0;
        String ueberschrift;
        List<String> zellen = new ArrayList<>();

        private void aktualisiereMaxBreite(int length) {
            if (maxBreite < length) {
                maxBreite = length;
            }
        }

        public void addZelle(String zelle) {
            aktualisiereMaxBreite(zelle.length());
            zellen.add(zelle);
        }

        @Override
        public String toString() {
            return ueberschrift + "\n" + zellen.stream().collect(Collectors.joining("\n"));
        }
    }
}

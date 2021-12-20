package de.svergin.java.playground.katas.k01;

import java.util.List;

/**
 * Kata nach https://ccd-school.de/coding-dojo/function-katas/csv-tabellieren/
 */
public class CsvTabellieren {

    public static void main(String[] args) throws Exception {
        List<String> csvZeilen =
//                Files.readAllLines(Path.of(new File("D:\\devenv\\repos\\java-playground\\src\\main\\resources\\personen.csv").toURI()));
                List.of("Name;Strasse;Ort;Alter",
                        "Peter Pan;Am Hang 5;12345 Einsam;42",
                        "Maria Schmitz;Kölner Straße 45;50123 Köln;43",
                        "Paul Meier;Münchener Weg 1;87654 München;65");
        long start = System.currentTimeMillis();
        List<String> csvFormatiert = new Tabellierer().formatiere(csvZeilen);
        long ende = System.currentTimeMillis();
        System.out.println("Konvertierung (klassisch) dauerte: " + ((ende - start)) + " ms");

        csvFormatiert.stream().forEach(s -> System.out.println(s));

        start = System.currentTimeMillis();

         csvFormatiert = new StreamTabellierer().formatiere(csvZeilen);
         ende = System.currentTimeMillis();
        System.out.println("Konvertierung (Streams) dauerte: " + ((ende - start)) + " ms");

        csvFormatiert.stream().forEach(s -> System.out.println(s));

    }

}
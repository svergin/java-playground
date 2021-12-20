package de.svergin.java.playground.samples;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.List.of;

public class StreamApiSample {

    List<Person> people = of(new Person("Meier", "Gustav", LocalDate.of(1980, 3, 12), "Karl", "Gustav"),
            new Person("Meier", "Justus", LocalDate.of(2001, 12, 12), "Otto"),
            new Person("Schmidt", "Jonas", LocalDate.of(2005, 1, 31), "Emil", "Hein"),
            new Person("Meyer", "Jens", LocalDate.of(1999, 8, 8), "Jonas"),
            new Person("Mueller", "Mario", LocalDate.of(1995, 4, 24), "Martin", "Max")
    );

    public void streamForEachSimple() {
        printLine("streamForEachSimple()");
        //Mutator
//        people.stream().forEach(person -> person.setNachname("Nachname"));
        // Output
        people.stream().forEach(person -> System.out.println(person));
    }

    public void streamMatchers() {
        printLine("streamMatchers()");
        boolean heissenAlleMeier = people.stream().allMatch(person -> person.nachname.equals("Meier"));
        System.out.println("heissenAlleMeier? " + heissenAlleMeier);
        boolean heisstJemandMeier = people.stream().anyMatch(person -> person.nachname.equals("Meier"));
        System.out.println("heisstJemandMeier? " + heisstJemandMeier);
        boolean heisstKeinerSchuster = people.stream().noneMatch(person -> person.nachname.equals("Schuster"));
        System.out.println("heisstKeinerSchuster? " + heisstKeinerSchuster);
    }

    public void streamMap() {
        printLine("streamMap()");
        people.stream()
                .map(Person::getVorname)
                .mapToInt(String::length)
                .forEach(value -> System.out.println("Vornamen-Laenge: " + value));
        int gesamtLaenge = people.stream().map(Person::getVorname).mapToInt(String::length).sum();
        System.out.println("gesamtLaenge: " + gesamtLaenge);
        System.out.println("Umwandlung in Haustiere:");
        people.stream()
                .map(person -> new Haustier(person.getVorname()))
                .forEach(haustier -> System.out.println(haustier));
    }

    public void streamFlatMap() {
        printLine("streamFlatMap()");
        people.stream()
                .flatMap(person -> person.getWeitereNamen().stream())
                .collect(Collectors.toList()).stream().forEach(s -> System.out.println(s));
    }

    private void printLine(String methodName) {
        System.out.println("#############################################");
        System.out.println("Methode: " + methodName);
        System.out.println("#############################################");

    }


    private class Person {
        private String nachname;
        private String vorname;
        private LocalDate geburtsdatum;
        private List<String> weitereNamen;

        public Person(String nachname, String vorname, LocalDate geburtsdatum, String... weitereNamen) {

            this.nachname = nachname;
            this.vorname = vorname;
            this.geburtsdatum = geburtsdatum;
            this.weitereNamen = List.of(weitereNamen);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "nachname='" + nachname + '\'' +
                    ", vorname='" + vorname + '\'' +
                    ", geburtsdatum=" + geburtsdatum +
                    '}';
        }

        public void setNachname(String nachname) {
            this.nachname = nachname;
        }

        public String getVorname() {
            return vorname;
        }

        public List<String> getWeitereNamen() {
            return weitereNamen;
        }
    }

    private class Haustier {
        private String vorname;

        public Haustier(String vorname) {
            this.vorname = vorname;
        }

        public String getVorname() {
            return vorname;
        }

        @Override
        public String toString() {
            return "Haustier{" +
                    "vorname='" + vorname + '\'' +
                    '}';
        }
    }
}

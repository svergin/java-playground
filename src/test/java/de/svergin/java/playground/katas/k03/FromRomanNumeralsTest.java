package de.svergin.java.playground.katas.k03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FromRomanNumeralsTest {
    private FromRomanNumerals.RomanConverter underTest = new FromRomanNumerals.RomanConverter();

    @Test
    public void testRomanToDecimals_SimpleSingleLetters_ReturnCorrectDecimal() {
        int result = underTest.calc("X");
        assertEquals(10, result);

        result = underTest.calc("V");
        assertEquals(5, result);
    }

    @Test
    public void testRomanToDecimals_MultiLettersAdditionOnly_ReturnCorrectDecimal() {
        int result = underTest.calc("XX");
        assertEquals(20, result);

        result = underTest.calc("XXIII");
        assertEquals(23, result);
    }

    @Test
    public void testRomanToDecimals_MultiLettersSubstractionOnly_ReturnCorrectDecimal() {
        int result = underTest.calc("IV");
        assertEquals(4, result);

//        result = underTest.calc("XXIII");
//        assertEquals(23,result);
    }

}
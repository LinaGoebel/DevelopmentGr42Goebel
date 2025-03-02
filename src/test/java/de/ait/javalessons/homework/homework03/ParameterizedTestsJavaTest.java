package de.ait.javalessons.homework.homework03;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParameterizedTestsJavaTest {

    @ParameterizedTest
    @CsvSource({
            "5, 6, 11",
            "5, 7, 12",
            "10,22,32",
            "-56, 17, -39"
    })
    public void testAdd(int a, int b, int expected) {
        int result = ParameterizedTestsJava.add(a, b);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 16, 24, 40, 94})
    public void testIsEven(int number) {
        boolean result = ParameterizedTestsJava.isEven(number);
        assertEquals(true, result);
    }

    @ParameterizedTest
    @CsvSource({
            "12,6,2",
            "10,5,2",
            "8,4,2",
            "7,0,0"
    })
    public void testDivide(int a, int b, int expected) {
        if (b == 0) {
            assertThrows(IllegalArgumentException.class, () -> ParameterizedTestsJava.divide(a, b));
        } else {
            int result = ParameterizedTestsJava.divide(a, b);
            assertEquals(expected, result);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "Hello, 5",
            "Computer, 8",
            "Energy, 6",
            "'', 0",
            "null, 0"
    })
    public void testGetLength(String str, int expected) {
        if ("null".equals(str)) {
            str = null;
        }
        int result = ParameterizedTestsJava.getLength(str);
        assertEquals(expected, result);

    }

    @ParameterizedTest
    @CsvSource({
            "Hello World, World, true",
            "Understand Java, World, false",
            "Goodnight, night, true"
    })
    public void testContainsWord(String text, String word, boolean expected) {
        boolean result = ParameterizedTestsJava.containsWord(text, word);
        assertEquals(expected, result);
    }
}

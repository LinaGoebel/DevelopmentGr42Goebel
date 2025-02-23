package de.ait.javalessons.homework.homework01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamApiTest {
    private StreamApi streamApi;


    @BeforeEach
    void setUp() {
        streamApi = new StreamApi();
    }

    @AfterEach
    void tearDown() {
        streamApi = null;
    }

    @Test
    void testCountriesWithC() {
        List<String> countriesWithC = streamApi.getCountriesWithC();
        assertTrue(countriesWithC.contains("Canada"));
    }

    @Test
    void testCitiesMore6Symbols() {
        List<String> citiesMore6Symbols = streamApi.getCitiesMore6Symbols(streamApi.getCities());
        assertTrue(citiesMore6Symbols.contains("Buenos Aires"));
    }

    @Test
    void testRiversWithEvenAmountLetters() {
        List<String> riversWithEvenAmountLetters = streamApi.getRiversWithEvenAmountLetters(streamApi.getRivers());
        assertTrue(riversWithEvenAmountLetters.contains("Nile"));
    }

    @Test
    void testContinentsLess7Symbols() {
        List<String> continentsLess7Symbols = streamApi.getContinentsLess7Symbols(streamApi.getContinents());
        assertTrue(continentsLess7Symbols.contains("Asia"));
    }

    @Test
    void testCountriesWith6Symbols() {
        List<String> countriesWith6Symbols = streamApi.getCountriesWith6Symbols(streamApi.getCountries1());
        assertTrue(countriesWith6Symbols.contains("France"));
    }

    @Test
    void testCountriesWithA() {
        List<String> countriesWithA = streamApi.getCountriesWithA(streamApi.getCountries(), streamApi.getCountries1());
        assertTrue(countriesWithA.contains("Canada"));
    }

    @Test
    void testCitiesWithO() {
        List<String> citiesWithO = streamApi.getCitiesWithO(streamApi.getCities());
        assertEquals(0, citiesWithO.size());
    }

    @Test
    void testRiversWithMore7Symbols() {
        List<String> riversWithMore7Symbols = streamApi.getRiversWithMore7Symbols(streamApi.getRivers());
        assertTrue(riversWithMore7Symbols.contains("Mississippi"));
    }

    @Test
    void testContinentsWithA() {
        List<String> continentsWithA = streamApi.getContinentsWithA(streamApi.getContinents());
        assertTrue(continentsWithA.contains("Asia"));
    }
}

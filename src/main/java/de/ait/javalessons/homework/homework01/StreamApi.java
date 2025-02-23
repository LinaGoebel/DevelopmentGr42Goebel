package de.ait.javalessons.homework.homework01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApi {
    private static List<String> countries = Arrays.asList("Germany", "France", "Brazil", "Argentina", "Canada",
            "China", "Australia", "India");
    private static List<String> cities = Arrays.asList("Berlin", "Buenos Aires", "Paris", "Los Angeles", "New York",
            "London", "Beijing");
    private static List<String> rivers = Arrays.asList("Amazon", "Nile", "Yangtze", "Mississippi", "Danube", "Main", "Ganges");
    private static List<String> continents = Arrays.asList("Europe", "Asia", "Africa", "Australia", "Antarctica",
            "South America", "North America");
    private static List<String> countries1 = Arrays.asList("Mexico", "Sweden", "Brazil", "USA", "Canada", "France", "Norway");

    public static List<String> getCountries() {
        return countries;
    }

    public static List<String> getCities() {
        return cities;
    }

    public static List<String> getRivers() {
        return rivers;
    }

    public static List<String> getContinents() {
        return continents;
    }

    public static List<String> getCountries1() {
        return countries1;
    }

    public static void main(String[] args) {
        System.out.println(getCountriesWithC());
        System.out.println(getCitiesMore6Symbols(cities));
        System.out.println(getRiversWithEvenAmountLetters(rivers));
        System.out.println(getContinentsLess7Symbols(continents));
        System.out.println(getCountriesWith6Symbols(countries1));
        System.out.println(getCountriesWithA(countries, countries1));
        System.out.println(getCitiesWithO(cities));
        System.out.println(getRiversWithMore7Symbols(rivers));
        System.out.println(getContinentsWithA(continents));
    }

    public static List<String> getCountriesWithC() {
        return countries.stream()
                .filter(countries -> countries.startsWith("C"))
                .collect(Collectors.toList());

    }

    public static List<String> getCitiesMore6Symbols(List<String> citiesList) {
        return citiesList.stream()
                .filter(cities -> cities.length() > 6)
                .collect(Collectors.toList());
    }

    public static List<String> getRiversWithEvenAmountLetters(List<String> riversList) {
        return riversList.stream()
                .filter(rivers -> rivers.length() % 2 == 0)
                .collect(Collectors.toList());
    }

    public static List<String> getContinentsLess7Symbols(List<String> continentsList) {
        return continentsList.stream()
                .filter(continents -> continents.length() < 7)
                .collect(Collectors.toList());
    }

    public static List<String> getCountriesWith6Symbols(List<String> countriesList) {
        return countriesList.stream()
                .filter(countries -> countries.length() == 6)
                .collect(Collectors.toList());
    }

    public static List<String> getCountriesWithA(List<String> countriesList, List<String> countries1) {
        return countriesList.stream()
                .filter(countries -> countries.contains("a"))
                .collect(Collectors.toList());
    }

    public static List<String> getCitiesWithO(List<String> citiesList) {
        return citiesList.stream()
                .filter(cities -> cities.endsWith("o"))
                .collect(Collectors.toList());
    }

    public static List<String> getRiversWithMore7Symbols(List<String> riversList) {
        return riversList.stream()
                .filter(rivers -> rivers.length() > 7)
                .collect(Collectors.toList());
    }

    public static List<String> getContinentsWithA(List<String> continentsList) {
        return continentsList.stream()
                .filter(continents -> continents.startsWith("A"))
                .collect(Collectors.toList());
    }

}




package de.ait.javalessons.homework.homework03;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParameterizedTestsJava {
    public static void main(String[] args) {
        System.out.println(add(1, 2));

        System.out.println(isEven(5));

       try {
           System.out.println(divide(10, 0));
       } catch (IllegalArgumentException e) {
           System.out.println(e.getMessage());
       }

        System.out.println(getLength("Hello"));

        System.out.println(containsWord("Hello World", "world"));
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static int divide(int a, int b) {
        if (b == 0) {
            log.error("Division b y zero. a: {}, b: {}", a, b);
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return a / b;
    }

    public static int getLength(String str) {
        if(str == null) {
            return 0;
        }
        return str.length();
    }

    public static boolean containsWord(String text, String word) {
        if(text.contains(word)) {
            return true;
        } else {
            return false;
        }
    }
}

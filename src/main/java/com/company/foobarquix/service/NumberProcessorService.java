package com.company.foobarquix.service;

import java.util.Map;
import java.util.stream.Collectors;

public class NumberProcessorService {

    private static final Map<Character, String> DIGIT_MAPPING = Map.of(
            '3', "FOO",
            '5', "BAR",
            '7', "QUIX"
    );

    private static final Map<Integer, String> DIVISIBILITY_MAPPING = Map.of(
            3, "FOO",
            5, "BAR"
    );

    public static String process(int number) {
        StringBuilder result = new StringBuilder();
        checkDivisibility(number, result);
        checkDigitPresence(number, result);
        return result.isEmpty() ? String.valueOf(number) : result.toString();
    }

    private static void checkDigitPresence(int number, StringBuilder result) {
        result.append(String.valueOf(number)
                .chars()
                .mapToObj(c -> DIGIT_MAPPING.getOrDefault((char) c, ""))
                .collect(Collectors.joining()));
    }

    private static void checkDivisibility(int number, StringBuilder result) {
        DIVISIBILITY_MAPPING.forEach((key, value) -> {
            if (number % key == 0) {
                result.append(value);
            }
        });

    }
}



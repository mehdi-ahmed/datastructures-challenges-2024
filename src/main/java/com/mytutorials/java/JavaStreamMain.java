package com.mytutorials.java;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Find the most frequent element in an array using Stream API.
 * Remove its immediate left and right elements.
 */

public class JavaStreamMain {

    public static void main(String[] args) {

        Integer[] array = {10, 20, 10, 30, 50, 20, 60, 20, 20, 20, 70, 80, 20, 90};

        // Step 1: Find the most frequent element in an array
        Integer maxCount = Arrays.stream(array)
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        System.out.println("maxCount = " + maxCount);
    }
}

package com.mytutorials.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Find the most frequent element in an array using Stream API.
 * <p>
 *  1st way: Classic double Loop
 *  2nd way: HashMap and PriorityQueue
 *  3rd way: Java Streams
 *  4th ways: Same as 3rd way. Instead of returning one frequentElement, return a list of n frequent elements.
 * </p>
 */

class MostFrequentElement {

    /*
     * is to run two loops. The outer loop picks all elements one by one.
     * The inner loop finds the frequency of the picked element and compares it with the maximum so far.
     */

    static int mostFrequentClassic(Integer[] array) {
        int maxCount = 0;
        int mostFrequentElement = 0;

        for (Integer outerValue : array) {
            int count = 0;

            for (Integer innerValue : array) {
                if (outerValue.equals(innerValue)) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mostFrequentElement = outerValue;
            }
        }
        return mostFrequentElement;
    }





    static int mostFrequentElementStream(Integer[] array) {
        return Arrays.stream(array)
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new IllegalArgumentException("Array must not be empty"));
    }


    public static List<Integer> findByStream(Integer[] arr, int n) {
        return Arrays.stream(arr).collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey)
                .limit(n)
                .collect(Collectors.toList());
    }



    public static void main(String[] args) {

        Integer[] array = {10, 20, 10, 30, 50, 20, 60, 20, 20, 20, 70, 80, 20, 90}; // mostCommon = 20

        // Find the most frequent element in an array

        // 1st Method with Java Stream
        int mostCommonStream = mostFrequentElementStream(array);
        System.out.println("mostCommonStream = " + mostCommonStream);

        // 2nd Method with Java Loops
        int mostCommonClassic = mostFrequentClassic(array);
        System.out.println("mostCommonClassic = " + mostCommonClassic);

        // 3rd Method
        List<Integer> anotherWay = findByStream(array, 3);
        anotherWay.stream()
                .map(e -> e + " ")
                .forEach(System.out::print);
    }

}

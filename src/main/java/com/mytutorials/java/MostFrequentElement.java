package com.mytutorials.java;

import java.util.*;
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

    // 1st way: Classic double Loop
    // The outer loop picks all elements one by one.
    // The inner loop finds the frequency of the picked element and compares it with the maximum so far.

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

    // 2nd way
    // HashMap and PriorityQueue.
    // HashMap to count the occurrences of each element
    // PriorityQueue to prioritize elements based on their count.
    public static List<Integer> findByHashMapAndPriorityQueues(Integer[] array, int n) {

        Map<Integer, Integer> countMap = new HashMap<>();

        // For each element i in the Array, add it the countMap and increment its count
        for (Integer element : array) {

            // getOrDefault: Returns the value to which the specified key is mapped,
            // or defaultValue if this map contains no mapping for the key.
            countMap.put(element, countMap.getOrDefault(element, 0) + 1);
        }

        // create a max heap (Priority Q) that will prioritize elements with higher counts
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> countMap.get(b) - countMap.get(a));

        // Add all unique elements in the array to the heap
        heap.addAll(countMap.keySet());

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n && !heap.isEmpty(); i++) {
            // Poll the highest-count element from the heap and add it to the result list.
            // Poll = Retrieves and removes the head of this queue, or returns null if this queue is empty.
            result.add(heap.poll());
        }
        return result;
    }


    // 3rd Java Stream
    // Create a Map to count the occurrences of each element,
    // Compare by values: Map<key, value> where key = the element and value = frequency/count
    // return most frequent key.

    static int mostFrequentElementStream(Integer[] array) {
        return Arrays.stream(array)
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new IllegalArgumentException("Array must not be empty"));
    }

    // 4th way
    // Same as 3rd way but this time:
    // Sort the entries in the map by frequency in descending order, and then extract the n most frequent elements:

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


        // 1st Method with Java double Loops
        int mostCommonClassic = mostFrequentClassic(array);
        System.out.println("mostCommon Classic = " + mostCommonClassic); // 20

        // 2nd Method: HashMap and PriorityQueue.
        List<Integer> byHashMapAndPriorityQueues = findByHashMapAndPriorityQueues(array, 1);
        byHashMapAndPriorityQueues.stream()
                .map(e -> e + " ")
                .forEach(System.out::print); // 20

        // 3rd way with Java Stream
        int mostCommonStream = mostFrequentElementStream(array);
        System.out.println("mostCommon Stream = " + mostCommonStream); //20


        // 4th way, similar to 3rd Method but return n values by frequency
        List<Integer> anotherWay = findByStream(array, 3);
        anotherWay.stream()
                .map(e -> e + " ")
                .forEach(System.out::print); // 20, 10, 80
    }

}

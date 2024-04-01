package com.mytutorials.java;

import java.util.*;

public class RemoveStringDuplicatesList {

    public static void main(String[] args) {

        List<String> cars = List.of(
                "volvo", "bmw", "mercedes", "vw", "tesla", "volvo", "toyota", "mercedes", "bugatti", "bmw");


        // 1. Removing duplicates using Streams
        List<String> carsWithoutDuplicates = cars.stream()
                .distinct()
                .toList();

        System.out.println("carsWithoutDuplicates = " + carsWithoutDuplicates); //[volvo, bmw, mercedes, vw, tesla, toyota, bugatti]

        // 2.  Removing duplicates using Loops
        List<String> tempCars = new ArrayList<>();
        for (String car : cars) {
            if (!tempCars.contains(car)) {
                tempCars.add(car);
            }
        }

        System.out.println("tempCars = " + tempCars);//  [volvo, bmw, mercedes, vw, tesla, toyota, bugatti]

        // 3. Removing duplicates using LinkedHashSet - With preserving order.
        List<String> carsWithNoDuplicates = new ArrayList<>(new LinkedHashSet<>(cars));
        System.out.println("carsWithNoDuplicates = " + carsWithNoDuplicates); // [volvo, bmw, mercedes, vw, tesla, toyota, bugatti]


        // 4. Removing duplicates using Map
        Map<String, Integer> carMap = new LinkedHashMap<>();
        for (String car : cars) {
            carMap.put(car, carMap.getOrDefault(car, 0) + 1);
        }

        // getOrDefault =  used to get the value mapped with specified key.
        // If no value is mapped with the provided key then the default value is returned.

        List<String> carWithNoDoubles = new ArrayList<>(carMap.keySet());
        System.out.println("carWithNoDoubles = " + carWithNoDoubles); // [volvo, bmw, mercedes, vw, tesla, toyota, bugatti]


    }
}

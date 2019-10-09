package com.sam.dacanay.interview.triplets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TripletSolution {
    static class Triplet {
        private int first;
        private int second;
        private int third;

        public Triplet(int first, int second, int third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public String toString() {
            return String.format("{first=%d, second=%d, third=%d}", first, second, third);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-1, 2, 0, 3, 4, 7, -1, -2};
        List<Triplet> triplets = findTripletsThatSumToZero(arr);
        System.out.println(triplets.stream().map(Triplet::toString).collect(Collectors.joining(",")));
    }

    public static List<Triplet> findTripletsThatSumToZero(int[] arr) {
        Map<Integer,Integer> occurrenceMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int occurrences = occurrenceMap.getOrDefault(arr[i], 0);
            occurrenceMap.put(arr[i], occurrences + 1);
        }

        List<Triplet> triplets = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int sum = arr[i] + arr[j];
                int lookupNum = 0 - sum;

                int numOccurrences = occurrenceMap.getOrDefault(lookupNum, -1);
                if (numOccurrences > 0) {
                    Triplet triplet = new Triplet(arr[i], arr[j], lookupNum);
                    triplets.add(triplet);
                    occurrenceMap.put(lookupNum, numOccurrences - 1);
                }
            }
        }
        return triplets;
    }
}

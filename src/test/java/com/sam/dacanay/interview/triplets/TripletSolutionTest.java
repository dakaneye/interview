package com.sam.dacanay.interview.triplets;

import org.junit.Test;

import java.util.List;

import static com.sam.dacanay.interview.triplets.TripletSolution.findTripletsThatSumToZero;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TripletSolutionTest {

    @Test
    public void testFindTripletsThatSumToZeroSimple() {
        int[] arr = new int[]{-1, 2, 0, 3, 4, 7, -1, -2};
        List<TripletSolution.Triplet> triplets = findTripletsThatSumToZero(arr);
        assertNotNull(triplets);
        assertEquals(triplets.size(), 6);
    }
}

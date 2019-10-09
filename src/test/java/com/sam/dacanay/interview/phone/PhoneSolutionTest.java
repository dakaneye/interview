package com.sam.dacanay.interview.phone;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

// Won't actually call the oxford API for now, because free accounts can't access the search API
public class PhoneSolutionTest {

    private static final String API_ID = System.getenv("API_ID");
    private static final String API_KEY = System.getenv("API_KEY");

    @Test
    public void testGetValidWords() {
        String phoneNumber = "(805)405-4748";
        List<String> words = PhoneSolution.getValidWords(phoneNumber, null, null);
        System.out.println(words.stream().collect(Collectors.joining(",")));
        assertNotNull(words);
        assertTrue(words.size() > 0);
    }
}

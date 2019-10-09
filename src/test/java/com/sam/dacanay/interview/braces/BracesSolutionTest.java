package com.sam.dacanay.interview.braces;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BracesSolutionTest {

    @Test
    public void testBracesAreValidSimple() {
        assertTrue(BracesSolution.bracesAreValid("{[()]}"));
        assertFalse(BracesSolution.bracesAreValid("{[(])}"));
    }

    @Test
    public void testBracesAreValidExtraCharacters() {
        assertTrue(BracesSolution.bracesAreValid("{{2 + v(x + 11)}/[4 + 8]}"));
        assertFalse(BracesSolution.bracesAreValid("{{2 + v(x + 11)}/[4 + 8]"));
    }
}

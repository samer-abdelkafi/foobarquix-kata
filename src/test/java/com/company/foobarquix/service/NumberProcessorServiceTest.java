package com.company.foobarquix.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class NumberProcessorServiceTest {

    @Test
    void testDivisibilityOnly() {
        // Number divisible by 3
        assertEquals("FOO", NumberProcessorService.process(6));
        // Number divisible by 5
        assertEquals("BAR", NumberProcessorService.process(10));

    }

    @Test
    void testDigitPresenceOnly() {
        // Number containing 3
        assertEquals("FOO", NumberProcessorService.process(13));
        // Number containing 5
        assertEquals("BAR", NumberProcessorService.process(52));
        // Number containing 7
        assertEquals("QUIX", NumberProcessorService.process(47));
    }

    @Test
    void testDivisibilityAndDigitPresence() {
        // Number divisible by 3 and containing 3
        assertEquals("FOOFOO", NumberProcessorService.process(3));
        // Number divisible by 3 and containing 5
        assertEquals("FOOBARBAR", NumberProcessorService.process(15));
        // Number divisible by 5 and containing 7
        assertEquals("FOOFOOFOO", NumberProcessorService.process(33));
    }

    @Test
    void testNumberWithoutEncodingMatchesItself() {
        // Number not divisible by or containing any key
        assertEquals("1", NumberProcessorService.process(1));
        assertEquals("2", NumberProcessorService.process(2));
        assertEquals("8", NumberProcessorService.process(8));
        assertEquals("14", NumberProcessorService.process(14));

    }
  
}
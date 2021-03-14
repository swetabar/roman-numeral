package com.roman.conversion.utility;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link RomanNumeralUtility}
 * @author swetabarman
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RomanNumeralUtilityTest {

    @Test
    public void testIfNumberIsDouble() {
        assertTrue(RomanNumeralUtility.isDoubleValue("9.5"));
    }

    @Test
    public void testIfNumberIsNotDouble() {
        assertFalse(RomanNumeralUtility.isDoubleValue("9"));
    }

    @Test
    public void testWithNullAsNumber() {
        assertFalse(RomanNumeralUtility.isDoubleValue(null));
    }

    @Test
    public void testWithStringValue() {
        assertFalse(RomanNumeralUtility.isDoubleValue("Hello"));
    }

    @Test
    public void testIsIntegerWithNullValue() {
        assertFalse(RomanNumeralUtility.isIntegerValue(null));
    }
}

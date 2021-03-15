package com.roman.conversion.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link RomanNumeralUtils}
 * @author swetabarman
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RomanNumeralUtilsTest {

    @Test
    public void testIfNumberIsDouble() {
        assertTrue(RomanNumeralUtils.isDoubleValue("9.5"));
    }

    @Test
    public void testIfNumberIsNotDouble() {
        assertFalse(RomanNumeralUtils.isDoubleValue("9"));
    }

    @Test
    public void testWithNullAsNumber() {
        assertFalse(RomanNumeralUtils.isDoubleValue(null));
    }

    @Test
    public void testWithStringValue() {
        assertFalse(RomanNumeralUtils.isDoubleValue("Hello"));
    }

    @Test
    public void testIsIntegerWithNullValue() {
        assertFalse(RomanNumeralUtils.isIntegerValue(null));
    }
}

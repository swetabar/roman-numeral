package com.roman.conversion.service;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RomanNumeralServiceTests {

    @Autowired
    private RomanNumeralService romanNumeralService;

    @Test
     public void testConvertDecimalToRomanNumeral() {
        Assertions.assertEquals("1",
                romanNumeralService.convertDecimalToRomanNumeral(1).getInput());
        Assertions.assertEquals("I",
                romanNumeralService.convertDecimalToRomanNumeral(1).getOutput());

    }
}

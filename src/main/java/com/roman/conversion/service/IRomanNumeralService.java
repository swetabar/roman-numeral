package com.roman.conversion.service;
import com.roman.conversion.model.RomanNumeral;

/**
 * This is the interface
 */
public interface IRomanNumeralService {

    RomanNumeral convertDecimalToRomanNumeral(int number);
}

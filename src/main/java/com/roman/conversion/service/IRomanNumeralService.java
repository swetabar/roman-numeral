package com.roman.conversion.service;
import com.roman.conversion.model.RomanNumeral;

public interface IRomanNumeralService {

    RomanNumeral convertDecimalToRomanNumeral(int number);
}

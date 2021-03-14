package com.roman.conversion.service;
import com.roman.conversion.model.RomanNumeral;
import org.springframework.stereotype.Component;

/**
 * This class implements methods that convert an integer to a roman numeral.
 * @author swetabarman
 */
@Component
public class RomanNumeralService implements IRomanNumeralService {

    /**
     * This method accepts an integer number and returns a RomalNumeral
     * object with two fields - input and output. For an input 10, the output
     * would be:
     * {@code {"input" : "10",
     * "output" : "X"}}
     * @param number
     * @return RomanNumeral
     */
    @Override
    public RomanNumeral convertDecimalToRomanNumeral(final int number) {
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanNumerals = {"M","CM","D","CD","C","XC","L","XL","X","IX",
                "V","IV",
                "I"};
        int num = number;
        StringBuilder builder = new StringBuilder();

        for(int i=0; i<values.length; i++) {
            while(num >= values[i]) {
                num -= values[i];
                builder.append(romanNumerals[i]);
            }
        }
        return new RomanNumeral(String.valueOf(number), builder.toString());
    }
}

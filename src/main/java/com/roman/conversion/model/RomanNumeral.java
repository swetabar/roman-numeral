package com.roman.conversion.model;

import lombok.Getter;

/**
 * This is a model class for Roman numerals that wraps in the input and the
 * output.
 * @author swetabarman
 */
public class RomanNumeral {

    @Getter
    String input;

    @Getter
    String output;

    public RomanNumeral(String input, String output) {
        this.input = input;
        this.output = output;
    }
}

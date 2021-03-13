package com.roman.conversion.model;

import lombok.Getter;
import lombok.Setter;

/**
 * This is a model class - which is a POJO that wraps in the input and the
 * output
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

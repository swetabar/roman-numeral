package com.roman.conversion.utility;

public final class RomanNumeralUtility {

    private RomanNumeralUtility() {}

    public static boolean isDoubleValue(String input) {
        if (input == null) {
            return false;
        }
        if (isIntegerValue(input)) {
            return false;
        }
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isIntegerValue(String input) {
        if (input == null)
        {
            return false;
        }
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

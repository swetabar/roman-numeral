package com.roman.conversion.utility;

public final class RomanNumeralUtility {

    public static final int ZERO = 0;
    public static final int MIN_LIMIT = 1;
    public static final int MAX_LIMIT = 255;
    public static final String EXCEPTION_MESSAGE_FOR_ZERO = "The number " +
            "entered was 0. There is no roman numeral for 0.";
    public static final String EXCEPTION_MESSAGE_OUTSIDE_LIMIT = "The number " +
            "should " +
            "lie within the limit of {0} - {1}.";
    public static final String EXCEPTION_MESSAGE_INVALID_INPUT = "{0} is an " +
            "invalid input.";
    public static final String VALID_MESSAGE = "Please enter an integer value" +
            " in the range of {0} - {1}";
    public static final String EXCEPTION_MESSAGE_DOUBLE_INPUT = "You entered " +
            "{0}, which is a double value.";
    public static final String ERROR_CODE_INVALID_INPUT = "INVALID_INPUT";
    public static final String ERROR_CODE_EMPTY_REQUEST = "REQUEST_IS_EMPTY";
    public static final String EXCEPTION_MESSAGE_EMPTY_REQUEST = "The {0} " +
            "parameter is blank.";


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

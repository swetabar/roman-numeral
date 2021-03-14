package com.roman.conversion.utility;

/**
 * This is a utility class with methods and constants to be used across the
 * project.
 * @author swetabarman
 */
public final class RomanNumeralUtility {

    public static final int ZERO = 0;
    public static final int MIN_LIMIT = 1;
    public static final int MAX_LIMIT = 255;
    public static final String BLANK = " ";
    public static final String BACKSLASH_QUOTE = "\\\"";
    public static final String QUERY = "query";
    public static final String EXCEPTION_MESSAGE_FOR_ZERO = "The number " +
            "entered was 0. There is no roman numeral for 0.";
    public static final String EXCEPTION_MESSAGE_OUTSIDE_LIMIT = "The number " +
            "should " +
            "lie within the limit of {0} - {1}.";
    public static final String EXCEPTION_MESSAGE_INVALID_INPUT = "{0} is an " +
            "invalid input.";
    public static final String VALID_MESSAGE = "Please enter an integer " +
            "value" +
            " in the range of {0} - {1}.";
    public static final String EXCEPTION_MESSAGE_DOUBLE_INPUT = "You entered " +
            "{0}, which is a double value.";
    public static final String ERROR_CODE_INVALID_INPUT = "INVALID_INPUT";
    public static final String ERROR_CODE_EMPTY_REQUEST = "REQUEST_IS_EMPTY";
    public static final String ERROR_CODE_REQUEST_LIMIT_ERROR =
            "REQUEST_LIMIT_ERROR";
    public static final String EXCEPTION_MESSAGE_EMPTY_REQUEST = "The {0} " +
            "parameter is blank.";


    private RomanNumeralUtility() {}

    /**
     * This utility method returns if input is a double value. It first checks
     * whether input is an integer.
     * @param input
     * @return boolean
     */
    public static boolean isDoubleValue(String input) {
        if (input == null || isIntegerValue(input)) {
            return false;
        }
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * This utlity method checks if input is an integer value.
     * @param input
     * @return
     */
    public static boolean isIntegerValue(String input) {
        if (input == null) {
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

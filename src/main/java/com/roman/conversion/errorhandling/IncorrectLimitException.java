package com.roman.conversion.errorhandling;

/**
 * The IncorrectLimitException is raised when the input number entered for
 * conversion to a roman numeral is above the maximum limit or below the
 * maximum limit or is the number 0.
 * @author swetabarman
 */
public class IncorrectLimitException extends Exception{

    public IncorrectLimitException(String message) {
        super(message);
    }
}

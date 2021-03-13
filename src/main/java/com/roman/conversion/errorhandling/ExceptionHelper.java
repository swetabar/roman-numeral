package com.roman.conversion.errorhandling;

import com.roman.conversion.model.Error;
import com.roman.conversion.utility.RomanNumeralUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHelper {

    @ExceptionHandler({NumberFormatException.class})
    public ResponseEntity<Error> handleNumberFormatException(NumberFormatException ex) {
        Error error = new Error();
        error.setErrorCode("INVALID_INPUT");
        String queryValue = ex.getLocalizedMessage().split("\\\"")[1];
        error.setMessage("You entered " + ex.getLocalizedMessage() + "." + "Please enter an" +
                " " +
                "integer value in the " +
                "range of 1 - " +
                "255");
        if (RomanNumeralUtility.isDoubleValue(queryValue)) {
            error.setMessage("You entered " + queryValue + ", which is a " +
                    "double value. Please enter an integer " +
                    "value in the range of 1 - 255.");
        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Error> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        Error error = new Error("REQUEST_IS_EMPTY", name + " parameter is " +
                "blank.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectLimitException.class)
    public ResponseEntity<Error> handleLimitException(IncorrectLimitException ex) {
        Error error = new Error("REQUEST_LIMIT_ERROR",
                ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

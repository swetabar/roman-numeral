package com.roman.conversion.errorhandling;

import com.roman.conversion.model.Error;
import com.roman.conversion.utility.RomanNumeralUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.MessageFormat;

import static com.roman.conversion.utility.RomanNumeralUtility.*;

/**
 * This class serves as the error handler for the application.
 * The error cases cases that we consider include mostly exceptions thrown
 * while making a GET request to : /romannumeral?query={number}
 * {@link com.roman.conversion.controller.RomanNumeralController#getConvertedRomanNumeral(Integer)}.
 *
 * @author swetabarman
 */
@ControllerAdvice
public class ExceptionHelper {

    /**
     * This method handles NumberFormatExpections. These exceptions are
     * thrown in the following cases :
     * <ul>
     *     <li>
     *         When {number} is a string text. For e.g., "abc"
     *     </li>
     *     <li>
     *         When {number} is a double value. For e.g., "9.8"
     *     </li>
     * </ul>
     * It return an Error ResponseEntity with an HTTP status of 400.
     * @param ex
     * @return ResponseEntity
     */
    @ExceptionHandler({NumberFormatException.class})
    public ResponseEntity<Error> handleNumberFormatException(NumberFormatException ex) {
        Error error = new Error();
        error.setErrorCode(ERROR_CODE_INVALID_INPUT);
        //queryValue stores the input string assigned to the query parameter
        String queryValue = ex.getLocalizedMessage().split(BACKSLASH_QUOTE)[1];
        error.setMessage(MessageFormat.format(EXCEPTION_MESSAGE_INVALID_INPUT
                , queryValue) + BLANK + MessageFormat.format(VALID_MESSAGE,
                MIN_LIMIT
                , MAX_LIMIT));
        if (RomanNumeralUtility.isDoubleValue(queryValue)) {
            error.setMessage(MessageFormat.format(EXCEPTION_MESSAGE_DOUBLE_INPUT, queryValue) + BLANK +
                    MessageFormat.format(VALID_MESSAGE, MIN_LIMIT
                            , MAX_LIMIT));
        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method handles exceptions when the request parameter - `query` is
     * either absent or is an empty string.
     *
     * @param ex
     * @return ResponseEntity
     * It return an Error ResponseEntity with an HTTP status of 400.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Error> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        Error error = new Error(ERROR_CODE_EMPTY_REQUEST,
                MessageFormat.format(EXCEPTION_MESSAGE_EMPTY_REQUEST, name));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method handles exceptions when the value of the request parameter -
     * `query` is below the {@link RomanNumeralUtility#MIN_LIMIT} or is above
     * the {@link RomanNumeralUtility#MAX_LIMIT}. It is also raised when the
     * input number entered in the query parameter is 0.
     *
     * @param ex
     * @return ResponseEntity
     * It return an Error ResponseEntity with an HTTP status of 422.
     */
    @ExceptionHandler(IncorrectLimitException.class)
    public ResponseEntity<Error> handleLimitException(IncorrectLimitException ex) {
        Error error = new Error(ERROR_CODE_REQUEST_LIMIT_ERROR,
                ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

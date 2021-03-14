package com.roman.conversion.controller;

import com.roman.conversion.errorhandling.IncorrectLimitException;
import com.roman.conversion.model.RomanNumeral;
import com.roman.conversion.service.IRomanNumeralService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.MessageFormat;
import static com.roman.conversion.utility.RomanNumeralUtility.*;

/**
 * This class is the Rest Controller for roman numeral conversion.
 * Currently, RomanNumeralConverter consists of one endpoint : /romannumeral.
 * @author swetabarman
 * @
 */

@RestController
public class RomanNumeralController {

    /**
     * We use the IRomanNumeralService method
     * {@link #getConvertedRomanNumeral(Integer)} to convert an integer to a
     * roman numeral.
     */
    @Resource
    IRomanNumeralService service;

    /**
     * This method is a restful endpoint to convert an input integer to a roman
     * numeral. It expects an integer {number} as the query parameter. On your
     * local instance,
     * make a GET request to
     * http://localhost:8080?query={number}.
     * For e.g. GET http://localhost:8080?query=9 will return a
     * ResponseEntity with an HTTP status of 200 and a
     * {@link com.roman.conversion.model.RomanNumeral} object
     * response, {@code {input : "9", output: "IX"}}.
     * <p>
     *     The range of acceptable numbers in this API for conversion to roman
     *     numerals
     *     are : 1 - 255. The number 0 or negative numbers do not have a
     *     valid roman numeral
     *     equivalent.
     * </p>
     *
     *
     * @param number
     * @return ResponseEntity
     * @throws IncorrectLimitException
     */
    @GetMapping(value = "/romannumeral")
    public ResponseEntity<RomanNumeral> getConvertedRomanNumeral(@RequestParam(value =
            "query") Integer number) throws IncorrectLimitException {
        if (number == ZERO)
            throw new IncorrectLimitException(EXCEPTION_MESSAGE_FOR_ZERO);
        if (number < MIN_LIMIT || number > MAX_LIMIT)
            throw new IncorrectLimitException(MessageFormat.format(EXCEPTION_MESSAGE_OUTSIDE_LIMIT,
                    MIN_LIMIT, MAX_LIMIT));
        RomanNumeral numeral = service.convertDecimalToRomanNumeral(number);
        return new ResponseEntity<>(numeral, HttpStatus.OK);
    }
}

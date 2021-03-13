package com.roman.conversion.controller;

import com.roman.conversion.errorhandling.IncorrectLimitException;
import com.roman.conversion.model.RomanNumeral;
import com.roman.conversion.service.IRomanNumeralService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * This class is the rest controller for roman numeral conversion.
 * @author Sweta Barman
 */

@RestController
public class RomanNumeralController {

    /**
     *
     */
    @Resource
    IRomanNumeralService service;

    /**
     *
     * @param number
     * @return
     * @throws IncorrectLimitException
     */
    @GetMapping(value = "/romannumeral")
    public ResponseEntity<RomanNumeral> getConvertedRomanNumeral(@RequestParam(value =
            "query") Integer number) throws IncorrectLimitException {
        if (number == 0)
            throw new IncorrectLimitException("The number entered was 0. " +
                    "There is no roman numeral for number 0");
        if (number < 0 || number > 255)
            throw new IncorrectLimitException("The number should lie within " +
                    "the limit of 1 - 255.");
        RomanNumeral numeral = service.convertDecimalToRomanNumeral(number);
        return new ResponseEntity<>(numeral, HttpStatus.OK);
    }

}

package com.roman.conversion.controller;

import com.roman.conversion.model.Error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@RestController
class HandleErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public ResponseEntity<Error> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Error error =
                new Error(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                error.setErrorCode(HttpStatus.NOT_FOUND.toString());
                error.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @deprecated
     */
    @Override
    public String getErrorPath() {
        return PATH;
    }
}

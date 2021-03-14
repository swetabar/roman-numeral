package com.roman.conversion.model;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents error object which is created when the application
 * runs into an exception.
 * @author swetabarman
 */
public class Error {

    @Getter
    @Setter
    String errorCode;

    public Error(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public Error() {
    }

    @Getter
    @Setter
    String message;

}

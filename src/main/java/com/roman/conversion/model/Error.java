package com.roman.conversion.model;

import lombok.Getter;
import lombok.Setter;

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

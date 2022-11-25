package com.carlosblinf.shoppingcart.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends RuntimeException {
    private String msg;
    private HttpStatus httpStatus;

    public CustomException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.msg = msg;
        this.httpStatus = httpStatus;
    }
}

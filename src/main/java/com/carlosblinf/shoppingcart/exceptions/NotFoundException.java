package com.carlosblinf.shoppingcart.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg);
    }
}

package com.cookieit.cart.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartNotFoundException extends Exception {
    public CartNotFoundException(String message) {
        super((message));
    }
}

package com.uzum.retail.exception;

import com.uzum.retail.constant.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class OrderItemNotFoundException extends BusinessException {
    public OrderItemNotFoundException(String message) {
        super(10011, message, ErrorType.INTERNAL, HttpStatus.NOT_FOUND);
    }
}

package com.uzum.retail.exception;

import com.uzum.retail.constant.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException(String message) {
        super(1001, message, ErrorType.INTERNAL, HttpStatus.NOT_FOUND);

    }
}

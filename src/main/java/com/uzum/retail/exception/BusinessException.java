package com.uzum.retail.exception;

import com.uzum.retail.constant.enums.ErrorType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;


@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BusinessException extends RuntimeException{
    int code;
    String message;
    HttpStatus status;
    ErrorType errorType;

    public BusinessException(int code, String message, ErrorType errorType, HttpStatus status){
        super(message);
        this.code = code;
        this.message = message;
        this.status = status;
        this.errorType = errorType;
    }
}

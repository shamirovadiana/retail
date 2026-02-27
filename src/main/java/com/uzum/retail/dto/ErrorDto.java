package com.uzum.retail.dto;

import com.uzum.retail.constant.enums.ErrorType;

import java.util.List;

public record ErrorDto(
        int code,
        String message,
        ErrorType type,
        List<String> validationErrors
) {}

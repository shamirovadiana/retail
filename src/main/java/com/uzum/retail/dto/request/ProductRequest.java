package com.uzum.retail.dto.request;

public record ProductRequest (
        String SKU,
        String size,
        String colour,
        String description,
        Double price,
        Long categoryId
){}

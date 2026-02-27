package com.uzum.retail.dto;

public record ProductDto(
        String SKU,
        String size,
        String colour,
        String description,
        Double price,
        Long categoryId
) {}

package com.uzum.retail.dto;

public record OrderItemDto(
        Long productId,
//        Long orderId,
        Double productAmount,
        Double price
) {}

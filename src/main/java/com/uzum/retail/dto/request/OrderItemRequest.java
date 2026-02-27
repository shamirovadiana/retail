package com.uzum.retail.dto.request;

public record OrderItemRequest(
        Long productId,
//        Long orderId,
        Double productAmount,
        Double price
) {
}

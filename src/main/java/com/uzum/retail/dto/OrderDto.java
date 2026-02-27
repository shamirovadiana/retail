package com.uzum.retail.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<OrderItemDto> items,
        Double totalPrice
) {}

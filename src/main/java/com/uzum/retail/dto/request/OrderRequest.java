package com.uzum.retail.dto.request;

import com.uzum.retail.dto.OrderItemDto;

import java.util.List;

public record OrderRequest(
       List<OrderItemDto> items
) {}

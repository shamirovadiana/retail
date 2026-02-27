package com.uzum.retail.service;

import com.uzum.retail.dto.request.OrderItemRequest;
import com.uzum.retail.dto.response.OrderItemResponse;
import org.springframework.data.domain.Page;

public interface OrderItemService {

    OrderItemResponse create(OrderItemRequest request);

    OrderItemResponse getById(Long id);

    OrderItemResponse update(Long id, OrderItemRequest request);

    void delete(Long id);
}

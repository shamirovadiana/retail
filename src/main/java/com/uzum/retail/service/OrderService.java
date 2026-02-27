package com.uzum.retail.service;

import com.uzum.retail.dto.request.OrderRequest;
import com.uzum.retail.dto.response.OrderResponse;

public interface OrderService {

    OrderResponse create(OrderRequest request);

    OrderResponse getById(Long id);

    OrderResponse update(Long id, OrderRequest request);

    void delete(Long id);
}

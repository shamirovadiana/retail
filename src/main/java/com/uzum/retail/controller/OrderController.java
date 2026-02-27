package com.uzum.retail.controller;

import com.uzum.retail.dto.request.OrderRequest;
import com.uzum.retail.dto.response.OrderResponse;
import org.springframework.http.ResponseEntity;

public interface OrderController {

    ResponseEntity<OrderResponse> createOrder(OrderRequest request);

    ResponseEntity<OrderResponse> getOrder(Long id);

    ResponseEntity<OrderResponse> updateOrder(Long id, OrderRequest request);

    void deleteOrder(Long id);
}

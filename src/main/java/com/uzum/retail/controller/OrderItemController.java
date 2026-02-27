package com.uzum.retail.controller;

import com.uzum.retail.dto.request.OrderItemRequest;
import com.uzum.retail.dto.response.OrderItemResponse;
import org.springframework.http.ResponseEntity;

public interface OrderItemController {

    ResponseEntity<OrderItemResponse> createOrderItem(OrderItemRequest request);

    ResponseEntity<OrderItemResponse> getOrderItem(Long id);

    ResponseEntity<OrderItemResponse> updateOrderItem(Long id, OrderItemRequest orderItemRequest);

    void deleteOrderItem(Long id);
}

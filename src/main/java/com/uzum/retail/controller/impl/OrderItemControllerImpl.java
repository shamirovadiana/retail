package com.uzum.retail.controller.impl;

import com.uzum.retail.controller.OrderItemController;
import com.uzum.retail.dto.request.OrderItemRequest;
import com.uzum.retail.dto.response.OrderItemResponse;
import com.uzum.retail.service.impl.OrderItemServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderitems")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderItemControllerImpl implements OrderItemController {

    OrderItemServiceImpl orderItemService;

    @PostMapping("/create-orderitem")
    @Override
    public ResponseEntity<OrderItemResponse> createOrderItem(@RequestBody @Valid OrderItemRequest request) {
        return ResponseEntity.ok(orderItemService.create(request));
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<OrderItemResponse> getOrderItem(@PathVariable Long id) {
        return ResponseEntity.ok(orderItemService.getById(id));
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<OrderItemResponse> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemRequest request) {
        return ResponseEntity.ok(orderItemService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemService.delete(id);
    }
}

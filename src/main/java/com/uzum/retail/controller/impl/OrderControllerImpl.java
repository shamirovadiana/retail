package com.uzum.retail.controller.impl;

import com.uzum.retail.controller.OrderController;
import com.uzum.retail.dto.request.OrderRequest;
import com.uzum.retail.dto.response.OrderResponse;
import com.uzum.retail.service.impl.OrderServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderControllerImpl implements OrderController {

    OrderServiceImpl orderService;

    @PostMapping("/create-order")
    @Override
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequest request) {
        return ResponseEntity.ok(orderService.create(request));
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id, @RequestBody @Valid OrderRequest request) {
        return ResponseEntity.ok(orderService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
    }
}

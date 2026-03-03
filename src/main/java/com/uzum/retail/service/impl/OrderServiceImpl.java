package com.uzum.retail.service.impl;

import com.uzum.retail.dto.OrderItemDto;
import com.uzum.retail.dto.request.OrderRequest;
import com.uzum.retail.dto.response.OrderResponse;
import com.uzum.retail.entity.OrderEntity;
import com.uzum.retail.exception.OrderNotFoundException;
import com.uzum.retail.mapper.OrderMapper;
import com.uzum.retail.repository.OrderRepository;
import com.uzum.retail.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;
    OrderMapper orderMapper;

    @Override
    public OrderResponse create(OrderRequest request) {
        List<OrderItemDto> orderItems = request.items();
        double totalPrice = 0.0;

        for(OrderItemDto orderItem : orderItems){
            totalPrice += orderItem.price() * orderItem.productAmount();
        }

        OrderEntity orderEntity = orderMapper.toEntity(request);
        orderEntity.setTotalPrice(totalPrice);
        orderEntity.setCreatedAt(LocalDateTime.now());
        orderEntity.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(orderEntity);

        return orderMapper.toDto(orderEntity);
    }

    @Override
    public OrderResponse getById(Long id) {
        OrderEntity orderEntity = orderRepository
                .findById(id)
                .orElseThrow(()-> new OrderNotFoundException(id.toString()));

        return orderMapper.toDto(orderEntity);
    }

    @Override
    public OrderResponse update(Long id, OrderRequest request) {
        OrderEntity orderEntity = orderRepository
                .findById(id)
                .orElseThrow(()-> new OrderNotFoundException(id.toString()));


        List<OrderItemDto> orderItems = request.items();
        double totalPrice = 0.0;
        for(OrderItemDto orderItem : orderItems){
            totalPrice += orderItem.price() * orderItem.productAmount();
        }

        orderEntity.setTotalPrice(totalPrice);
        orderEntity.setUpdatedAt(LocalDateTime.now());

        return orderMapper.toDto(orderEntity);
    }

    @Override
    public void delete(Long id) {
        orderRepository
                .findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id.toString()));

        orderRepository.deleteById(id);
    }
}

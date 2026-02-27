package com.uzum.retail.service.impl;

import com.uzum.retail.dto.request.OrderItemRequest;
import com.uzum.retail.dto.response.OrderItemResponse;
import com.uzum.retail.entity.OrderItemEntity;
import com.uzum.retail.exception.OrderItemNotFoundException;
import com.uzum.retail.mapper.OrderItemMapper;
import com.uzum.retail.repository.OrderItemRepository;
import com.uzum.retail.service.OrderItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderItemServiceImpl implements OrderItemService {

    OrderItemRepository orderItemRepository;
    OrderItemMapper orderItemMapper;

    @Override
    public OrderItemResponse create(OrderItemRequest request) {
        OrderItemEntity orderItemEntity = orderItemMapper.toEntity(request);
        OrderItemEntity result = orderItemRepository.save(orderItemEntity);
        return orderItemMapper.toDto(result);
    }

    @Override
    public OrderItemResponse getById(Long id) {
        OrderItemEntity orderItemEntity = orderItemRepository
                .findById(id)
                .orElseThrow(() -> new OrderItemNotFoundException(id.toString()));

        return orderItemMapper.toDto(orderItemEntity);
    }

    @Override
    public OrderItemResponse update(Long id, OrderItemRequest request) {
        OrderItemEntity orderItemEntity = orderItemRepository
                .findById(id)
                .orElseThrow(() -> new OrderItemNotFoundException(id.toString()));

        orderItemEntity.setProductAmount(request.productAmount());
        if(orderItemEntity.getProductAmount() == 0){
            delete(id);
        } else {
            orderItemRepository.save(orderItemEntity);
        }
        return orderItemMapper.toDto(orderItemEntity);
    }


    @Override
    public void delete(Long id) {
        orderItemRepository
                .findById(id)
                .orElseThrow(() -> new OrderItemNotFoundException(id.toString()));

        orderItemRepository.deleteById(id);
    }
}

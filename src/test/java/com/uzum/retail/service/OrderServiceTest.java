package com.uzum.retail.service;

import com.uzum.retail.entity.OrderEntity;
import com.uzum.retail.mapper.OrderMapper;
import com.uzum.retail.repository.OrderRepository;
import com.uzum.retail.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Spy
    OrderMapper orderMapper;

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    @Captor
    ArgumentCaptor<OrderEntity> orderCaptor;

    @Test
    @DisplayName("createOrder")
    void createOrder_ShouldReturnOrderResponse(){

    }
}

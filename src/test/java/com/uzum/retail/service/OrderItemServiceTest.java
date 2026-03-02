package com.uzum.retail.service;

import com.uzum.retail.dto.request.OrderItemRequest;
import com.uzum.retail.dto.response.OrderItemResponse;
import com.uzum.retail.entity.OrderItemEntity;
import com.uzum.retail.exception.OrderItemNotFoundException;
import com.uzum.retail.mapper.OrderItemMapper;
import com.uzum.retail.repository.OrderItemRepository;
import com.uzum.retail.service.impl.OrderItemServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderItemServiceTest {
    @Spy
    OrderItemMapper orderItemMapper;

    @Mock
    OrderItemRepository orderItemRepository;

    @InjectMocks
    OrderItemServiceImpl orderItemService;

    @Captor
    ArgumentCaptor<OrderItemEntity> orderItemCaptor;

    @Test
    @DisplayName("createOrderItem")
    void createOrderItem_ShouldReturnOrderItemResponse(){
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        OrderItemRequest orderItemRequest = new OrderItemRequest(1L,2.0,30.0);
        OrderItemResponse orderItemResponse = new OrderItemResponse(1L, 1L);

        when(orderItemMapper.toEntity(orderItemRequest))
                .thenReturn(orderItemEntity);

        when(orderItemRepository.save(orderItemEntity))
                .thenReturn(orderItemEntity);

        when(orderItemMapper.toDto(orderItemEntity))
                .thenReturn(orderItemResponse);

        OrderItemResponse result = orderItemService.create(orderItemRequest);

        assertThat(result).isNotNull();
        assertThat(orderItemResponse).isNotNull().isEqualTo(result);

        verify(orderItemMapper, times(1)).toEntity(orderItemRequest);
        verify(orderItemRepository, times(1)).save(orderItemEntity);
        verify(orderItemMapper, times(1)).toDto(orderItemEntity);
        verify(orderItemMapper, times(1)).toDto(orderItemCaptor.capture());
    }

    @Test
    @DisplayName("getById")
    void getById_ShouldReturnOrderItemResponse(){
        Long orderItemId = 1L;
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        OrderItemResponse orderItemResponse = new OrderItemResponse(1L, 1L);

        when(orderItemRepository.findById(orderItemId))
                .thenReturn(Optional.of(orderItemEntity));

        when(orderItemMapper.toDto(orderItemEntity))
                .thenReturn(orderItemResponse);

        OrderItemResponse result = orderItemService.getById(orderItemId);

        assertThat(result).isNotNull();
        assertThat(orderItemResponse).isNotNull().isEqualTo(result);

        verify(orderItemRepository, times(1)).findById(orderItemId);
        verify(orderItemMapper, times(1)).toDto(orderItemEntity);
        verify(orderItemMapper, times(1)).toDto(orderItemCaptor.capture());
    }

    @Test
    @DisplayName("getById throws OrderItemNotFoundException")
    void getById_ShouldThrowOrderItemNotFoundException_WhenOrderItemDoesNotExist(){
        Long orderItemId = 1L;

        when(orderItemRepository.findById(orderItemId))
                .thenReturn(Optional.empty());

        assertThrows(OrderItemNotFoundException.class, ()-> orderItemService.getById(orderItemId));
        verify(orderItemRepository, times(1)).findById(orderItemId);
        verifyNoInteractions(orderItemMapper);
    }

    @Test
    @DisplayName("updateOrderItem")
    void updateOrderItem_ShouldReturnOrderItemResponse(){
        Long orderItemId = 1L;
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        OrderItemRequest orderItemRequest = new OrderItemRequest(1L,2.0,30.0);
        OrderItemResponse orderItemResponse = new OrderItemResponse(1L, 1L);

        when(orderItemRepository.findById(orderItemId))
                .thenReturn(Optional.of(orderItemEntity));

        when(orderItemRepository.save(orderItemEntity))
                .thenReturn(orderItemEntity);

        when(orderItemMapper.toDto(orderItemEntity))
                .thenReturn(orderItemResponse);

        OrderItemResponse result = orderItemService.update(orderItemId, orderItemRequest);

        assertThat(result).isNotNull();
        assertThat(orderItemResponse).isNotNull().isEqualTo(result);

        verify(orderItemRepository, times(1)).findById(orderItemId);
        verify(orderItemRepository, times(1)).save(orderItemEntity);
        verify(orderItemMapper, times(1)).toDto(orderItemEntity);
        verify(orderItemMapper, times(1)).toDto(orderItemCaptor.capture());
    }

    @Test
    @DisplayName("updateOrderItem throws OrderItemNotFoundException")
    void updateOrderItem_ShouldThrowOrderItemNotFoundException_WhenOrderItemDoesNotExist(){
        Long orderItemId = 1L;

        when(orderItemRepository.findById(orderItemId))
                .thenReturn(Optional.empty());

        assertThrows(OrderItemNotFoundException.class, ()-> orderItemService.getById(orderItemId));
        verify(orderItemRepository, times(1)).findById(orderItemId);
        verifyNoInteractions(orderItemMapper);
    }

    @Test
    @DisplayName("deleteOrderItem")
    void deleteOrderItem_ShouldReturnNoResponse(){
        Long orderItemId = 1L;
        OrderItemEntity orderItemEntity = new OrderItemEntity();

        when(orderItemRepository.findById(orderItemId))
                .thenReturn(Optional.of(orderItemEntity));

        doNothing().when(orderItemRepository).deleteById(isA(Long.class));
        orderItemService.delete(orderItemId);

        verify(orderItemRepository, times(1)).findById(orderItemId);
        verify(orderItemRepository, times(1)).deleteById(orderItemId);
    }
}

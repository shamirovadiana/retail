package com.uzum.retail.mapper;

import com.uzum.retail.dto.request.OrderItemRequest;
import com.uzum.retail.dto.response.OrderItemResponse;
import com.uzum.retail.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productEntity.id", source = "productId")
    OrderItemEntity toEntity(OrderItemRequest request);

    @Mapping(target = "productId", source = "productEntity.id")
    //@Mapping(target = "orderId", source = "orderEntity.id")
    OrderItemResponse toDto(OrderItemEntity orderItemEntity);

    @Mapping(target = "productId", source = "productEntity.id")
    List<OrderItemResponse> toDto(List<OrderItemEntity> orderItemEntities);
}

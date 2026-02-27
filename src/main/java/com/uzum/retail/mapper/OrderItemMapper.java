package com.uzum.retail.mapper;

import com.uzum.retail.dto.request.OrderItemRequest;
import com.uzum.retail.dto.response.OrderItemResponse;
import com.uzum.retail.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(target = "id", ignore = true)
    OrderItemEntity toEntity(OrderItemRequest request);

    @Mapping(target = "productId", source = "productEntity.id")
    //@Mapping(target = "orderId", source = "orderEntity.id")
    OrderItemResponse toDto(OrderItemEntity orderItemEntity);
}

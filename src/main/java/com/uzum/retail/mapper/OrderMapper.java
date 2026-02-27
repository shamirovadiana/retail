package com.uzum.retail.mapper;

import com.uzum.retail.dto.request.OrderRequest;
import com.uzum.retail.dto.response.OrderResponse;
import com.uzum.retail.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    OrderEntity toEntity(OrderRequest request);

    OrderResponse toDto(OrderEntity orderEntity);
}

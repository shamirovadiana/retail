package com.uzum.retail.mapper;

import com.uzum.retail.dto.request.ProductRequest;
import com.uzum.retail.dto.response.ProductResponse;
import com.uzum.retail.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category.id", source = "categoryId")
    ProductEntity toEntity(ProductRequest request);

    ProductResponse toDto(ProductEntity productEntity);
}

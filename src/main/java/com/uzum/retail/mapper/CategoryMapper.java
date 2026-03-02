package com.uzum.retail.mapper;

import com.uzum.retail.dto.request.CategoryRequest;
import com.uzum.retail.dto.response.CategoryResponse;
import com.uzum.retail.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    CategoryEntity toEntity(CategoryRequest request);

    CategoryResponse toDto(CategoryEntity entity);
    //PagedModel<CategoryResponse> toDto(PagedModel<CategoryEntity> pageOfEntities, Pageable pageable);
}

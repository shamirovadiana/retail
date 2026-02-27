package com.uzum.retail.service;

import com.uzum.retail.dto.request.CategoryRequest;
import com.uzum.retail.dto.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CategoryService {

    CategoryResponse create(CategoryRequest request);

    CategoryResponse getById(Long id);

    Page<CategoryResponse> getAllCategories(Pageable pageable);

    CategoryResponse update(Long id, CategoryRequest request);

    void delete(Long id);
}

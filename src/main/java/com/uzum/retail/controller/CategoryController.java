package com.uzum.retail.controller;

import com.uzum.retail.dto.request.CategoryRequest;
import com.uzum.retail.dto.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface CategoryController {

    ResponseEntity<CategoryResponse> createCategory(CategoryRequest categoryRequest);

    ResponseEntity<Page<CategoryResponse>> getCategories(int page, int size);

    ResponseEntity<CategoryResponse> getCategory(Long id);

    ResponseEntity<CategoryResponse> updateCategory(Long id, CategoryRequest categoryRequest);

    void deleteCategory(Long id);
}

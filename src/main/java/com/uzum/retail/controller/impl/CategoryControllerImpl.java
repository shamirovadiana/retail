package com.uzum.retail.controller.impl;

import com.uzum.retail.controller.CategoryController;
import com.uzum.retail.dto.request.CategoryRequest;
import com.uzum.retail.dto.response.CategoryResponse;
import com.uzum.retail.service.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CategoryControllerImpl implements CategoryController {

    CategoryServiceImpl categoryService;

    @PostMapping("/create-category")
    @Override
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.create(categoryRequest));
    }

    @GetMapping("/get-categories")
    @Override
    public Page<CategoryResponse> getCategories(Pageable pageable) {
        return categoryService.getAllCategories(pageable);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.update(id, categoryRequest));
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }
}

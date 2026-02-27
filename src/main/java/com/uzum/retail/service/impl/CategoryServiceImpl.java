package com.uzum.retail.service.impl;

import com.uzum.retail.dto.request.CategoryRequest;
import com.uzum.retail.dto.response.CategoryResponse;
import com.uzum.retail.entity.CategoryEntity;
import com.uzum.retail.exception.CategoryNotFoundException;
import com.uzum.retail.mapper.CategoryMapper;
import com.uzum.retail.repository.CategoryRepository;
import com.uzum.retail.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @Override
    public CategoryResponse create(CategoryRequest request) {
        CategoryEntity entity = categoryMapper.toEntity(request);
        CategoryEntity result = categoryRepository.save(entity);

        return categoryMapper.toDto(result);
    }

    @Override
    public CategoryResponse getById(Long id) {
        CategoryEntity result = categoryRepository
                .findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id.toString()));

        return categoryMapper.toDto(result);
    }

    @Override
    public Page<CategoryResponse> getAllCategories(Pageable pageable) {
        Page<CategoryEntity> categories = categoryRepository.findAll(pageable);
        return categories.map(categoryMapper::toDto);
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) {
        CategoryEntity existingCategory = categoryRepository
                .findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id.toString()));

        existingCategory.setName(request.name());
        categoryRepository.save(existingCategory);

        return categoryMapper.toDto(existingCategory);
    }

    @Override
    public void delete(Long id) {
        categoryRepository
                .findById(id)
                .orElseThrow(()-> new CategoryNotFoundException(id.toString()));

        categoryRepository.deleteById(id);
    }
}

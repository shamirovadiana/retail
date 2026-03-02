package com.uzum.retail.service;

import com.uzum.retail.dto.request.CategoryRequest;
import com.uzum.retail.dto.response.CategoryResponse;
import com.uzum.retail.entity.CategoryEntity;
import com.uzum.retail.exception.CategoryNotFoundException;
import com.uzum.retail.mapper.CategoryMapper;
import com.uzum.retail.repository.CategoryRepository;
import com.uzum.retail.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Spy
    CategoryMapper categoryMapper;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryServiceImpl categoryService;

    @Captor
    ArgumentCaptor<CategoryEntity> categoryCaptor;

//    @Test
//    @DisplayName("getAllCategories")
//    void getAllCategories_ShouldReturnPageOfCategoryResponses(){
//        Pageable pageable = Pageable.unpaged();
//        CategoryEntity entity1 = new CategoryEntity(1L, "Coats");
//        CategoryEntity entity2 = new CategoryEntity(2L, "Caps");
//        CategoryResponse response1 = new CategoryResponse(1L);
//        CategoryResponse response2 = new CategoryResponse(2L);
//        Page<CategoryEntity> pageOfEntities = new PageImpl<>(List.of(entity1, entity2));
//        Page<CategoryResponse> pageOfResponses = new PageImpl<>(List.of(response1, response2));
//
//        when(categoryRepository.findAll(pageable)).thenReturn(pageOfEntities);
//        when(categoryMapper.toDto(pageOfEntities)).thenReturn(pageOfResponses);
//
//        Page<CategoryResponse> result = categoryService.getAllCategories(pageable);
//        assertThat(result).isNotNull();
//        assertThat(pageOfResponses).isNotNull().isEqualTo(result);
//
//        verify(categoryRepository, times(1)).findAll(pageable);
//        verify(categoryMapper, times(1)).toDto(pageOfEntities);
//        verify(categoryMapper, times(1)).toDto(categoryCaptor.capture());
//
//    }

    @Test
    @DisplayName("getById")
    void getCategoryById_ShouldReturnCategoryResponse_WhenCategoryExists(){
        Long categoryId = 1L;
        CategoryEntity categoryEntity = new CategoryEntity();
        CategoryResponse categoryResponse = new CategoryResponse(1L);

        when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.of(categoryEntity));

        when(categoryMapper.toDto(categoryEntity))
                .thenReturn(categoryResponse);

        CategoryResponse result = categoryService.getById(categoryId);

        assertThat(result).isNotNull();
        assertThat(categoryResponse)
                .isNotNull()
                .isEqualTo(result);

        verify(categoryRepository, times(1)).findById(categoryId);
        verify(categoryMapper, times(1)).toDto(categoryEntity);
        verify(categoryMapper, times(1)).toDto(categoryCaptor.capture());
    }

    @Test
    @DisplayName("getById throws categoryNotFoundException")
    void getCategoryById_ShouldThrowCategoryNotFoundException_WhenCategoryDoesNotExist(){
        Long categoryId = 9L;

        when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, ()->categoryService.getById(categoryId));
        verify(categoryRepository, times(1)).findById(categoryId);
        verifyNoInteractions(categoryMapper);
    }

    @Test
    @DisplayName("createCategory")
    void createCategory_ShouldReturnCategoryResponse(){
        CategoryEntity categoryEntity = new CategoryEntity(10L, "dresses");
        CategoryRequest categoryRequest = new CategoryRequest("dresses");
        CategoryResponse categoryResponse = new CategoryResponse(10L);

        when(categoryMapper.toEntity(categoryRequest))
                .thenReturn(categoryEntity);

        when(categoryRepository.save(categoryEntity))
                .thenReturn(categoryEntity);

        when(categoryMapper.toDto(categoryEntity))
                .thenReturn(categoryResponse);

        CategoryResponse result = categoryService.create(categoryRequest);

        assertThat(result).isNotNull();
        assertThat(categoryResponse).isNotNull().isEqualTo(result);

        verify(categoryMapper, times(1)).toEntity(categoryRequest);
        verify(categoryRepository, times(1)).save(categoryEntity);
        verify(categoryMapper, times(1)).toDto(categoryEntity);
        verify(categoryMapper, times(1)).toDto(categoryCaptor.capture());
    }

    @Test
    @DisplayName("updateCategory")
    void updateCategory_ShouldReturnCategoryResponse(){
        Long categoryId = 8L;
        CategoryEntity categoryEntity = new CategoryEntity();
        CategoryRequest categoryRequest = new CategoryRequest("Shirts");
        CategoryResponse categoryResponse = new CategoryResponse(8L);

        when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.of(categoryEntity));

        when(categoryRepository.save(categoryEntity))
                .thenReturn(categoryEntity);

        when(categoryMapper.toDto(categoryEntity))
                .thenReturn(categoryResponse);

        CategoryResponse result = categoryService.update(categoryId, categoryRequest);

        assertThat(result).isNotNull();
        assertThat(categoryResponse).isNotNull().isEqualTo(result);

        verify(categoryRepository, times(1)).findById(categoryId);
        verify(categoryRepository, times(1)).save(categoryEntity);
        verify(categoryMapper, times(1)).toDto(categoryEntity);
        verify(categoryMapper, times(1)).toDto(categoryCaptor.capture());
    }

    @Test
    @DisplayName("updateCategory throws CategoryNotFoundException")
    void updateCategory_ShouldThrowCategoryNotFoundException_WhenCategoryDoesNotExist(){
        Long categoryId = 8L;

        when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, ()->categoryService.getById(categoryId));

        verify(categoryRepository, times(1)).findById(categoryId);
        verifyNoInteractions(categoryMapper);
    }

    @Test
    @DisplayName("deleteCategory")
    void deleteCategory_ShouldReturnNoResponse(){
        Long categoryId = 8L;
        CategoryEntity categoryEntity = new CategoryEntity();

        when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.of(categoryEntity));

        doNothing().when(categoryRepository).deleteById(isA(Long.class));
        categoryService.delete(categoryId);

        verify(categoryRepository, times(1)).findById(categoryId);
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }

    @Test
    @DisplayName("deleteCategory throws CategoryNotFoundException")
    void deleteCategory_ShouldThrowCategoryNotFoundException_WhenCategoryDoesNotExist(){
        Long categoryId = 8L;

        when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class,()->categoryService.delete(categoryId));

        verify(categoryRepository, times(1)).findById(categoryId);
    }
}

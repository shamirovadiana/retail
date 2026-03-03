package com.uzum.retail.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.common.Json;
import com.uzum.retail.controller.impl.CategoryControllerImpl;
import com.uzum.retail.dto.request.CategoryRequest;
import com.uzum.retail.dto.response.CategoryResponse;
import com.uzum.retail.entity.CategoryEntity;
import com.uzum.retail.exception.CategoryNotFoundException;
import com.uzum.retail.mapper.CategoryMapper;
import com.uzum.retail.repository.CategoryRepository;
import com.uzum.retail.service.impl.CategoryServiceImpl;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.Parameter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.cglib.core.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryControllerImpl.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoryServiceImpl categoryService;

    @MockitoBean
    private CategoryMapper categoryMapper;
    @MockitoBean
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Test getAllCategories - Validation happy flow")
    void getAllCategories_ShouldReturnPageOfCategories() throws Exception{
        CategoryResponse response1 = new CategoryResponse(5L);
        CategoryResponse response2 = new CategoryResponse(6L);
        Page<CategoryResponse> page = new PageImpl<>(
                List.of(response1, response2),
                Pageable.ofSize(10),1);

        ObjectMapper objectMapper = new ObjectMapper();
        when(categoryService.getAllCategories(PageRequest.of(0,2))).thenReturn(page);

        String res = objectMapper.writeValueAsString(page);

        mockMvc.perform(
                get("/api/categories/get-categories" )
                        .param("page", "0")
                        .param("size","2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(res))
                .andDo(print());

        Mockito.verify(categoryService, Mockito.times(1)).getAllCategories(PageRequest.of(0,2));
    }

//    @Test
//    @DisplayName("Test getAllCategories - empty page")
//    void getAllCategories_ShouldReturnEmptyPageOfCategories() throws Exception{
//        when(categoryService.getAllCategories(PageRequest.of(0,2))).thenReturn(Page.empty());
//
//        Page<CategoryResponse> page = new PageImpl<>(List.of(), Pageable.ofSize(1),0);
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        String result = objectMapper.writeValueAsString(page);
//
//        mockMvc.perform(
//                        get("/api/categories/get-categories" )
//                                .param("page", "0")
//                                .param("size","2")
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json(result))
//                .andDo(print());
//
//        Mockito.verify(categoryService, Mockito.times(1)).getAllCategories(PageRequest.of(0,2));
//    }

    @Test
    @DisplayName("Test getCategoryById - Validation happy flow")
    void getCategoryById_ShouldReturnCategoryResponse_WhenCategoryExists() throws Exception{
        Long categoryId = 1L;
        CategoryResponse categoryResponse = new CategoryResponse(1L);

        when(categoryService.getById(categoryId)).thenReturn(categoryResponse);

        mockMvc.perform(get("/api/categories/{id}", categoryId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));

        Mockito.verify(categoryService, Mockito.times(1)).getById(categoryId);
    }

    @ParameterizedTest
    @ValueSource(longs = {1,2})
    @DisplayName("Test getCategoryById - Validation category not found")
    void getCategoryById_ShouldReturnNotFound_WhenCategoryDoesNotExist(@NotNull Long categoryId) throws Exception{
        when(categoryService.getById(categoryId)).thenThrow(new CategoryNotFoundException(categoryId.toString()));

        mockMvc.perform(get("/api/categories/{id}", categoryId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        Mockito.verify(categoryService, Mockito.times(1)).getById(categoryId);
    }

    @Test
    @DisplayName("Test updateCategory - Validation happy flow")
    void updateCategory_ShouldReturnCategoryResponse_WhenCategoryExists() throws Exception{
        Long categoryId = 1L;
        CategoryRequest categoryRequest = new CategoryRequest("updatedCategoryName");
        CategoryResponse categoryResponse = new CategoryResponse(1L);

        when(categoryService.getById(categoryId)).thenReturn(categoryResponse);

        when(categoryService.update(categoryId, categoryRequest)).thenReturn(categoryResponse);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(categoryRequest);

        mockMvc.perform(put("/api/categories/{id}", categoryId)
                        .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(categoryService, Mockito.times(1)).update(categoryId, categoryRequest);
    }

    @Test
    @DisplayName("Test updateCategory - Validation category not found")
    void updateCategory_ShouldReturnNotFound_WhenCategoryDoesNotExist() throws Exception{
        Long categoryId = 1L;
        CategoryRequest categoryRequest = new CategoryRequest("updatedCategoryName");

        when(categoryService.getById(categoryId)).thenThrow(new CategoryNotFoundException(categoryId.toString()));

        when(categoryService.update(categoryId, categoryRequest)).thenThrow(new CategoryNotFoundException(categoryId.toString()));

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(categoryRequest);

        mockMvc.perform(put("/api/categories/{id}", categoryId)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test deleteCategory - Validation happy flow")
    void deleteCategory_ShouldReturnNoResponse_WhenCategoryDoesNotExist() throws Exception{
        Long categoryId = 1L;
        CategoryResponse categoryResponse = new CategoryResponse(1L);

        when(categoryService.getById(categoryId)).thenReturn(categoryResponse);

        mockMvc.perform(delete("/api/categories/{id}", categoryId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(categoryService, Mockito.times(1)).delete(categoryId);
    }

//    @Test
//    @DisplayName("Test deleteCategory - Validation category not found")
//    void deleteCategory_ShouldReturnNotFound_WhenCategoryDoesNotExist() throws Exception{
//        Long categoryId = 1L;
//
//        when(categoryService.getById(categoryId)).thenThrow(new CategoryNotFoundException(categoryId.toString()));
//
//        mockMvc.perform(delete("/api/categories/{id}", categoryId)
//                .contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(status().isNotFound());
//
//        Mockito.verify(categoryService, Mockito.times(1)).delete(categoryId);
//    }
}

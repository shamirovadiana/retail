package com.uzum.retail.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.common.Json;
import com.uzum.retail.controller.impl.CategoryControllerImpl;
import com.uzum.retail.dto.response.CategoryResponse;
import com.uzum.retail.entity.CategoryEntity;
import com.uzum.retail.mapper.CategoryMapper;
import com.uzum.retail.repository.CategoryRepository;
import com.uzum.retail.service.impl.CategoryServiceImpl;
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
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    static Stream<Arguments> pageableSource(){
        return Stream.of(arguments(PageRequest.of(0,2)));
    }
    @ParameterizedTest
    @MethodSource("pageableSource")
    @DisplayName("Test getAllCategories - Validation happy flow")
    void getAllCategories_ShouldReturnPageOfCategories(Pageable pageable) throws Exception{
//        CategoryEntity category1 = new CategoryEntity(1L, "Dresses");
//        CategoryEntity category2 = new CategoryEntity(4L, "Shirts");
//        categoryRepository.save(category1);
//        categoryRepository.save(category2);
//
//
//        CategoryResponse response1 = categoryMapper.toDto(category1);
//        CategoryResponse response2 = categoryMapper.toDto(category2);

//        CategoryResponse response1 = new CategoryResponse(5L);
//        CategoryResponse response2 = new CategoryResponse(6L);
//        Page<CategoryResponse> page = new PageImpl<>(List.of(response1, response2));
//        ObjectMapper objectMapper = new ObjectMapper();
//        when(categoryService.getAllCategories(pageable)).thenReturn(page);
//
//        mockMvc.perform(get("/api/categories/get-categories")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.convertValue(page, String.class)));
//
//
//        Mockito.verify(categoryService, Mockito.times(1)).getAllCategories(pageable);


    }
}

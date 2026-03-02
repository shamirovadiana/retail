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

}

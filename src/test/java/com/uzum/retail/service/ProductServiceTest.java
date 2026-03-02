package com.uzum.retail.service;

import com.uzum.retail.dto.request.ProductRequest;
import com.uzum.retail.dto.response.ProductResponse;
import com.uzum.retail.entity.ProductEntity;
import com.uzum.retail.exception.ProductNotFoundException;
import com.uzum.retail.mapper.ProductMapper;
import com.uzum.retail.repository.ProductRepository;
import com.uzum.retail.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Spy
    ProductMapper productMapper;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Captor
    ArgumentCaptor<ProductEntity> productCaptor;

    @Test
    @DisplayName("getById")
    void getProductById_ShouldReturnProductResponse_WhenProductExists(){
        Long productId = 1L;
        ProductEntity productEntity = new ProductEntity();
        ProductResponse productResponse = new ProductResponse(1L);

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(productEntity));

        when(productMapper.toDto(productEntity))
                .thenReturn(productResponse);

        ProductResponse result = productService.getById(productId);

        assertThat(result).isNotNull();
        assertThat(productResponse).isNotNull()
                .isEqualTo(result);

        verify(productRepository, times(1)).findById(productId);
        verify(productMapper, times(1)).toDto(productEntity);
        verify(productMapper, times(1)).toDto(productCaptor.capture());
    }

    @Test
    @DisplayName("getById throws ProductNotFoundException")
    void getProductById_ShouldThrowProductNotFoundException_WhenProductDoesNotExist(){
        Long productId = 1L;

        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, ()->productService.getById(productId));
        verify(productRepository, times(1)).findById(productId);
        verifyNoInteractions(productMapper);
    }

    @Test
    @DisplayName("createProduct")
    void createProduct_ShouldReturnProductResponse(){
        ProductEntity productEntity = new ProductEntity();
        ProductResponse productResponse = new ProductResponse(1L);
        ProductRequest productRequest = new ProductRequest("12345", "49", "purple", null, 50.0, 1L);

        when(productMapper.toEntity(productRequest))
                .thenReturn(productEntity);

        when(productRepository.save(productEntity))
                .thenReturn(productEntity);

        when(productMapper.toDto(productEntity))
                .thenReturn(productResponse);

        ProductResponse result = productService.create(productRequest);

        assertThat(result).isNotNull();
        assertThat(productResponse).isNotNull().isEqualTo(result);

        verify(productMapper, times(1)).toEntity(productRequest);
        verify(productRepository, times(1)).save(productEntity);
        verify(productMapper, times(1)).toDto(productEntity);
        verify(productMapper, times(1)).toDto(productCaptor.capture());
    }

    @Test
    @DisplayName("deleteProduct")
    void deleteProduct_ShouldReturnNoResponse(){
        Long productId = 1L;
        ProductEntity productEntity = new ProductEntity();

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(productEntity));

        doNothing().when(productRepository).deleteById(isA(Long.class));
        productService.delete(productId);

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    @DisplayName("deleteProduct throws ProductNotFoundException")
    void deleteProduct_ShouldThrowProductNotFoundException(){
        Long productId = 1L;

        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, ()->productService.delete(productId));

        verify(productRepository, times(1)).findById(productId);
    }
}

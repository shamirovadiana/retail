package com.uzum.retail.service.impl;

import com.uzum.retail.dto.request.ProductRequest;
import com.uzum.retail.dto.response.ProductResponse;
import com.uzum.retail.entity.ProductEntity;
import com.uzum.retail.exception.ProductNotFoundException;
import com.uzum.retail.mapper.ProductMapper;
import com.uzum.retail.repository.ProductRepository;
import com.uzum.retail.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    ProductMapper productMapper;
    ProductRepository productRepository;

    @Override
    public ProductResponse create(ProductRequest request) {
        ProductEntity productEntity = productMapper.toEntity(request);
        ProductEntity result = productRepository.save(productEntity);
        return productMapper.toDto(result);
    }

    @Override
    public ProductResponse getById(Long id) {
        ProductEntity productEntity = productRepository
                .findById(id)
                .orElseThrow(()-> new ProductNotFoundException(id.toString()));

        return productMapper.toDto(productEntity);
    }

    @Override
    public Page<ProductResponse> getProductsByCategory(Pageable pageable, Long categoryId) {
        Page<ProductEntity> products = productRepository.findAllProductsByCategoryId(pageable, categoryId);
        return products.map(productMapper::toDto);
    }


    @Override
    public void delete(Long id) {
        productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));

        productRepository.deleteById(id);
    }
}

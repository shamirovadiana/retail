package com.uzum.retail.service;

import com.uzum.retail.dto.request.ProductRequest;
import com.uzum.retail.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse getById(Long id);

    Page<ProductResponse> getProductsByCategory(Pageable pageable, Long categoryId);

    void delete(Long id);
}

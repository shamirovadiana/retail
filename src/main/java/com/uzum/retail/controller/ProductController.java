package com.uzum.retail.controller;

import com.uzum.retail.dto.request.ProductRequest;
import com.uzum.retail.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface ProductController {

    ResponseEntity<ProductResponse> createProduct(ProductRequest request);

    ResponseEntity<ProductResponse> getProduct(Long id);

    Page<ProductResponse> getProductsByCategory(Pageable pageable, Long id);

    void deleteProduct(Long id);
}

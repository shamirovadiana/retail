package com.uzum.retail.controller.impl;

import com.uzum.retail.controller.ProductController;
import com.uzum.retail.dto.request.ProductRequest;
import com.uzum.retail.dto.response.ProductResponse;
import com.uzum.retail.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductControllerImpl implements ProductController {

    ProductServiceImpl productService;

    @PostMapping("/create-product")
    @Override
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.create(request));
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/get-products/{id}")
    @Override
    public Page<ProductResponse> getProductsByCategory(Pageable pageable,@PathVariable Long id) {
        return productService.getProductsByCategory(pageable, id);
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}

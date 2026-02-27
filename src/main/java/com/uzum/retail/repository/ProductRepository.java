package com.uzum.retail.repository;

import com.uzum.retail.dto.response.ProductResponse;
import com.uzum.retail.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    //@Query("select p from ProductEntity p where p.category =?1")
    Page<ProductEntity> findAllProductsByCategoryId(Pageable pageable, Long categoryId);
}

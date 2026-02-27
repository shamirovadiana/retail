package com.uzum.retail.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "order_items")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @NotNull
    @JoinColumn(name = "product_id")
    ProductEntity productEntity;

    @NotNull
    Double productAmount;

    @Nullable
    Double price;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @NotNull
//    @JoinColumn(name = "order_id")
//    OrderEntity orderEntity;
}

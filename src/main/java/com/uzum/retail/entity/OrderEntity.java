package com.uzum.retail.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @CreatedDate
    LocalDateTime createdAt;

    @NotNull
    @LastModifiedDate
    LocalDateTime updatedAt;


    //@JoinColumn(name = "order_item_id")
    @JoinTable(
            name = "orders_items",
            joinColumns = { @JoinColumn(name="order_id")},
            inverseJoinColumns = {@JoinColumn(name="order_item_id")}
    )
    @OneToMany
    List<OrderItemEntity> items = new ArrayList<>();

    @NotNull
    Double totalPrice;
}

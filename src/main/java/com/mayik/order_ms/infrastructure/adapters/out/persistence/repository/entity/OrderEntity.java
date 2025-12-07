package com.mayik.order_ms.infrastructure.adapters.out.persistence.repository.entity;

import com.mayik.order_ms.domain.model.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    @ElementCollection
    private List<String> items;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
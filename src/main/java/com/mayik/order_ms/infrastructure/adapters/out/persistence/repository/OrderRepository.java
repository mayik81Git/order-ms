package com.mayik.order_ms.infrastructure.adapters.out.persistence.repository;

import com.mayik.order_ms.infrastructure.adapters.out.persistence.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
